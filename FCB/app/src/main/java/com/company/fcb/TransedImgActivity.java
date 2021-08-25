package com.company.fcb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.icu.number.Scale;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import static android.speech.tts.TextToSpeech.ERROR;
import static org.opencv.core.Core.countNonZero;
import static org.opencv.imgproc.Imgproc.rectangle;

public class TransedImgActivity extends AppCompatActivity {
    private ArrayList<Rect> rect_point = null;  // 각 점 시작점, 넓이, 높이
    private float width_avg = 0, height_avg = 0;    // 각 점 넓이, 높이 평균
    private ArrayList<Integer> center_x = null, center_y = null;    // 각 점 중앙 위치
    private ArrayList<Bitmap[]> bArea = null;   // 각 글자별로 자른 점자 이미지
    private int[] transed_braille = null;    // 번역된 이진화 점자(ex: "110110")
    private ImageView imgVBack = null;
    private TextView txtBraille, txtViewTrans;
    private Bitmap bitmapImg = null;    // 1차 편집 이미지
    private Bitmap rotatedImg = null;   // 2차 편집 이미지
    private Bitmap originalImg = null;  // 3차 원본 이미지
    private TextToSpeech tts;              // TTS 변수 선언

    // Opencv가 제대로 열렸는지 체크
    static {
        if (!OpenCVLoader.initDebug()) {
            System.out.println("OpenCV not loaded");
        } else {
            System.out.println("OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgtrans_view);

        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        try {
            // 핸드폰 화면 사이즈 받아오기
            Display display = getWindowManager().getDefaultDisplay();  // in Activity
            Point size = new Point();
            display.getRealSize(size); // or getSize(size)
            int width = size.x;
            int height = size.y;

            txtBraille = (TextView) findViewById(R.id.txtViewBraille);
            txtViewTrans = (TextView) findViewById(R.id.txtViewTrans);
            imgVBack = (ImageView) findViewById(R.id.imgVBack);
            // 점자 이미지 최대 사이즈 조절
            imgVBack.setMaxWidth((width * 9) / 10);
            imgVBack.setMaxHeight(height / 4);
            imgVBack.getAdjustViewBounds();

            // 앞에서 자른 이미지 받아오기
            originalImg = CropImageActivity.bitmap_crop.createScaledBitmap(CropImageActivity.bitmap_crop, CropImageActivity.bitmap_crop.getWidth() * 2, CropImageActivity.bitmap_crop.getHeight() * 2, true);
            bitmapImg = CropImageActivity.bitmap_crop.createScaledBitmap(CropImageActivity.bitmap_crop, CropImageActivity.bitmap_crop.getWidth() * 2, CropImageActivity.bitmap_crop.getHeight() * 2, true);
            rotatedImg = CropImageActivity.bitmap_crop.createScaledBitmap(CropImageActivity.bitmap_crop, CropImageActivity.bitmap_crop.getWidth() * 2, CropImageActivity.bitmap_crop.getHeight() * 2, true);
        } catch (Exception e) {
            System.out.println("onCreate error : " + e);
            finish();
        }

        //여기서 메서드 호출해서 bitmapImg에 bitmap 반환받은 값 넣기
        // 1. Guassian 적용
        bitmapImg = chageToGray(bitmapImg);

        // 2. 흰 부분들 찾아서 직사각형 처리
        bitmapImg = drawRectangle(bitmapImg);
        getInfo();  // 필요한 정보들 계산

        // 3. 이미지 회전
        rotatedImg = rotateImg(bitmapImg);

        // 4. 1~2번 다시 회전 이미지에 적용
        rotatedImg = chageToGray(rotatedImg);
        rotatedImg = drawRectangle(rotatedImg);
        RemoveinRect();  // 겹치는 네모 없애기

        //4.5 점자 면적을 채워주기
        drawC();

        // 값들 초기화
        width_avg = 0;
        height_avg = 0;
        // 가로, 세로 평균 구하기
        getInfo();

        //5. 중심점 선들 찾기
        drawLineRect();
        ReArray();

        //6. 점자 간 거리 계산
        CalcDistance();

        //7. 이미지 자를 영역 계산
        rotatedImg = BrailleArea(rotatedImg);

        //8. 이미지 Binary Code화 및 흰색 부분 % 계산
        rotatedImg = MKImageToBC(rotatedImg);

        // 바로 시작하면 tts에 bound 되기 전에 실행해서 소리가 안남
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //9. 점자 번역
                transe_braille();
            }
        }, 500); //딜레이 타임 조절

        // 점자 이미지 배치
        imgVBack.setImageBitmap(originalImg);
    }

    // 1. Guassian 적용
    public Bitmap chageToGray(Bitmap bt) {
        Bitmap result = bt;
        Mat orgMat = new Mat();
        Mat grayMat = new Mat();
        Mat blur1 = new Mat();
        Mat blur2 = new Mat();
        Mat mt_braille = new Mat();

        try {
            // Bitmap -> Math
            Utils.bitmapToMat(bt, orgMat);

            // 흑백처리
            Imgproc.cvtColor(orgMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // 이미지 흐리게 하기
            Imgproc.GaussianBlur(grayMat, grayMat, new Size(0, 0), 5, 5);
            Imgproc.GaussianBlur(grayMat, blur1, new Size(15, 15), 5);
            Imgproc.GaussianBlur(grayMat, blur2, new Size(21, 21), 5);

            //Subtracting the two blurred images
            Core.absdiff(blur1, blur2, mt_braille);

            //Inverse Binary Thresholding
            Core.multiply(mt_braille, new Scalar(100), mt_braille);
            Imgproc.GaussianBlur(mt_braille, mt_braille, new Size(0, 0), 5);

            for (int i = 0; i < 2; i++) {
                // 배경 잡음 제거
                Imgproc.erode(mt_braille, mt_braille, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)));
                Imgproc.dilate(mt_braille, mt_braille, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)));
            }
            Imgproc.threshold(mt_braille, mt_braille, 50, 255, Imgproc.THRESH_BINARY);

            // Mat to Bitmap
            Utils.matToBitmap(mt_braille, result);
        } catch (Exception e) {
            System.out.println("chageToGray error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }

    // 2. 흰 부분들 찾아서 직사각형 처리
    public Bitmap drawRectangle(Bitmap bt) {
        Bitmap result = bt;
        Mat orgMat = new Mat();
        Mat grayMat = new Mat();
        Mat hierMat = new Mat();
        int cnt = 0;
        rect_point = new ArrayList<Rect>();

        try {
            // Bitmap -> Math
            Utils.bitmapToMat(bt, orgMat);

            // RGB -> BGR
            Imgproc.cvtColor(orgMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(grayMat, contours, hierMat, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            // 사각형 그리기
            for (int i = 0; i < contours.size(); i++) {
                MatOfPoint2f approxCurve = new MatOfPoint2f();
                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());

                double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
                MatOfPoint points = new MatOfPoint(approxCurve.toArray());

                // 네모 그릴 점자 부분 좌표 및 가로 세로 길이
                Rect rect = Imgproc.boundingRect(points);
                if (Checkrectangle(rect)) {
                    rect_point.add(rect);
                    rect_point.get(cnt).width += 5;
                    cnt++;
                    //rectangle(orgMat, new org.opencv.core.Point(rect.x, rect.y), new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 5);
                }
            }

            // Mat to Bitmap
            Utils.matToBitmap(orgMat, result);
        } catch (Exception e) {
            System.out.println("drawRectangle error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }

    public boolean Checkrectangle(Rect rect) {
        boolean result = true;

        try {
            // 점자 위치가 0인 경우
            if (rect.x <= 5 || rect.y <= 5) {
                result = false;
            }

            // 작은 경우
            // weight or height가 15이하
            if ((rect.width <= 15) || (rect.height <= 15)) {
                result = false;
            }

            // 큰 경우
            // 화면보다 큰 경우
            else if ((rect.width > bitmapImg.getWidth()) && (rect.height > bitmapImg.getHeight())) {
                result = false;
            }

            // 짧은 면 대비 긴 면이 3배 이상인 경우
            else if (rect.width < rect.height) {  // 넓이가 짧은 경우
                if ((rect.height / rect.width) >= 3) {
                    result = false;
                }
            } else if (rect.width > rect.height) {  // 높이가 짧은 경우
                if ((rect.width / rect.height) >= 3) {
                    result = false;
                }
            } else {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Checkrectangle error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }

    // 가로, 세로 평균 구하기
    public void getInfo() {
        int cnt_x = 0, cnt_y = 0;

        try {
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    if (rect_point.get(i).width < 90) {
                        cnt_x++;
                        width_avg += rect_point.get(i).width;
                    }
                    if (rect_point.get(i).height < 90) {
                        cnt_y++;
                        height_avg += rect_point.get(i).height;
                    }
                }
            }
            // 점자 부분 가로 세로 길이 평균
            width_avg = width_avg / cnt_x + 4;
            height_avg = height_avg / cnt_y + 10;
        } catch (Exception e) {
            System.out.println("getInfo error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    // 3. 이미지 회전
    public Bitmap rotateImg(Bitmap bt) {
        Bitmap result = bt;
        Mat rotatedMat = new Mat();
        int minY = 0, munX = 0;

        try {
            // Bitmap -> Math
            Utils.bitmapToMat(bt, rotatedMat);

            // RGB -> BGR
            Imgproc.cvtColor(rotatedMat, rotatedMat, Imgproc.COLOR_BGR2GRAY);

            // 가장 위에 있는 점 찾기
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    if (rect_point.get(minY).y >= rect_point.get(i).y) {
                        minY = i;
                    }
                }
            }

            // 높이가 braille_size 크기 내에 있는 애들 중 x 좌표가 가장 먼 점 구하기
            munX = minY;
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    if (Math.abs(rect_point.get(i).y - rect_point.get(minY).y) < height_avg) {  // 높이가 해당하는 경우
                        if (Math.abs(rect_point.get(minY).x - rect_point.get(munX).x) < Math.abs(rect_point.get(minY).x - rect_point.get(i).x)) {
                            munX = i;
                        }
                    }
                }
            }

            // 둘의 각도 구해
            double width = Math.abs(rect_point.get(minY).x - rect_point.get(munX).x);
            double height = Math.abs(rect_point.get(minY).y - rect_point.get(munX).y);
            double radian = Math.atan2(height, width);
            double angle = Math.round(radian * 180 / Math.PI);

            if (angle < 10) {
                // 이미지 회전!!
                Matrix rotateMatrix = new Matrix();
                if (angle > 1.5) {
                    rotateMatrix.postRotate((float) -angle + 1); //-360~360
                } else {
                    rotateMatrix.postRotate(0); //-360~360
                }
                result = Bitmap.createBitmap(rotatedImg, 0, 0, rotatedImg.getWidth(), rotatedImg.getHeight(), rotateMatrix, false);
            } else {
                Toast myToast = Toast.makeText(this.getApplicationContext(), "다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
                myToast.show();
                finish();
            }
        } catch (Exception e) {
            System.out.println("rotateImg error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }


    // 4. 겹치는 네모 없애기
    public void RemoveinRect() {
        try {
            for (int i = 0; i < rect_point.size(); i++) {
                for (int j = 0; j < rect_point.size(); j++) {
                    if ((rect_point.get(i) != null) && (rect_point.get(j) != null)) {
                        if (i != j) {
                            if ((rect_point.get(i).x < rect_point.get(j).x) && (rect_point.get(j).x < (rect_point.get(i).x + rect_point.get(i).width))) {
                                if ((rect_point.get(i).y < rect_point.get(j).y) && (rect_point.get(j).y < (rect_point.get(i).y + rect_point.get(i).height))) {
                                    rect_point.remove(j);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("RemoveinRect error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    //4.5 점자 면적을 채워주기
    public void drawC() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);

        Canvas cv = new Canvas(rotatedImg);

        for(int i = 0; i < rect_point.size(); i++) {
            int x = (rect_point.get(i).x+(rect_point.get(i).width/2));
            int y = (rect_point.get(i).y+(rect_point.get(i).height/2));
            if(width_avg < height_avg) {
                cv.drawCircle(x, y, height_avg / 3, paint);
            } else {
                cv.drawCircle(x, y, width_avg / 3, paint);
            }
        }
    }

    // 5. 각 점 중심 찾기
    public void drawLineRect() {
        ArrayList<Integer> rect_x = new ArrayList<>();
        ArrayList<Integer> rect_y = new ArrayList<>();
        center_x = new ArrayList<>();
        center_y = new ArrayList<>();

        try {
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    float chk_x = rect_point.get(i).width / width_avg;
                    float chk_y = rect_point.get(i).height / height_avg;

                    if ((rect_point.get(i).width >= Math.floor((width_avg * 4 / 5) - 5)) && (rect_point.get(i).height >= Math.floor((height_avg * 4 / 5) - 10))) {
                        // 가로 1개일 경우
                        if (chk_x <= 1.6) {
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width / 2));
                        }
                        // 가로 2개일 경우
                        else if ((chk_x > 1.6) && (chk_x <= 2.8)) {
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width / 4));
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width * 3 / 4));
                        }
                        // 가로 3개일 경우
                        else if (chk_x > 2.8) {
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width / 6));
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width * 3 / 6));
                            rect_x.add(rect_point.get(i).x + (rect_point.get(i).width * 5 / 6));
                        }

                        // 세로 1개일 경우
                        if (chk_y <= 1.6) {
                            rect_y.add(rect_point.get(i).y + rect_point.get(i).height / 2);
                        }
                        // 세로 2개일 경우
                        else if ((chk_y > 1.6) && (chk_y <= 2.6)) {
                            rect_y.add(rect_point.get(i).y + (rect_point.get(i).height / 4));
                            rect_y.add(rect_point.get(i).y + (rect_point.get(i).height * 3 / 4));
                        }
                        // 세로 3개일 경우
                        else if (chk_y > 2.6) {
                            rect_y.add(rect_point.get(i).y + (rect_point.get(i).height / 6));
                            rect_y.add(rect_point.get(i).y + (rect_point.get(i).height * 3 / 6));
                            rect_y.add(rect_point.get(i).y + (rect_point.get(i).height * 5 / 6));
                        }
                    } else {
                        System.out.println("width = " + rect_point.get(i).width);
                        System.out.println("width2 = " + ((width_avg * 4 / 5) - 5));
                        System.out.println("height = " + rect_point.get(i).height);
                        System.out.println("height2 = " + ((height_avg * 4 / 5) - 10));
                    }
                }
            }

            // 차이가 별로 나지 않는 x좌표 평균 내기
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    float chk_x = rect_point.get(i).width / width_avg;
                    int sum1 = 0, sum2 = 0, sum3 = 0;
                    int cnt1 = 0, cnt2 = 0, cnt3 = 0;

                    // 세로 1개일 경우
                    if (chk_x <= 1.6) {
                        for (int j = 0; j < rect_x.size(); j++) {
                            if ((rect_point.get(i).x <= rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + rect_point.get(i).width))) {
                                sum1 += rect_x.get(j);
                                cnt1++;
                            }
                        }
                    }
                    // 세로 2개일 경우
                    else if ((chk_x > 1.6) && (chk_x <= 2.8)) {
                        for (int j = 0; j < rect_x.size(); j++) {
                            if ((rect_point.get(i).x <= rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + (rect_point.get(i).width / 2)))) {
                                sum1 += rect_x.get(j);
                                cnt1++;
                            } else if (((rect_point.get(i).x + (rect_point.get(i).width / 2)) < rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + rect_point.get(i).width))) {
                                sum2 += rect_x.get(j);
                                cnt2++;
                            }
                        }
                    }
                    // 세로 3개일 경우
                    else if (chk_x > 2.8) {
                        for (int j = 0; j < rect_x.size(); j++) {
                            if ((rect_point.get(i).x <= rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + (rect_point.get(i).width / 3)))) {
                                sum1 += rect_x.get(j);
                                cnt1++;
                            } else if (((rect_point.get(i).x + (rect_point.get(i).width / 3)) < rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + (rect_point.get(i).width * 2 / 3)))) {
                                sum2 += rect_x.get(j);
                                cnt2++;
                            } else if (((rect_point.get(i).x + (rect_point.get(i).width * 2 / 3)) < rect_x.get(j)) && (rect_x.get(j) <= (rect_point.get(i).x + rect_point.get(i).width))) {
                                sum3 += rect_x.get(j);
                                cnt3++;
                            }
                        }
                    }

                    if (sum1 != 0) {
                        sum1 /= cnt1;
                        center_x.add(sum1);
                    }

                    if (sum2 != 0) {
                        sum2 /= cnt2;
                        center_x.add(sum2);
                    }

                    if (sum3 != 0) {
                        sum3 /= cnt3;
                        center_x.add(sum3);
                    }
                }
            }

            // 차이가 별로 나지 않는 y좌표 평균 내기
            for (int i = 0; i < rect_point.size(); i++) {
                if (rect_point.get(i) != null) {
                    float chk_y = rect_point.get(i).height / height_avg;
                    int sum1 = 0, sum2 = 0, sum3 = 0;
                    int cnt1 = 0, cnt2 = 0, cnt3 = 0;

                    // 가로 1개일 경우
                    if (chk_y <= 1.6) {
                        for (int j = 0; j < rect_y.size(); j++) {
                            if ((rect_point.get(i).y <= rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + rect_point.get(i).height))) {
                                sum1 += rect_y.get(j);
                                cnt1++;
                            }
                        }
                    }
                    // 가로 2개일 경우
                    else if ((chk_y > 1.6) && (chk_y <= 2.6)) {
                        for (int j = 0; j < rect_y.size(); j++) {
                            if ((rect_point.get(i).y <= rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + (rect_point.get(i).height / 2)))) {
                                sum1 += rect_y.get(j);
                                cnt1++;
                            } else if (((rect_point.get(i).y + (rect_point.get(i).height / 2)) < rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + rect_point.get(i).height))) {
                                sum2 += rect_y.get(j);
                                cnt2++;
                            }
                        }
                    }
                    // 가로 3개일 경우
                    else if (chk_y > 2.6) {
                        for (int j = 0; j < rect_y.size(); j++) {
                            if ((rect_point.get(i).y <= rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + (rect_point.get(i).height / 3)))) {
                                sum1 += rect_y.get(j);
                                cnt1++;
                            } else if (((rect_point.get(i).y + (rect_point.get(i).height / 3)) < rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + (rect_point.get(i).height * 2 / 3)))) {
                                sum2 += rect_y.get(j);
                                cnt2++;
                            } else if (((rect_point.get(i).y + (rect_point.get(i).height * 2 / 3)) < rect_y.get(j)) && (rect_y.get(j) <= (rect_point.get(i).y + rect_point.get(i).height))) {
                                sum3 += rect_y.get(j);
                                cnt3++;
                            }
                        }
                    }

                    if (sum1 != 0) {
                        sum1 /= cnt1;
                        center_y.add(sum1);
                    }

                    if (sum2 != 0) {
                        sum2 /= cnt2;
                        center_y.add(sum2);
                    }

                    if (sum3 != 0) {
                        sum3 /= cnt3;
                        center_y.add(sum3);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("drawLineRect error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    // 우선 배열이 엉망이니까 다시 정리해서 값 초기화
    public void ReArray() {
        try {
            // 오름차순 정리
            Collections.sort(center_x);
            Collections.sort(center_y);

            // x좌표 겹치거나 맨 앞 끝 줄 삭제
            if (center_x.get(0) <= 10) {
                center_x.remove(0);
            }
            for (int i = 0; i < center_x.size(); i++) {
                for (int j = i + 1; j < center_x.size(); j++) {
                    if ((center_x.get(i) - center_x.get(j)) == 0) {
                        center_x.remove(j);
                    }
                }
            }

            // y좌표 겹치거나 맨 앞 끝 줄 삭제
            if (center_y.get(0) <= 5) {
                center_y.remove(0);
            }
            for (int i = 0; i < center_y.size(); i++) {
                for (int j = i + 1; j < center_y.size(); j++) {
                    if ((center_y.get(i) - center_y.get(j)) == 0) {
                        center_y.remove(j);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ReArray error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    //6. 점자 간 거리 계산
    // (dx1: 1점-4점 거리, dx2:1번째 줄 2번째 점자 2점-1번째 줄 1번째 점자 1점)
    // (dy1: 1점-2점 거리, dy2:1번째 줄 1번째 점자 3점-2번째 줄 1번째 점자 1점)
    public void CalcDistance() {
        TreeSet<Integer> rmvVal_x;
        TreeSet<Integer> rmvVal_y;
        ArrayList<Integer> temp = new ArrayList<>();

        try {
            // TreeSet을 이용하여 중복제거
            rmvVal_x = new TreeSet<>(center_x);
            center_x = new ArrayList<>(rmvVal_x);
            rmvVal_y = new TreeSet<>(center_y);
            center_y = new ArrayList<>(rmvVal_y);

            // 맨 앞 점자의 1,2,3점이 다 빈 경우
            if ((center_x.get(1) - center_x.get(0)) > (center_x.get(2) - center_x.get(1))) {
                center_x.add((int) (center_x.get(0) - width_avg));
            }

            // 오름차순 정리
            Collections.sort(center_x);
            Collections.sort(center_y);

            // 띄어쓰기에 점자 중심점 채우기
            for (int i = 0; i < center_x.size(); i++) {
                if (i + 1 < center_x.size()) {
                    int num = (center_x.get(i + 1) - center_x.get(i));
                    float x = num / width_avg;
                    float cnt = center_x.get(2) - center_x.get(1);

                    // 빈 곳이 1칸인 경우
                    if ((x > 2) && (x < 3)) {
                        boolean chk1 = ((cnt-10) > num);
                        boolean chk2 = (((cnt + width_avg) - 20 < num) && (num < (cnt + width_avg) + 20));
                        if(chk1 || chk2) {
                            System.out.println(1);
                            if (((i + 1) % 2) == 1) {  // 앞에가 홀수
                                temp.add((int) (center_x.get(i) + width_avg));
                            } else {    // 앞에가 짝수
                                int cnt_line = 0;
                                // 뒤에 띄어쓰기 전까지의 점자 개수가 몇인지
                                for (int j = (i + 1); j < center_x.size(); j++) {
                                    if ((j + 1 < center_x.size()) && ((center_x.get(j + 1) - center_x.get(j)) / width_avg >= 2)) {
                                        break;
                                    } else if((j + 1 < center_x.size()) && ((center_x.get(j + 1) - center_x.get(j)) / width_avg < 2)) {
                                        cnt_line++;
                                    }
                                }
                                cnt_line += 1;

                                if ((cnt_line % 2) == 1) {   //뒤에가 홀수
                                    temp.add((int) (center_x.get(i + 1) - width_avg));
                                }
                            }
                        }
                    }
                    // 빈 곳이 2칸인 경우
                    else if ((x >= 3) && (x < 4)) {
                        if (((cnt + width_avg) - 20 < num) && (num < (cnt + width_avg) + 20)) {
                            temp.add((int) (center_x.get(i + 1) - (width_avg + 10)));
                        } else {
                            temp.add(center_x.get(i) + num / 3);
                            temp.add(center_x.get(i) + num * 2 / 3);
                        }
                    }
                    // 빈 곳이 3칸인 경우
                    else if (x >= 4) {
                        temp.add(center_x.get(i) + num / 4);
                        temp.add(center_x.get(i) + num * 2 / 4);
                        temp.add(center_x.get(i) + num * 3 / 4);
                    }
                }
            }

            for (int i = 0; i < temp.size(); i++) {
                center_x.add(temp.get(i));
            }

            // TreeSet을 이용하여 중복제거 및 오름차순 정렬
            rmvVal_x = new TreeSet<>(center_x);
            center_x = new ArrayList<>(rmvVal_x);
            rmvVal_y = new TreeSet<>(center_y);
            center_y = new ArrayList<>(rmvVal_y);

            // 맨 뒤 글자 4,5,6번이 다 빈 경우
            int chk = center_x.size() % 2;
            if (chk == 1) {
                center_x.add((int) (center_x.get(center_x.size() - 1) + width_avg));
            }

            // 가로 선들 겹치는 부분 제거
            temp = new ArrayList<>();
            for(int i = 0; i < center_y.size(); i++) {

            }

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.GREEN);
            paint.setTextSize(20);
            paint.setTextAlign(Paint.Align.CENTER);

            Canvas cv = new Canvas(rotatedImg);

            for(int i = 0; i < center_x.size(); i++) {
                paint.setColor(Color.GREEN);
                cv.drawLine(center_x.get(i), 0, center_x.get(i), rotatedImg.getHeight(), paint);
            }

            for(int i = 0; i < center_y.size(); i++) {
                paint.setColor(Color.GREEN);
                cv.drawLine(0, center_y.get(i), rotatedImg.getWidth(), center_y.get(i), paint);
            }

        } catch (Exception e) {
            System.out.println("CalcDistance error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    //7. 이미지 자를 영역 계산
    public Bitmap BrailleArea(Bitmap bt) {
        Bitmap result = bt;
        rect_point = null;
        rect_point = new ArrayList<Rect>();
        Mat orgMat = new Mat();

        try {
            // 오름차순 정리
            Collections.sort(center_x);
            Collections.sort(center_y);

            // Bitmap -> Math
            Utils.bitmapToMat(bt, orgMat);

            for (int i = 0; i < center_y.size(); i += 3) {
                if (i + 2 < center_y.size()) {
                    for (int j = 0; j < center_x.size(); j += 2) {
                        if (j + 1 < center_x.size()) {
                            int x = (int) (center_x.get(j) - width_avg / 2);
                            int y = (int) (center_y.get(i) - height_avg / 2);
                            int width = (int) ((center_x.get(j + 1) + width_avg / 2) - x);
                            int height = (int) ((center_y.get(i + 2) + height_avg / 2) - y);
                            Rect newRC = new Rect(x, y, width, height);
                            rect_point.add(newRC);
                        }
                    }
                }
            }

            for (int i = 0; i < rect_point.size(); i++) {
                //rectangle(orgMat, new org.opencv.core.Point(rect_point.get(i).x, rect_point.get(i).y), new org.opencv.core.Point(rect_point.get(i).x + rect_point.get(i).width, rect_point.get(i).y + rect_point.get(i).height), new Scalar(0, 0, 255), 5);
            }

            // Mat to Bitmap
            Utils.matToBitmap(orgMat, result);
        } catch (Exception e) {
            System.out.println("BrailleArea error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }

    //8. 이미지 Binary Code화 및 흰색 부분 % 계산
    public Bitmap MKImageToBC(Bitmap bt) {
        Bitmap result = bt;
        bArea = new ArrayList<>();
        Mat orgMat = new Mat();

        try {
            // Bitmap -> Math
            Utils.bitmapToMat(bt, orgMat);

            // 점자 부분들만 가져와서 bitmap에 넣어주기
            for (int i = 0; i < rect_point.size(); i++) {
                Bitmap[] bitSmall = new Bitmap[6];
                bitSmall[0] = Bitmap.createBitmap(bt, rect_point.get(i).x, rect_point.get(i).y, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bitSmall[1] = Bitmap.createBitmap(bt, rect_point.get(i).x, rect_point.get(i).y + rect_point.get(i).height / 3, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bitSmall[2] = Bitmap.createBitmap(bt, rect_point.get(i).x, rect_point.get(i).y + rect_point.get(i).height * 2 / 3, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bitSmall[3] = Bitmap.createBitmap(bt, rect_point.get(i).x + rect_point.get(i).width / 2, rect_point.get(i).y, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bitSmall[4] = Bitmap.createBitmap(bt, rect_point.get(i).x + rect_point.get(i).width / 2, rect_point.get(i).y + rect_point.get(i).height / 3, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bitSmall[5] = Bitmap.createBitmap(bt, rect_point.get(i).x + rect_point.get(i).width / 2, rect_point.get(i).y + rect_point.get(i).height * 2 / 3, rect_point.get(i).width / 2, rect_point.get(i).height / 3);
                bArea.add(bitSmall);
            }
            transed_braille = new int[bArea.size()];

            for (int i = 0; i < transed_braille.length; i++) {
                String rst_binary = "";
                for (int j = 0; j < 6; j++) {
                    Mat rpMat = new Mat();
                    double imgSize = 0, nonzero = 0;
                    int pct = 0;

                    Utils.bitmapToMat(bArea.get(i)[j], rpMat);
                    imgSize = rpMat.rows() * rpMat.cols();
                    nonzero = getWhiteProportion(rpMat);
                    pct = (int) ((nonzero / imgSize) * 100);

                    if ((27 <= pct) && (pct <= 100)) {
                        pct = 1;
                    } else {
                        pct = 0;
                    }

                    rst_binary += pct;
                }
                transed_braille[i] = Integer.parseInt(rst_binary);
            }

            // Mat to Bitmap
            Utils.matToBitmap(orgMat, result);

        } catch (Exception e) {
            System.out.println("MKImageToBC error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        return result;
    }

    // 흰 부분 비율 구하기
    public double getWhiteProportion(Mat img) {
        // you can use whathever for maxval, since it's not used in CV_THRESH_TOZERO
        Imgproc.threshold(img, img, 0, -1, Imgproc.THRESH_TOZERO);
        Core.extractChannel(img, img, 0);
        int nonzero = Core.countNonZero(img);

        return nonzero;
    }

    // 잘린 이미지 확인
    int numberIs = 0;
    Timer time5 = new Timer();

    private void resetImage() {
        try {
            time5.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (numberIs > 6) {
                        time5.cancel();
                    } else {
                        imgVBack.post(new Runnable() {
                            public void run() {
                                // TODO Auto-generated method stub
                                imgVBack.setImageBitmap(bArea.get(1)[numberIs]);
                                numberIs++;
                            }
                        });
                    }
                }
            }, 1000, 1000);
        } catch (Exception e) {
            System.out.println("resetImage error : " + e);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
    }

    //9. 점자 번역
    public void transe_braille() {
        String sentence, result = "";
        String[] trans_letters = null;

        // Binary to Braille
        TransToBraille tb = new TransToBraille(getApplicationContext(), transed_braille);
        sentence = tb.returnResult();

        if((sentence == null) || (sentence == "")) {
            Toast myToast = Toast.makeText(getApplicationContext(), "점자를 인식하지 못했습니다!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }
        else {
            txtBraille.setText(sentence);
        }

        // 번역
        try {
            TransToHalfHangul trans_b = new TransToHalfHangul(getApplicationContext(), sentence);
            trans_letters = trans_b.returnResult();
        } catch (Exception e) {
            System.out.println("TransToHalfHangul error : " + e);
            Toast myToast = Toast.makeText(getApplicationContext(), "잘못된 점자입니다!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        sentence = "";
        for (int i = 0; i < trans_letters.length; i++) {
            if ((trans_letters[i] != null) && (trans_letters[i] != " ")) {
                sentence += trans_letters[i];
            }
        }

        // 한글 자모 합치기
        try {
            TransToHangul tth = new TransToHangul(sentence);
            result = tth.ReturnResult();
        } catch (Exception e) {
            System.out.println("TransToHangul error : " + e);
            Toast myToast = Toast.makeText(getApplicationContext(), "잘못된 점자입니다!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        }

        if((result == null) || (result == "")) {
            Toast myToast = Toast.makeText(getApplicationContext(), "점자를 인식하지 못했습니다!!", Toast.LENGTH_SHORT);
            myToast.show();
            finish();
        } else {
            txtViewTrans.setText(result);
            tts.speak(result, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TTS 객체 열려있으면 닫기
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}


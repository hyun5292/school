package com.company.fcb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.InputStream;

public class CropImageActivity extends AppCompatActivity {
    private final int PICK_IMAGE = 1111;
    private final int CAPTURE_IMAGE = 2222;
    private AlertDialog alert = null;
    private ImageButton btnCrop = null;
    private ImageButton btnTurnLeft = null;
    private ImageButton btnTurnRight = null;
    public static Bitmap bitmap_crop = null;

    private CropImageView cropImageView = null;
    private Uri mCropImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_view);

        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        btnCrop = (ImageButton) findViewById(R.id.btnCrop);
        btnTurnLeft = (ImageButton) findViewById(R.id.btnTurnLeft);
        btnTurnRight = (ImageButton) findViewById(R.id.btnTurnRight);

        try {
            photoDialogRadio();
        } catch (Exception e) {
            System.out.println("photoDialogRadio error : " + e);
            finish();
        }
        //왼쪽 회전 버튼 클릭 시 이미지 회전
        btnTurnLeft.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                cropImageView.rotateImage(-90);
            }
        });

        //이미지 자르기
        btnCrop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap_crop = cropImageView.getCroppedImage();

                Intent intent = new Intent(getApplicationContext(), TransedImgActivity.class);
                startActivity(intent);
            }
        });

        //오른쪽 회전 버튼 클릭 시 이미지 회전
        btnTurnRight.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                cropImageView.rotateImage(90);
            }
        });
    }

    // 카메라 or 앨범 선택 다이얼로그
    private void photoDialogRadio() {
        try {
            final CharSequence[] PhotoModels = {"직접 찍기", "갤러리"};
            AlertDialog.Builder alt_bid = new AlertDialog.Builder(this);
            alt_bid.setTitle("이미지 가져오기");
            alt_bid.setSingleChoiceItems(PhotoModels, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int item) {
                    if (item == 0) {  // 직접 찍기
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAPTURE_IMAGE);

                    } else {  // 갤러리
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                        intent.setType("image/*");
                        startActivityForResult(intent, PICK_IMAGE);
                    }
                }
            });
            alt_bid.setCancelable(false);
            alert = alt_bid.create();
            alert.show();
        } catch (Exception e) {
            System.out.println("photoDialogRadio error : " + e);
            finish();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            // 갤러리에서 이미지를 골랐을 경우
            try {
                alert.dismiss();

                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);

                img = Bitmap.createScaledBitmap(img, cropImageView.getWidth(), (img.getHeight() * cropImageView.getWidth() / img.getWidth()), true);
                cropImageView.setImageBitmap(img);

                bitmap_crop = cropImageView.getCroppedImage();
                cropImageView.getCroppedImageAsync(cropImageView.getWidth(), cropImageView.getHeight());

                in.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK && data.hasExtra("data")) {
            // 직접 찍었을 경우
            try {
                alert.dismiss();

                Bundle extra = data.getExtras();
                Bitmap bitmap = extra.getParcelable("data");
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                cropImageView.setImageBitmap(bitmap);

                bitmap_crop = cropImageView.getCroppedImage();
                cropImageView.getCroppedImageAsync(cropImageView.getWidth(), cropImageView.getHeight());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            finish();
        }
    }
}
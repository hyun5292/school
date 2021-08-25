package com.company.fcb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private final int PICK_IMAGE = 1111;
    private final int CAPTURE_IMAGE = 2222;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private Activity activity = this;
    private ImageView imgVBack = null;
    private ImageButton btnCamera = null;
    private ImageButton btnTrans = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        imgVBack = (ImageView) findViewById(R.id.imgVBack);
        if (null == imgVBack) {
            Log.e("Error", "Ouh! there is no there is no child view with R.id.imageView ID within my parent view View.");
        }
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnTrans = (ImageButton) findViewById(R.id.btnTrans);

        //카메라 버튼 클릭 시 페이지 이동
        btnCamera.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한이 허용되어 있지 않다면 권한요청
                if (!hasPermissions(getApplicationContext(), PERMISSIONS)) {
                    try {
                        ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                    } catch (Exception e) {
                        System.out.println("권한허용 error : " + e);
                        finish();
                    }
                }
                // 권한이 허용되어 있다면 다음 화면 진행
                else {
                    try {
                        Intent intent = new Intent(getApplicationContext(), CropImageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    } catch (Exception e) {
                        System.out.println("권한허용 error : " + e);
                        finish();
                    }
                }
            }
        });

        //일반 번역 버튼 클릭 시 페이지 이동
        btnTrans.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TransViewActivity.class);
                startActivity(intent);
            }
        });
    }

    // 권한 확인
    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    // 권한 요청
    private void getPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.WAKE_LOCK
                },
                1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (Build.VERSION.SDK_INT >= 23) {

            // requestPermission의 배열의 index가 아래 grantResults index와 매칭
            // 퍼미션이 승인되면
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Log.d(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);

                // TODO : 퍼미션이 승인되는 경우에 대한 코드
                Intent intent = new Intent(getApplicationContext(), CropImageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
            // 퍼미션이 승인 거부되면
            else {
                //Log.d(TAG,"Permission denied");

                // TODO : 퍼미션이 거부되는 경우에 대한 코드
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
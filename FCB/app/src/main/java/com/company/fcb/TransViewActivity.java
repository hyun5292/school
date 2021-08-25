package com.company.fcb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import static android.speech.tts.TextToSpeech.ERROR;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

public class TransViewActivity extends AppCompatActivity {
    private EditText eTextInput;
    private ImageButton btnSearch;
    private TextView txtBraille, txtViewTrans;
    private String sentence = null;  // 받아온 문장
    private char[] letters = null;  // 여러 문장 속 단어 속 글자
    private String[] trans_letters = null;  // 번역 된 글자들
    private String KO_result = null;  // 번역 후 합쳐진 완성본
    private TextToSpeech tts;              // TTS 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_view);

        // 각 컴포넌트 연결
        eTextInput = (EditText) findViewById(R.id.edTextInput);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        txtBraille = (TextView) findViewById(R.id.txtViewBraille);
        txtViewTrans = (TextView) findViewById(R.id.txtViewTrans);

        txtBraille.setMaxHeight(txtBraille.getHeight());
        txtViewTrans.setMaxHeight(txtViewTrans.getHeight());
        txtBraille.setMovementMethod(new ScrollingMovementMethod());
        txtViewTrans.setMovementMethod(new ScrollingMovementMethod());

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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 입력된 점자 & 갖고와서 화면 출력
                sentence = eTextInput.getText().toString();
                sentence.replace("\n", " ");
                txtBraille.setText(sentence);

                // 번역
                try {
                    TransToHalfHangul trans_b = new TransToHalfHangul(getApplicationContext(), sentence);
                    trans_letters = trans_b.returnResult();

                    KO_result = "";
                    for (int i = 0; i < trans_letters.length; i++) {
                        if ((trans_letters[i] != null) && (trans_letters[i] != " ")) {
                            KO_result += trans_letters[i];
                        }
                    }
                } catch (Exception e) {
                    System.out.println("TransToHalfHangul error : " + e);
                    Toast myToast = Toast.makeText(getApplicationContext(), "잘못된 점자입니다!!", Toast.LENGTH_SHORT);
                    myToast.show();
                    finish();
                }

                // 3. 한글 자모 합치기
                try {
                    TransToHangul tth = new TransToHangul(KO_result);
                    KO_result = tth.ReturnResult();
                } catch (Exception e) {
                    System.out.println("TransToHangul error : " + e);
                    Toast myToast = Toast.makeText(getApplicationContext(), "잘못된 점자입니다!!", Toast.LENGTH_SHORT);
                    myToast.show();
                    finish();
                }

                // 4. 출력
                txtViewTrans.setText(KO_result);

                tts.speak(KO_result, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
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
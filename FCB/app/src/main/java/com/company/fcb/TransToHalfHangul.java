package com.company.fcb;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TransToHalfHangul extends AppCompatActivity {
    public String[] jaum = null;
    public String[] doublejaum = null;
    public int[] chosungB = null;
    public int[] jongsungB = null;
    public String[] moum = null;
    public int[] moumB = null;
    public String[] yakja = null;
    public int[] yakjaB = null;
    public int[] excepB = null;
    private String sentence = null;  // 받아온 문장
    private char[] letters = null;  // 여러 문장 속 단어 속 글자
    private String[] trans_letters = null;  // 번역 된 글자들
    private Context mainCxt = null;

    public TransToHalfHangul(Context cxt, String sentence) {
        mainCxt = cxt;
        this.sentence = sentence;

        // 한 글자씩 배열에 분리
        letters = sentence.toCharArray();
        trans_letters = new String[sentence.length()];

        // 초성, 종성, 모음, 약어 파일 가져오기
        jaum = mainCxt.getResources().getStringArray(R.array.jaum);
        doublejaum = mainCxt.getResources().getStringArray(R.array.doublejaum);
        chosungB = mainCxt.getResources().getIntArray(R.array.chosungB);
        jongsungB = mainCxt.getResources().getIntArray(R.array.jongsungB);
        moum = mainCxt.getResources().getStringArray(R.array.moum);
        moumB = mainCxt.getResources().getIntArray(R.array.moumB);
        yakja = mainCxt.getResources().getStringArray(R.array.yakja);
        yakjaB = mainCxt.getResources().getIntArray(R.array.yakjaB);
        excepB = mainCxt.getResources().getIntArray(R.array.excepB);

        // 2. 일치하는 거 찾아서 배열에 적립(?)(약자, 초성 된소리, 초성, 모음, 종성 된소리, 종성 순)
        Except();  // 예외
        //System.out.println("Except - " + ex());
        DoYakja1();  // 약자(그래서, 그러나, 그러면, 그러므로, 그런데, 그리고) 비교
        //System.out.println("DoYakja1 - " + ex());
        Dochosung();  // 초성 된소리, 초성 비교
        //System.out.println("Dochosung - " + ex());
        DoYakja2();  // 약자(억, 언, 얼, 연, 열, 영, 옥, 온, 옹, 운, 울, 은, 을, 인) 비교
        //System.out.println("DoYakja2 - " + ex());
        DoMoum();  // 모음 비교
        //System.out.println("DoMoum - " + ex());
        DoYakjaEx();  // 약자(가 ~ 하) 비교
        //System.out.println("DoYakjaEx - " + ex());
        DoPlusA();  // 뒤에 모음이 없는 초성 'ㅏ' 붙여주기
        //System.out.println("DoPlusA - " + ex());
        ChangeDoubleJongsung();  //종성(ㄳ, ㄵ, ㄶ, ㄺ, ㄻ, ㄼ, ㄽ, ㄾ, ㄿ, ㅀ, ㅄ) 바꾸기
        //System.out.println("ChangeDoubleJongsung - " + ex());
        Dojongsung();  // 종성 비교
        //System.out.println("Dojongsung - " + ex());
    }

    public String[] returnResult() {
        return trans_letters;
    }

    public String ex() {
        String KO_result = "";
        for (int i = 0; i < trans_letters.length; i++) {
            if ((trans_letters[i] != null) && (trans_letters[i] != "")) {
                KO_result += trans_letters[i] + ",";
            } else {
                KO_result += " ,";
            }
        }
        return KO_result;
    }

    // 예외
    //1. 'ㅘ' 나 'ㅜ' 다음에 'ㅐ' 가올 때는 사이에 붙임표(3-6)를 합니다.
    //2. 모음 'ㅖ'(3-4)는 받침 'ㅆ'(3-4)과 동일하므로 혼동을 일으킬 수 있는
    // 낱말 사이에 붙임표(3-6점)를 합니다.('ㅘ'나 'ㅜ' 다음에 '애'가 올 경우도 마찬가지)
    // => 모음 'ㅖ'는 앞에 모음이 오면 붙임표(3-6점)를 쓴다.
    //3. 'ㄲ'과 'ㅆ' 이 받침으로 쓰일 때는 된소리 기호 6점을 쓰지 않고
    // 초성 'ㄱ' 과 종성 'ㅅ'을 두 번 쓴다.
    // 4. '것'
    public void Except() {
        // 1.
        for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
            if (letters[i] == (char) moumB[10]) {  //'ㅐ' 일 경우
                if (letters[i - 1] == (char) excepB[0]) {  // 앞에 '⠤'가 온 경우
                    trans_letters[i - 1] = "";  // 앞 빈 글자 지우기
                    trans_letters[i] = moum[10];
                }
            }
        }

        // 2.
        for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
            if ((i - 1) > 0) {
                if (letters[i - 1] == (char) excepB[0]) {  // 앞에 '⠤'가 온 경우 "ㅖ"
                    if (letters[i] == (char) moumB[16]) {  //'⠌' 일 경우
                        trans_letters[i] = "";  // 뒤 빈 글자 지우기
                        trans_letters[i - 1] = "ㅇㅖ";
                    } else if (letters[i] == moumB[10]) {  //'애' 일 경우
                        trans_letters[i] = "";  // 뒤 빈 글자 지우기
                        trans_letters[i - 1] = "ㅇㅐ";
                    }
                }
            }
        }

        // 3.
        for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
            if (letters[i] == (char) chosungB[0]) {  // 'ㄱ' 일 경우
                if (i + 1 < letters.length) {
                    if (letters[i + 1] == (char) chosungB[0]) {  // 'ㄲ' 일 경우
                        trans_letters[i] = "";  // 앞 빈 글자 지우기
                        trans_letters[i + 1] = doublejaum[0];
                    }
                }
            } else if (letters[i] == (char) jongsungB[6]) {  // 'ㅅ' 일 경우
                if (i + 1 < letters.length) {
                    if (letters[i + 1] == (char) jongsungB[6]) {  // 'ㅆ' 일 경우
                        trans_letters[i] = "";  // 앞 빈 글자 지우기
                        trans_letters[i + 1] = doublejaum[6];
                    }
                }
            }
        }

        // 4.
        for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
            if (i + 1 < letters.length) {
                if (letters[i] == (char) excepB[1]) {  // 약자 '것'의 첫 번째 일 경우
                    if (letters[i + 1] == (char) excepB[2]) {  // 약자 '것'의 두 번째 일 경우
                        trans_letters[i] = "";  // 앞 빈 글자 지우기
                        trans_letters[i + 1] = "ㄱㅓㅅ";
                    }
                }
            }
        }
    }

    // 약자(그래서, 그러나, 그러면, 그러므로, 그런데, 그리고)
    public void DoYakja1() {
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (letters[i] == (char) yakjaB[25]) {  // 해당하는 경우
                    for (int j = 0; j < 6; j++) {
                        if (letters[i + 1] == (char) yakjaB[26 + j]) {  // 뒷글자로 맞는 약자 찾기
                            if (i + 1 < letters.length) {
                                trans_letters[i] = yakja[26 + j];
                                letters[i + 1] = ' ';  // 빈 뒷글자 지우기
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("약자1 : " + e);
        }
    }

    // 약자(억, 언, 얼, 연, 열, 영, 옥, 온, 옹, 운, 울, 은, 을, 인)
    public void DoYakja2() {
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 두글자 점자 약자가 아닌 경우
                    for (int j = 11; j < yakjaB.length - 7; j++) {  // 점자 약자 순환
                        if (letters[i] == (char) yakjaB[j]) {  // 입력된 점자랑 일치하는 약자 찾기
                            boolean result = ChkFrontJaum(i);
                            if (result) {  // 앞에 자음이 없는 경우
                                trans_letters[i] = jaum[7] + String.valueOf(yakja[j]);
                            } else {  // 앞에 자음이 있는 경우
                                trans_letters[i] = String.valueOf(yakja[j].charAt(0)) + String.valueOf(yakja[j].charAt(1));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("약자2 : " + e);
        }
    }

    // 약자(가, 나, 다, 라, 마, 바, 사, 자, 카, 타, 파, 하)
    public void DoYakjaEx() {
        // ㅆ 받침 구별
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 된소리가 아닌 경우
                    if (letters[i] == (char) jongsungB[14]) {  // 입력된 점자랑 일치하는 종성 찾기
                        boolean chk_f = ChkFrontJaum(i);
                        boolean chk_b = ChkBackJaum(i);

                        if(!chk_f && !chk_b) {
                            trans_letters[i] = "ㅖ";
                        } else {
                            trans_letters[i] = "ㅆ";
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("종성ㅆ : " + e);
        }

        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 두글자 점자 약자가 아닌 경우
                    for (int j = 0; j < 11; j++) {  // 점자 약자 순환
                        if (letters[i] == (char) yakjaB[j]) {  // 입력된 점자랑 일치하는 약자 찾기
                            boolean result = ChkBehindMoum(i);
                            if (result) {  // 뒤에 모음이 없을 경우
                                trans_letters[i] = String.valueOf(yakja[j].charAt(0)) + String.valueOf(yakja[j].charAt(1));
                            } else {  // 뒤에 모음이 있을 경우
                                if (j >= 3 && j <= 6) {  // 가 ~ 다
                                    trans_letters[i] = String.valueOf(jaum[j + 1].charAt(0));
                                } else if (j >= 7) {  // 마 ~ 자
                                    trans_letters[i] = String.valueOf(jaum[j + 2].charAt(0));
                                } else {  // 카 ~ 하
                                    trans_letters[i] = String.valueOf(jaum[j].charAt(0));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("약자3 : " + e);
        }
    }

    // 초성
    public void Dochosung() {
        // 초성 된소리
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (letters[i] == (char) chosungB[6]) {  // 해당하는 경우
                    for (int j = 0; j < chosungB.length; j++) {
                        if (i + 1 < letters.length) {
                            if (letters[i + 1] == (char) chosungB[j]) { // 입력된 점자랑 일치하는 초성 찾기
                                trans_letters[i] = " ";  // 빈 뒷글자 지우기
                                trans_letters[i + 1] = doublejaum[j];
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // 초성
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 된소리가 아닌 경우
                    for (int j = 0; j < chosungB.length; j++) {  // 점자 초성 순환
                        if (letters[i] == (char) chosungB[j]) {  // 입력된 점자랑 일치하는 초성 찾기
                            if (j >= 7) { // 초성 점자는 'ㅇ'이 없기 때문에
                                trans_letters[i] = jaum[j + 1];
                            } else {
                                trans_letters[i] = jaum[j];
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("초성 : " + e);
        }
    }

    // 모음
    public void DoMoum() {
        //모음(ㅟ, ㅒ, ㅙ, ㅞ)
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (letters[i] == (char) moumB[17]) {  // 해당하는 경우
                    for (int j = 0; j < 4; j++) {
                        if (letters[i - 1] == (char) moumB[18 + j]) {  // 앞글자로 맞는 모음 찾기
                            boolean result = ChkFrontJaum(i);
                            if (result) {  // 앞에 자음이 없는 경우
                                trans_letters[i - 1] = moum[18 + j];
                            } else {  // 앞에 자음이 있는 경우
                                trans_letters[i - 1] = String.valueOf(moum[18 + j].charAt(1));
                                trans_letters[i - 1] = moum[18 + j];
                            }
                            letters[i] = ' ';  // 빈 글자 지우기
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("모음1 : " + e);
        }

        //모음(ㅏ, ㅑ, ㅓ, ㅕ, ㅗ, ㅛ, ㅜ, ㅠ, ㅡ, ㅣ, ㅐ, ㅔ, ㅚ, ㅘ,  ㅝ,  ㅢ)
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 두글자 점자 모음이 아닌 경우
                    for (int j = 0; j < moumB.length - 6; j++) {  // 점자 모음 순환
                        if (letters[i] == (char) moumB[j]) { // 입력된 점자랑 일치하는 초성 찾기
                            boolean result = ChkFrontJaum(i);
                            if (result) {  // 앞에 자음이 있는 경우
                                trans_letters[i] = moum[j];
                            } else {  // 앞에 자음이 없는 경우
                                trans_letters[i] = String.valueOf(moum[j].charAt(1));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("모음2 : " + e);
        }
    }

    // 종성
    public void Dojongsung() {
        try {
            for (int i = 0; i < letters.length; i++) {  // 번역하고자 하는 letters 순환
                if (trans_letters[i] == null) {  // 된소리가 아닌 경우
                    for (int j = 0; j < jongsungB.length - 1; j++) {  // 점자 종성 순환
                        if (letters[i] == (char) jongsungB[j]) {  // 입력된 점자랑 일치하는 종성 찾기
                            trans_letters[i] = jaum[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("종성1 : " + e);
        }
    }

    // 종성(ㄳ, ㄵ, ㄶ, ㄺ, ㄻ, ㄼ, ㄽ, ㄾ, ㄿ, ㅀ, ㅄ) 바꾸기
    public void ChangeDoubleJongsung() {
        try {
            for (int i = 0; i < letters.length; i++) {
                if ((letters[i] == (char) jongsungB[0]) && (letters[i + 1] == (char) jongsungB[6])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄳ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[1]) && (letters[i + 1] == (char) jongsungB[8])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄵ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[1]) && (letters[i + 1] == (char) jongsungB[13])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄶ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[0])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄺ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[4])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄻ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[5])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄼ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[6])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄽ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[11])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄾ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[12])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㄿ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[3]) && (letters[i + 1] == (char) jongsungB[13])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㅀ";
                    trans_letters[i + 1] = " ";
                } else if ((letters[i] == (char) jongsungB[5]) && (letters[i + 1] == (char) jongsungB[6])) {  // 'ㄱ' 일 경우
                    trans_letters[i] = "ㅄ";
                    trans_letters[i + 1] = " ";
                }
            }
        } catch (Exception e) {
            System.out.println("종성2 : " + e);
        }
    }

    // 뒤에 모음이 오는지
    public boolean ChkBehindMoum(int num) {
        boolean result;
        int whe = -1;

        try {
            // 약자일 경우
            for (int i = 11; i < yakja.length; i++) {
                if (yakja[i].equals(trans_letters[num + 1])) {
                    whe = 1;    // 있어
                    break;
                } else {
                    whe = -1;   // 없어
                }
            }

            if (whe == -1) {
                for (int i = 0; i < moum.length; i++) {
                    if (moum[i].equals("ㅇ" + trans_letters[num + 1])) {
                        whe = 1;    // 있어
                        break;
                    } else {
                        whe = -1;   // 없어
                    }
                }
            }
        } catch (Exception e) {

        }

        if (whe < 0) {  // 없음
            result = true;
        } else {  // 있음
            result = false;
        }

        return result;
    }

    // 앞에 자음이 오는지
    public boolean ChkFrontJaum(int num) {
        boolean result = false;
        String[] djaum = {"ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
        int whe = -1;

        try {
            whe = Arrays.binarySearch(jaum, trans_letters[num - 1]);
            if (whe == -1) {
                whe = Arrays.binarySearch(djaum, trans_letters[num - 1]);
            }
        } catch (Exception e) {

        }

        if (whe < 0) {  // 없음
            result = true;
        } else {  // 있음
            result = false;
        }

        return result;
    }

    // 뒤에 자음이 오는지
    public boolean ChkBackJaum(int num) {
        boolean result = false;
        String[] djaum = {"ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
        int whe = -1;

        try {
            whe = Arrays.binarySearch(jaum, trans_letters[num + 1]);
            if (whe < 0) {
                whe = Arrays.binarySearch(djaum, trans_letters[num + 1]);
            }
        } catch (Exception e) {

        }

        if (whe < 0) {  // 없음
            result = true;
        } else {  // 있음
            result = false;
        }

        return result;
    }

    // 뒤에 모음이 없는 초성 'ㅏ' 붙여주기
    public void DoPlusA() {
        boolean result_back_moum = false;
        boolean result_jaum = false;
        int rs_jaum = -1;

        try {
            for (int i = 0; i < trans_letters.length; i++) {
                result_back_moum = ChkBehindMoum(i);  // 뒤에 모음이 있는지
                if (trans_letters[i] != null) {
                    // 현재 위치가 자음인지
                    rs_jaum = Arrays.binarySearch(jaum, trans_letters[i]);
                }
                if (rs_jaum < 0) {  // 자음이 아닌 경우
                    result_jaum = false;
                } else {  // 자음인 경우
                    result_jaum = true;
                }

                if (result_back_moum && (trans_letters[i] != null)) {  // 없으면
                    if ((trans_letters[i].length() == 1) && result_jaum) {
                        trans_letters[i] += 'ㅏ';
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("/'ㅏ/' 붙이기 : " + e);
        }
    }
}

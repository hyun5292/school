package com.company.fcb;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TransToHangul {
    private String KO_result;
    private String result_hangul = "";
    private ArrayList<char[]> resultOne = new ArrayList<>();
    private char[] charBraille;

    String[] chosungS = new String[]{"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    String[] joongsungS = new String[]{"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    String[] jongsungS = new String[]{" ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    ArrayList<String> al_cho = new ArrayList<>(Arrays.asList(chosungS));
    ArrayList<String> al_jung = new ArrayList<>(Arrays.asList(joongsungS));
    ArrayList<String> al_jong = new ArrayList<>(Arrays.asList(jongsungS));


    public TransToHangul(String KO_result) {
        this.KO_result = KO_result;
        charBraille = this.KO_result.toCharArray();

        DivideOne();  // 한글자(초성+중성+종성) 씩 나누기
        ConCatHangul();  // 한글 자모 합치기
    }

    // 결과 반환
    public String ReturnResult() {
        return result_hangul;
    }

    // 한글자(초성+중성+종성) 씩 나누기
    public void DivideOne() {
        ArrayList<Integer> ptJoong = new ArrayList<>();

        try {
            // 모음이 있을 경우 '초성+중성'으로 저장 후 " " 초기화
            for (int i = 1; i < charBraille.length; i++) {
                for (int j = 0; j < joongsungS.length; j++) {
                    if (joongsungS[j].equals(String.valueOf(charBraille[i]))) {
                        // 있어
                        char[] newRslt = new char[3];
                        newRslt[0] = charBraille[i - 1];
                        newRslt[1] = charBraille[i];
                        charBraille[i] = ' ';
                        ptJoong.add(i);
                        newRslt[2] = ' ';
                        resultOne.add(newRslt);
                        break;
                    }
                }
            }

            // 모음이 있을 경우 '초성+중성'으로 저장 후 " " 초기화
            for (int i = 0; i < ptJoong.size(); i++) {
                int jong = (ptJoong.get(i) + 1);
                int next = (ptJoong.get(i) + 2);
                if ((next < charBraille.length)) {
                    if ((charBraille[jong] != ' ') && (charBraille[next] != ' ')) {
                        resultOne.get(i)[2] = charBraille[jong];
                    }
                } else if (jong == (charBraille.length - 1) && (ptJoong.get(i) != (charBraille.length - 1))) {    // 맨 끝
                    if ((charBraille[jong] != ' ')) {
                        resultOne.get(i)[2] = charBraille[jong];
                    }
                }
            }
/*
            for(int i = 0; i < resultOne.size(); i++) {
                System.out.println(String.valueOf(resultOne.get(i)[0]) + String.valueOf(resultOne.get(i)[1])+String.valueOf(resultOne.get(i)[2]));
            }
 */
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 한글 자모 합치기
    public void ConCatHangul() {
        int n_1;
        int n_2;
        int n_3 = 0;
        int result_num;

        result_hangul = "";
        for (int i = 0; i < resultOne.size(); i++) {
            n_1 = al_cho.indexOf(String.valueOf(resultOne.get(i)[0]));  // 초성
            n_2 = al_jung.indexOf(String.valueOf(resultOne.get(i)[1]));  // 중성
            n_3 = al_jong.indexOf(String.valueOf(resultOne.get(i)[2]));

            result_num = 44032 + (n_1 * 588) + (n_2 * 28) + n_3;

            result_hangul += (char) result_num;
        }
    }
}

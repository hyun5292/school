package com.company.fcb;

import android.content.Context;
import android.widget.Toast;

import java.util.Arrays;

import static android.net.wifi.WifiConfiguration.Status.strings;

public class TransToBraille {
    public int[] chosungTB = null;
    public int[] jongsungTB = null;
    public int[] moumTB = null;
    public int[] yakjaTB = null;
    public int[] excepTB = null;
    public int[] chosungB = null;
    public int[] jongsungB = null;
    public int[] moumB = null;
    public int[] yakjaB = null;
    public int[] excepB = null;

    private int[] getbraille = null;
    private int[] transed_braille = null;
    private String Bsentence = "";
    private Context mainCxt = null;

    public TransToBraille(Context cxt, int[] gb) {
        try {
            mainCxt = cxt;
            getbraille = gb;
            transed_braille = new int[getbraille.length];

            // 초성, 종성, 모음, 약어 파일 가져오기
            chosungTB = mainCxt.getResources().getIntArray(R.array.chosungTB);
            jongsungTB = mainCxt.getResources().getIntArray(R.array.jongsungTB);
            moumTB = mainCxt.getResources().getIntArray(R.array.moumTB);
            yakjaTB = mainCxt.getResources().getIntArray(R.array.yakjaTB);
            excepTB = mainCxt.getResources().getIntArray(R.array.excepTB);
            chosungB = mainCxt.getResources().getIntArray(R.array.chosungB);
            jongsungB = mainCxt.getResources().getIntArray(R.array.jongsungB);
            moumB = mainCxt.getResources().getIntArray(R.array.moumB);
            yakjaB = mainCxt.getResources().getIntArray(R.array.yakjaB);
            excepB = mainCxt.getResources().getIntArray(R.array.excepB);

            FindExcep();  // 예외
            FindYakja();  // 약자 먼저 찾기
            FindChosung();  // 초성 찾기
            FindMoum();  // 모음 찾기
            Findjongsung();  // 종성 찾기
        } catch (Exception e) {
            System.out.println("TransToBraille error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    // 반환
    public String returnResult() {
        String result = "";

        try {
            if (transed_braille != null) {
                for (int i = 0; i < transed_braille.length; i++) {
                    if ((transed_braille[i] != ' ') && (transed_braille[i] != 0)) {
                        result += (char) transed_braille[i];
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("returnResult error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }

        return result;
    }

    // 해당하는 점자 찾기
    // 예외 찾기
    public void FindExcep() {
        try {
            if (getbraille != null) {
                for (int i = 0; i < getbraille.length; i++) {
                    for (int j = 0; j < excepTB.length; j++) {
                        if (getbraille[i] == excepTB[j]) {
                            transed_braille[i] = excepB[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("FindExcep error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    // 약자 찾기
    public void FindYakja() {
        try {
            if (getbraille != null) {
                for (int i = 0; i < getbraille.length; i++) {
                    for (int j = 0; j < yakjaTB.length; j++) {
                        if (getbraille[i] == yakjaTB[j]) {
                            transed_braille[i] = yakjaB[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("FindYakja error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    // 초성 찾기
    public void FindChosung() {
        try {
            if (getbraille != null) {
                for (int i = 0; i < getbraille.length; i++) {
                    for (int j = 0; j < chosungTB.length; j++) {
                        if (getbraille[i] == chosungTB[j]) {
                            transed_braille[i] = chosungB[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("FindChosung error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    // 모음 찾기
    public void FindMoum() {
        try {
            if (getbraille != null) {
                for (int i = 0; i < getbraille.length; i++) {
                    for (int j = 0; j < moumTB.length; j++) {
                        if (getbraille[i] == moumTB[j]) {
                            transed_braille[i] = moumB[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("FindMoum error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    // 종성 찾기
    public void Findjongsung() {
        try {
            if (getbraille != null) {
                for (int i = 0; i < getbraille.length; i++) {
                    for (int j = 0; j < jongsungTB.length; j++) {
                        if (getbraille[i] == jongsungTB[j]) {
                            transed_braille[i] = jongsungB[j];
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Findjongsung error : " + e);
            Toast myToast = Toast.makeText(mainCxt, "영역을 다시 설정하거나 다른 사진을 선택해주세요!!", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }
}

package P_2024_01;

import java.io.*;
import java.util.*;

class 파일명_정렬 {
    String[][] arr;
    public String[] solution(String[] files) {
        arr = new String[files.length][4]; // HEAD, NUMBER, TAIL, count
        // 분류
        for(int i=0; i<files.length; i++) {
            String file = files[i];
            String HEAD="", NUMBER="", TAIL="";
            int j=0;
            int hidx=-1;
            // HEAD
            for(j=0; j<file.length(); j++) {
                if(isNumber(file.charAt(j))) {
                    HEAD = file.substring(0, j);
                    hidx = j;
                    break;
                }
            }
            // System.out.println("file.length():"+file.length());
            // NUMBER
            for( ; j<file.length(); j++) {
                // System.out.println("j"+j);
                if(!isNumber(file.charAt(j))) {
                    NUMBER = file.substring(hidx, j);
                    // System.out.println("NUMBER>>"+NUMBER);
                    break;
                }
            }
            if(NUMBER.equals("")) {
                NUMBER = file.substring(hidx, file.length());
                TAIL="";
            }
            // TAIL
            if(j>=file.length()) TAIL="";
            else TAIL = file.substring(j, file.length());

            // 대소문자 구분 하지 않음
            HEAD = HEAD.toLowerCase();

            arr[i][0] = HEAD;
            arr[i][1] = NUMBER;
            arr[i][2] = TAIL;
            arr[i][3] = String.valueOf(i);

            // System.out.println(NUMBER);
        }

        // Sort
        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                if(!s1[0].equals(s2[0])) {
                    return s1[0].compareTo(s2[0]);
                } else {
                    if(!s1[1].equals(s2[1])) {
                        return Integer.parseInt(s1[1])-Integer.parseInt(s2[1]);
                    } else {
                        return Integer.parseInt(s1[3])-Integer.parseInt(s2[3]);
                    }
                }
            }
        });

        String[] rst = new String[arr.length];
        for(int i=0; i<arr.length; i++) {
            int n = Integer.parseInt(arr[i][3]);
            rst[i] = files[n];
        }
        return rst;
    }

    private boolean isNumber(char ch) {
        if('0'<=ch && ch<='9') return true;
        else return false;
    }
}
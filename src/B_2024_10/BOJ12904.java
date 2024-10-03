package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// search) T->S로 변환 (끝문자만 보기)
// WR1) stringbuilder 비교시 equals가 아니라 compareto 사용
public class BOJ12904 {
    static StringBuilder S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());

        while(S.length()!=T.length()) {
            if(T.charAt(T.length()-1)=='A') {
                deleteLastChar(T);
            } else {
                deleteLastCharAndReverse(T);
            }
        }

        System.out.println((S.compareTo(T) == 0) ?1:0);
    }

    private static void deleteLastChar(StringBuilder sb) {
        sb.deleteCharAt(sb.length()-1);
    }

    private static void deleteLastCharAndReverse(StringBuilder sb) {
        sb.deleteCharAt(sb.length()-1);
        sb.reverse();
    }
}

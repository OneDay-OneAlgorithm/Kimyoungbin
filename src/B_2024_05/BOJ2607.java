package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2607 {
    static int N, rst = 0;
    static int[] standard = new int[26];
    static int[] cur;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[] std = br.readLine().toCharArray();
        for(int i=0; i<std.length; i++) {
            standard[std[i]-'A']++;
        }

        for(int i=0; i<N-1; i++) {
            cur = new int[26];
            char[] next = br.readLine().toCharArray();
            for(int j=0; j<next.length; j++) {
                cur[next[j]-'A']++;
            }
            if(checkEqual()) rst++;
        }

        System.out.println(rst);
    }

    private static boolean checkEqual() {
        boolean plusFlag = false;
        boolean minusFlag = false;

        // 두 배열값의 차이가 1인경우, -1인경우 각각 한번까지 용인
        for(int i=0; i<26; i++) {
            int check = standard[i]-cur[i];
            if(check<=-2 || check>=2) return false;
            if((check==1 && plusFlag) || (check==-1 && minusFlag)) return false;
            if(check==1) plusFlag = true;
            if(check==-1) minusFlag = true;
        }

        return true;
    }
}
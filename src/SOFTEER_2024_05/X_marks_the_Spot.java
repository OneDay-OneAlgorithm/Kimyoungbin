package SOFTEER_2024_05;

import java.io.*;

public class X_marks_the_Spot {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            String s1 = line_N[0];
            String s2 = line_N[1];
            for(int j=0; j<s1.length(); j++) {
                if(s1.charAt(j)=='x'||s1.charAt(j)=='X') {
                    char target = s2.charAt(j);
                    if('a'<=target && target<='z') {
                        target += 'A'-'a';
                    }
                    sb.append(target);
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }
}

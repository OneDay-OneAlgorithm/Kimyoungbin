package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ22233 {
    static int N, M;
    static HashSet<String> memo = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        for(int i=0; i<N; i++) {
            memo.add(br.readLine());
        }
        for(int i=0; i<M; i++) {
            String[] keywords = br.readLine().split(",");
            for(int j=0; j<keywords.length; j++) {
                memo.remove(keywords[j]);
            }
            System.out.println(memo.size());
        }
    }
}
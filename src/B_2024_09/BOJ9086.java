package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9086 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            char[] input = br.readLine().toCharArray();
            sb.append(input[0]).append(input[input.length-1]).append("\n");
        }
        System.out.println(sb);
    }
}

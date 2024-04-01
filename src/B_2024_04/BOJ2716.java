package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 깊이만 알면 최소 원숭이수를 알 수 있지 않나
public class BOJ2716 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            int depth = 0, maxDepth = 0;
            char[] input = br.readLine().toCharArray();
            for(int i=0; i<input.length; i++) {
                if(input[i]=='[') {
                    depth++;
                } else {
                    depth--;
                }
                maxDepth = Math.max(maxDepth, depth);
            }
            System.out.println((int) Math.pow(2, maxDepth));
        }
    }
}
package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ28113 {
    static int N,A,S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //지하철까지
        A = Integer.parseInt(st.nextToken()); //버스
        S = Integer.parseInt(st.nextToken()); //지하철

        System.out.println(A==S?"Anything":(A>S)?"Subway":"Bus");
    }
}
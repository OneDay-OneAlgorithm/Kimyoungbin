package B_2024_07;

import java.io.*;

public class BOJ16394 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        bw.write(N-1946+"");
        bw.flush();
        bw.close();
    }
}
package B_2024_07;

import java.io.*;

public class BOJ2741 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++) {
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();
    }
}
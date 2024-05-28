package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N^3=125_000_000이라 floyd는 TLE 아닌가?
// WA1) 등호 추가
public class BOJ11265 {
    static int N, M;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        dist = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                dist[i][j] = Integer.parseInt(line_N[j-1]);
            }
        }

        // floyd
        for(int k=1; k<=N; k++) { // 경유점
            for(int i=1; i<=N; i++) { // 시작점
                for(int j=1; j<=N; j++) { // 도착점
                    dist[i][j] = Math.min(dist[i][k]+dist[k][j], dist[i][j]);
                }
            }
        }

        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int A = Integer.parseInt(line_M[0]);
            int B = Integer.parseInt(line_M[1]);
            int C = Integer.parseInt(line_M[2]);
            System.out.println(dist[A][B]<=C?"Enjoy other party":"Stay here"); // WA1
        }
    }
}

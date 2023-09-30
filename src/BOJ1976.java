import java.io.*;
import java.util.*;

public class BOJ1976
{
    static int N, M;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;
    static String rst = "YES";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N][N];

        //초기화
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                dist[i][j] = INF;
                if(i==j) dist[i][j] = 0; //자기 자신까지의 거리는 0
            }
        }
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                int d = Integer.parseInt(line_N[j]);
                if(d!=0) dist[i][j] = d;
            }
        }

        //경유점
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
                    dist[k][j] = dist[j][k]; //방향성이 없으므로
                }
            }
        }

        String[] line_T = br.readLine().split(" ");
        for(int i=0; i<M-1; i++) {
            int start = Integer.parseInt(line_T[i])-1;
            int end = Integer.parseInt(line_T[i+1])-1;

            if(dist[start][end]==INF) {
                rst = "NO";
            }
        }
        System.out.println(rst);
    }
}

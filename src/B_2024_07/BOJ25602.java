package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ25602 {
    static int N, K;
    static int[] A;
    static int[][] R;
    static int[][] M;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        R = new int[K][N];
        M = new int[K][N];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int day, int sum) {
        if(day==K) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<N; i++) { // 랑이
            for(int j=0; j<N; j++) { // 메리
                A[i]--;
                A[j]--;
                if(A[i]<0 || A[j]<0) {
                    A[i]++;
                    A[j]++;
                    continue;
                }

                dfs(day+1, sum+ R[day][i]+ M[day][j]);
                A[i]++;
                A[j]++;
            }
        }
    }
}
package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// search) greedy
// WA1) 범위 잘못생각
public class BOJ1080 {
    static int N, M;
    static int[][] A, B;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        A = new int[N][M];
        B = new int[N][M];

        //A
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(line_N[j]);
            }
        }
        //B
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                B[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        for(int i=0; i<=N-3; i++) {
            for(int j=0; j<=M-3; j++) {
                if(A[i][j]!=B[i][j]) {
                    change(i, j);
                    rst++;
                }
//                printRst();
            }
        }
        // 모두 같은지 화인
        // 입력범위 실수 조심
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(A[i][j]!=B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(rst);
    }

    // 입력범위 실수 조심
    private static void change(int y, int x) {
        for(int i=y; i<y+3; i++) {
            for(int j=x; j<x+3; j++) {
                if(i<N && j<M) {
                    A[i][j] = A[i][j]==0?1:0;
                }
            }
        }
    }

    private static void printRst() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(A[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
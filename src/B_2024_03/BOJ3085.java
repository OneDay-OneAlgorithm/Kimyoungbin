package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085 {
    static int N;
    static char[][] board;
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for(int i=0; i<N; i++) {
            String line_N = br.readLine();
            for(int j=0; j<N; j++) {
                board[i][j] = line_N.charAt(j);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                swap1(i, j);
                rst = Math.max(rst, checkMaxLen());
                swap1(i, j); // 원상복구
                swap2(j, i);
                rst = Math.max(rst, checkMaxLen());
                swap2(j, i);
            }
        }

        System.out.println(rst);
    }

    private static void swap1(int a, int b) {
        char tmp = board[a][b];
        board[a][b] = board[a][b+1];
        board[a][b+1] = tmp;
    }

    private static void swap2(int a, int b) {
        char tmp = board[a][b];
        board[a][b] = board[a+1][b];
        board[a+1][b] = tmp;
    }


    private static int checkMaxLen() {
        int min = Integer.MIN_VALUE;
        int 가로최대, 세로최대;
        for(int i=0; i<N; i++) {
            // 가로길이
            가로최대 = 1;
            for(int j=0; j<N-1; j++) {
                if(board[i][j]==board[i][j+1]) {
                    min = Math.max(min, ++가로최대);
                }
                else 가로최대 = 1;
            }
            // 세로길이
            세로최대 = 1;
            for(int j=0; j<N-1; j++) {
                if(board[j][i]==board[j+1][i]) {
                    min = Math.max(min, ++세로최대);
                }
                else 세로최대 = 1;
            }
        }
        return min;
    }
}
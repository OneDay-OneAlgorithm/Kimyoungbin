package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 유망검사 백트래킹?
// WA1) 1x1 1일떄, 1x1 0일떄 모두 처리 안됨
// TLE) 왜?
public class BOJ1799 {
    static int N;
    static int[][] board; // 0: 불가, 1: 가능, 2: 확정
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        dfs(0, 0, 0);

        System.out.println(rst);
    }

    private static void dfs(int x, int y, int count) {
        rst = Math.max(rst, count);
        if(x==N-1 && y==N-1) {
            if(board[N-1][N-1]==1 && isPromising(N-1, N-1)) {
                rst = Math.max(rst, count+1);
            }
            return;
        }

        int nx, ny;
        if(x==N-1) {
            nx = 0;
            ny = y+1;
        } else {
            nx = x+1;
            ny = y;
        }
        while(ny<N) {
            while(nx<N) {
                if(board[ny][nx]==1 && isPromising(nx, ny)) {
                    board[ny][nx] = 2;
                    dfs(nx, ny, count+1);
                    board[ny][nx] = 1;
                    dfs(nx, ny, count);
                }
                nx++;
            }
            nx = 0;
            ny++;
        }
    }

    private static boolean isPromising(int x, int y) {
        int nx=x-1, ny=y-1;
        while(nx>=0 && ny>=0) {
            if(board[ny][nx]==2) {
                return false;
            }
            nx--;
            ny--;
        }

        nx=x+1; ny=y+1;
        while(nx<N && ny<N) {
            if(board[ny][nx]==2) {
                return false;
            }
            nx++;
            ny++;
        }

        nx=x+1; ny=y-1;
        while(0<=nx && nx<N && 0<=ny && ny<N) {
            if(board[ny][nx]==2) {
                return false;
            }
            nx++;
            ny--;
        }

        nx=x-1; ny=y+1;
        while(0<=nx && nx<N && 0<=ny && ny<N) {
            if(board[ny][nx]==2) {
                return false;
            }
            nx--;
            ny++;
        }

        return true;
    }
}
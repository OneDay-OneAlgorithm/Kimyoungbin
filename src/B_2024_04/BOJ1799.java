package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 유망검사 백트래킹?
// WA1) 1x1 1일떄, 1x1 0일떄 모두 처리 안됨
// TLE) 기존 방법으로는 유망검사를 해도 N=100이라 dfs가 안되는 것으로 보임.
// search) 대각선만 신경쓰면 되므로 10x10 dfs대신 5x5 dfs 2번으로 나누어 탐색
// refactor) 기존 로직은 분기처리가 너무 많아서 개선 필요
public class BOJ1799 {
    static int N;
    static int[][] board; // 0: 불가, 1: 가능, 2: 확정
    static int rst, tmp;
    static int ans = 0;
    static final int T1 = 0, T2 = 1; // T1: xy합 짝수, T2: xy합 홀수
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


        // T1 dfs - 0,0에 말을 놓지 않는 경우
        rst = Integer.MIN_VALUE;
        dfs(0, 0, 0, T1);
        tmp = rst;
        if(board[0][0]==1) {
            rst = Integer.MIN_VALUE;
            board[0][0] = 2;
            dfs(0, 0, 1, T1);
        }
        ans += Math.max(tmp, rst);

        // T2 dfs
        if(N>1) { // N==1인 경우는 한가지만 가능
            rst = Integer.MIN_VALUE;
            dfs(1, 0, 0, T2);
            tmp = rst;
            if (board[0][1] == 1) {
                rst = Integer.MIN_VALUE;
                board[0][1] = 2;
                dfs(1, 0, 1, T2);
            }
            ans += Math.max(tmp, rst);
        }

        System.out.println(ans);
    }

    private static void dfs(int x, int y, int count, int type) {
        rst = Math.max(count, rst);
        if(y>=N) {
            return;
        }

        int nx = x;
        int ny = y;
        nx += 2;
        if(nx>=N) {
            if(type==T1) {
                nx = ny%2==0?1:0;
                ny++;
            } else {
                nx = ny%2==0?0:1;
                ny++;
            }
        }


        if(0<=nx && nx<N && 0<=ny && ny<N && board[ny][nx]==1 && isPromising(nx, ny)) {
            board[ny][nx] = 2;
            dfs(nx, ny, count+1, type); // 다음칸에 말을 놓는 경우
            board[ny][nx] = 1;
            dfs(nx, ny, count, type); // 다음칸에 말을 놓지 않는 경우
        } else {
            dfs(nx, ny, count, type);
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
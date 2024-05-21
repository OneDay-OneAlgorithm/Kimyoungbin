package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2665 {
    static int n, rst = Integer.MAX_VALUE;
    static int[][] arr, cnt;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        cnt = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split("");
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(line_n[j]); //0: 검정방, 1: 흰방
                cnt[i][j] = INF;
            }
        }

        visited[0][0] = true;
        dfs(0, 0, 0);

        System.out.println(rst);
    }

    // cnt: 바꾼 검은방의 수
    private static void dfs(int x, int y, int cur) {
        if(x==n-1 && y==n-1) {
            rst = Math.min(rst, cur);
        }

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            // 유망검사) 방문한 적 없고, 바꾼 방의 수가 작은 경우에만 진행
            if(0<=nx && nx<n && 0<=ny && ny<n && !visited[ny][nx]) {
                if(arr[ny][nx]==0 && cnt[ny][nx]>cur+1) { // 다음이 검은방
                    cnt[ny][nx] = cur+1;
                    visited[ny][nx] = true;
                    dfs(nx, ny, cur+1);
                    visited[ny][nx] = false;
                } else if(arr[ny][nx]==1 && cnt[ny][nx]>cur) { // 다음이 흰방
                    cnt[ny][nx] = cur;
                    visited[ny][nx] = true;
                    dfs(nx, ny, cur);
                    visited[ny][nx] = false;
                }
            }
        }
    }
}
package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dfs 반복
public class BOJ2468 {
    static int N, cnt;
    static int[][] arr;
    static boolean[][] visited;
    static int rst = Integer.MIN_VALUE;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        // 수위 0~100
        for(int i=0; i<100; i++) {
            cnt=0;
            visited = new boolean[N][N];
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    if(!visited[j][k] && arr[j][k]>i) {
                        dfs(k, j, i);
                        cnt++;
                    }
                }
            }
            rst = Math.max(rst, cnt);
        }

        System.out.println(rst);
    }

    private static void dfs(int x, int y, int level) {
        visited[y][x] = true;

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N && !visited[ny][nx] && arr[ny][nx]>level) {
                dfs(nx, ny, level);
            }
        }
    }
}
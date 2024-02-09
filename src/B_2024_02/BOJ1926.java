package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1926
{
    static int n, m;
    static int[][] paper;
    static boolean[][] visited;
    static int 그림개수 = 0;
    static int 최대넓이 = 0;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int 그림넓이;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        paper = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++) {
            int[] line_n = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            for(int j=0; j<m; j++) {
                paper[i][j] = line_n[j];
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visited[i][j] && paper[i][j]==1) {
                    그림개수++;
                    그림넓이 = 0;
                    dfs(i, j);
                    최대넓이 = Math.max(최대넓이, 그림넓이);
                }
            }
        }

        System.out.println(그림개수);
        System.out.println(최대넓이);
    }

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        그림넓이++;
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<m && 0<=ny && ny<n && !visited[ny][nx] && paper[ny][nx]==1) {
                dfs(ny, nx);
            }
        }
    }
}
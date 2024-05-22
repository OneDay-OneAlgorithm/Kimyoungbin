package SOFTEER_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// n이 작으므로 그냥 dfs 연속으로 3개
public class 순서대로_방문하기 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        arr = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                arr[j][i] = Integer.parseInt(line_n[j]);
            }
        }
        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            list.add(new int[]{Integer.parseInt(line_m[0])-1, Integer.parseInt(line_m[1])-1});
        }

        visited[list.get(0)[1]][list.get(0)[0]] = true;
        dfs(list.get(0)[0], list.get(0)[1], 1);

        System.out.println(rst);
    }

    private static void dfs(int x, int y, int dest) {
        // 마지막 방문지 도착
        if(dest==m-1 && x==list.get(dest)[0] && y==list.get(dest)[1]) {
            rst++;
            return;
        }
        // 다음 방문지 도착
        if(x==list.get(dest)[0] && y==list.get(dest)[1]) {
            dfs(x, y, dest+1);
            return;
        }


        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            int ndestx = list.get(dest)[0];
            int ndesty = list.get(dest)[1];
            // 다음점 미방문, 다음목표 미방문, 빈칸
            if(0<=nx && nx<n && 0<=ny && ny<n && !visited[ny][nx] && !visited[ndesty][ndestx] && arr[ny][nx]==0) {
                visited[ny][nx] = true;
                dfs(nx, ny, dest);
                visited[ny][nx] = false;
            }
        }
    }
}

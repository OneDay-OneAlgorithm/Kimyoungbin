package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2583 {
    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static int cnt, rst = 0;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        M = Integer.parseInt(line_1[0]);
        N = Integer.parseInt(line_1[1]);
        K = Integer.parseInt(line_1[2]);
        arr = new int[M][N];
        visited = new boolean[M][N];

        for(int i=0; i<K; i++) {
            String[] line_K = br.readLine().split(" ");
            int x1 = Integer.parseInt(line_K[0]);
            int y1 = Integer.parseInt(line_K[1]);
            int x2 = Integer.parseInt(line_K[2]);
            int y2 = Integer.parseInt(line_K[3]);

            for(int j=y1; j<y2; j++) {
                for(int k=x1; k<x2; k++) {
                    visited[j][k] = true;
                }
            }
        }

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    cnt = 0;
                    dfs(j, i);
                    list.add(cnt);
                    rst++;
                }
            }
        }

        Collections.sort(list);
        System.out.println(rst);
        for (Integer i : list) {
            System.out.print(i+" ");
        }
    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;
        cnt++;

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<M && !visited[ny][nx]) {
                dfs(nx, ny);
            }
        }
    }
}
package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ21736
{
    static int N, M, rst = 0;
    static char[][] arr;
    static boolean[][] visited;
    static int sN, sM;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String line_N = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = line_N.charAt(j);
                if(arr[i][j]=='I') {
                    sN = i;
                    sM = j;
                }
            }
        }

        dfs(sN, sM);
        System.out.println(rst!=0?rst:"TT");
    }

    private static void dfs(int y, int x) {
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<M && 0<=ny && ny<N && !visited[ny][nx] && arr[ny][nx]!='X') {
                visited[ny][nx] = true;
                if(arr[ny][nx]=='P') rst++;
                dfs(ny, nx);
            }
        }
    }
}
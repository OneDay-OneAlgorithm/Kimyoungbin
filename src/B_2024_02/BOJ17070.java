package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//3^16<1억 같은데
public class BOJ17070
{
    static int N;
    static int[][] arr;
    static int rst = 0;
    static int[] dx = {1,1,0};
    static int[] dy = {0,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            String[] line_1 = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(line_1[j-1]);
            }
        }

        dfs(1, 1, 2);
        System.out.println(rst);
    }

    // dir: 1(우측), 2(대각), 3(아래)
    private static void dfs(int dir, int y, int x) {
        if(y==N && x==N) {
            rst++;
            return;
        }

        for(int i=1; i<=3; i++) {
            if(Math.abs(dir-i)<2) { // 1-> (1,2), 2->(1,2,3), 3->(2,3)
                int nx = x+dx[i-1];
                int ny = y+dy[i-1];
                if (1<=nx && nx<=N && 1<=ny && ny<=N && 벽을긁는지여부(x, y, nx, ny)) {
                    dfs(i, ny, nx);
                }
            }
        }

    }

    private static boolean 벽을긁는지여부(int x, int y, int nx, int ny) {
        if(arr[y][x]==1) return false;
        if(arr[y][nx]==1) return false;
        if(arr[ny][x]==1) return false;
        if(arr[ny][nx]==1) return false;
        return true;
    }
}
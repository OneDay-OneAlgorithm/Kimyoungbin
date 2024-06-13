package B_2024_06;

import java.io.*;

public class BOJ16926 {
    static int N, M, R;
    static int[][] arr, newarr;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0}; // 맨 왼쪽 위부터 돌아가면서
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        R = Integer.parseInt(line_1[2]);
        arr = new int[N+1][M+1];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i+1][j+1] = Integer.parseInt(line_N[j]);
            }
        }

        for(int i=0; i<R; i++) {
            rotate();
            copy();
        }

        printRst();
    }

    private static void rotate() {
        newarr = new int[N+1][M+1];
        for(int k=1; k<=N/2 && k<=M/2; k++) {
            int sx = k;
            int sy = k;
            int x = sx;
            int y = sy;
            int dir = 0;

            while(true) {
                // 범위 초과시 dir++
                if(k>x+dx[dir] || x+dx[dir]>M-(k-1) || k>y+dy[dir] || y+dy[dir]>N-(k-1)) {
                    dir = (dir+1)%4;
                    continue;
                }

                // 다음 방향 검사
                int nx = x+dx[dir];
                int ny = y+dy[dir];
                if(k<=nx && nx<=M-(k-1) && k<=ny && ny<=N-(k-1) && newarr[ny][nx]==0) {
                    newarr[ny][nx] = arr[y][x];
                    x = nx;
                    y = ny;
                    if(x==sx && y==sy) {
                        break;
                    }
                }
            }
        }
    }

    private static void copy() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                arr[i][j] = newarr[i][j];
            }
        }
    }

    private static void printRst() throws IOException {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                bw.write(arr[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}

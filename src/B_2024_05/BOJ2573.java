package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N,M<=300
// DIST1) 변수 오기
public class BOJ2573 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        int year = 0;
        while(true) {
            int isSeparated = isSeparated();
            if(isSeparated==2) {
                year = 0;
                break;
            } else if(isSeparated==1) {
                break;
            } else {
                updateIce();
                year++;
            }
//            printMap();
        }
        System.out.println(year);

    }

    // 빙산 업데이트
    private static void updateIce() {
        int[][] nextMap = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                nextMap[i][j] = map[i][j];
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 빙산은 사방에 물 개수만큼 높이 감소시킴
                if(map[i][j]!=0) {
                    int water = 0;
                    for(int k=0; k<4; k++) {
                        int nx = j+dx[k]; // DIST1
                        int ny = i+dy[k]; // DIST1
                        if(0<=nx && nx<M && 0<=ny && ny<N && map[ny][nx]==0) {
                            water++;
                        }
                    }
                    nextMap[i][j] = Math.max(map[i][j]-water, 0); // time 증가시 map을 별도로 분리
                }
            }
        }

        // 실제 map 업데이트
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = nextMap[i][j];
            }
        }
    }

    // 분리X: 0, 분리O: 1, 빙산존재X: 2
    private static int isSeparated() {
        int ice = 0;
        int sx = -1, sy = -1;
        // 빙산 넓이 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]!=0) {
                    ice++;
                    sx = j;
                    sy = i;
                }
            }
        }
        if(ice==0) return 2;

        // dfs를 통해서 visited 표시
        visited = new boolean[N][M];
        dfs(sx, sy);

        // dfs후 visited되지 않은 빙산이 있다면 분리되었음을 의미
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j] && map[i][j]!=0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<M && 0<=ny && ny<N && map[ny][nx]!=0 && !visited[ny][nx]) {
                dfs(nx, ny);
            }
        }
    }

    private static void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
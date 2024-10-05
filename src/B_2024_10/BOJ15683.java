package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// CCTV 개수가 최대 8개이므로 완탐 빡구현
// 1,3,4번: 4종류, 2번: 2종류, 5번: 1종류
// CCTV 위치는 사각지대가 아님
// WR1) 사각지대 제거해나갈때 돌 고려해야함
public class BOJ15683 {
    static int N, M;
    static int[][] map; // 1~5: CCTV, 6: 벽, 7: 빈칸, 8이상: 감시중
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int origin = Integer.parseInt(st.nextToken());
                map[i][j] = origin==0?7:origin;
            }
        }

        // 1차원 배열 dfs
        dfs(0);

        System.out.println(rst);
    }

    private static void dfs(int cur) {
        if(cur==N*M) {
            rst = Math.min(rst, getBlank());
//            System.out.println(getBlank());
            return;
        }

        int x = cur%M;
        int y = cur/M;
//        System.out.println("x = " + x + ", y = "+y);

        if(map[y][x]==1) { // 1번 CCTV -> 4방향
            // 위쪽
            high(x, y, true);
            dfs(cur+1);
            high(x, y, false);

            // 아래쪽
            low(x, y, true);
            dfs(cur+1);
            low(x, y, false);

            // 왼쪽
            left(x, y, true);
            dfs(cur+1);
            left(x, y, false);

            // 오른쪽
            right(x, y, true);
            dfs(cur+1);
            right(x, y, false);
        } else if(map[y][x]==2) {
            // 왼쪽+오른쪽
            left(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            left(x, y, false);
            right(x, y, false);

            // 위쪽+아래쪽
            high(x, y, true);
            low(x, y, true);
            dfs(cur+1);
            high(x, y, false);
            low(x, y, false);
        } else if(map[y][x]==3) {
            // 위쪽+오른쪽
            high(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            high(x, y, false);
            right(x, y, false);

            // 오른쪽+아래쪽
            right(x, y, true);
            low(x, y, true);
            dfs(cur+1);
            right(x, y, false);
            low(x, y, false);

            // 아래쪽+왼쪽
            low(x, y, true);
            left(x, y, true);
            dfs(cur+1);
            low(x, y, false);
            left(x, y, false);

            // 왼쪽+위쪽
            left(x, y, true);
            high(x, y, true);
            dfs(cur+1);
            left(x, y, false);
            high(x, y, false);
        } else if(map[y][x]==4) {
            // 아래 제외
            left(x, y, true);
            high(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            left(x, y, false);
            high(x, y, false);
            right(x, y, false);

            // 왼쪽 제외
            low(x, y, true);
            high(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            low(x, y, false);
            high(x, y, false);
            right(x, y, false);

            // 위쪽 제외
            low(x, y, true);
            left(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            low(x, y, false);
            left(x, y, false);
            right(x, y, false);

            // 오른쪽 제외
            low(x, y, true);
            left(x, y, true);
            high(x, y, true);
            dfs(cur+1);
            low(x, y, false);
            left(x, y, false);
            high(x, y, false);
        } else if(map[y][x]==5) {
            // 오른쪽 제외
            low(x, y, true);
            left(x, y, true);
            high(x, y, true);
            right(x, y, true);
            dfs(cur+1);
            low(x, y, false);
            left(x, y, false);
            high(x, y, false);
            right(x, y, false);
        } else {
            dfs(cur+1);
        }
    }

    // 좌측방향 증/감
    private static void left(int x, int y, boolean incr) {
        // 증가
        if(incr) {
            while(0<=x && map[y][x]!=6) {
                if(map[y][x]>=7) {
                    map[y][x]++;
                }
                x--;
            }
        } else {
            while(0<=x && map[y][x]!=6) {
                if(map[y][x]>=8) {
                    map[y][x]--;
                }
                x--;
            }
        }
    }

    // 우측방향 증/감
    private static void right(int x, int y, boolean incr) {
        // 증가
        if(incr) {
            while(x<M && map[y][x]!=6) {
                if(map[y][x]>=7) {
                    map[y][x]++;
                }
                x++;
            }
        } else {
            while(x<M && map[y][x]!=6) {
                if(map[y][x]>=8) {
                    map[y][x]--;
                }
                x++;
            }
        }
    }

    // 윗방향 증/감
    private static void high(int x, int y, boolean incr) {
        // 증가
        if(incr) {
            while(y>=0 && map[y][x]!=6) {
                if(map[y][x]>=7) {
                    map[y][x]++;
                }
                y--;
            }
        } else {
            while(y>=0 && map[y][x]!=6) {
                if(map[y][x]>=8) {
                    map[y][x]--;
                }
                y--;
            }
        }
    }

    // 아래방향 증/감
    private static void low(int x, int y, boolean incr) {
        // 증가
        if(incr) {
            while(y<N && map[y][x]!=6) {
                if(map[y][x]>=7) {
                    map[y][x]++;
                }
                y++;
            }
        } else {
            while(y<N && map[y][x]!=6) {
                if(map[y][x]>=8) {
                    map[y][x]--;
                }
                y++;
            }
        }
    }

    private static int getBlank() {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==7) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

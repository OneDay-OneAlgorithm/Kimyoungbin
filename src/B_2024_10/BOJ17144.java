package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 미세먼지 확산 -> 공기청정기 작동 순서
// 공기청정기에서 바람이 나오기 시작하는 것은 고려 X (time=1일때부터 이동하는 것으로 보임)
// R*C*T 작으므로 3차원 배열(시간)으로 풀이
// T초 후의 상황 => arr[2*T][][]
public class BOJ17144 {
    static int R, C, T;
    static int[][][] arr; // -2: 반시계방향 공기청정기, -1: 시계방향, 0: 빈칸, 1+: 먼지
    static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0}; // 상-하-좌-우
    static int[][] wind; // 0~3중 고름
    static int wx1, wy1, wx2, wy2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[2*T+2][R][C];
        wind = new int[R][C];

        boolean firstCond = true;
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                arr[0][i][j] = Integer.parseInt(st.nextToken());
                // 먼지 큐 초기화
//                if(arr[0][i][j]>0) {
//                    queue.add(new Info(j, i, 0));
//                }
                wind[i][j] = -1; // 바람 초기화
                if(arr[0][i][j]==-1) { // 공기청정기 초기화
                    if(firstCond) {
                        arr[0][i][j] = -2;
                        wx1 = j; wy1 = i;
                        firstCond = false;
                    } else {
                        arr[0][i][j] = -1;
                        wx2 = j; wy2 = i;
                    }
                }
            }
        }

        // 공기청정기 바람 방향 설정
        initWind();
        arr[0][wy1][wx1] = -1;

        // 초당 동작
        for(int t=0; t<=2*T; t++) {
            // 다음 초 초기화
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    arr[t+1][i][j] = arr[t][i][j];
                }
            }

            // 미세먼지 확산
//            System.out.println("t = " + t);
//            printArr(t);
            if(t%2==0) {
                for(int i=0; i<R; i++) {
                    for(int j=0; j<C; j++) {
                        if(arr[t][i][j]>0) { // 먼지가 있다면 확산
                            for(int k=0; k<4; k++) {
                                int nx = j+dx[k];
                                int ny = i+dy[k];
                                if(0<=nx && nx<C && 0<=ny && ny<R && arr[t][ny][nx]>=0) {
                                    arr[t+1][ny][nx]+=(arr[t][i][j]/5);
                                    arr[t+1][i][j]-=(arr[t][i][j]/5);
                                }
                            }
                        }
                    }
                }
            } else { // 공기청정기
                for(int i=0; i<R; i++) {
                    for(int j=0; j<C; j++) {
                        if(wind[i][j]!=-1) {
                            int nx = j+dx[wind[i][j]];
                            int ny = i+dy[wind[i][j]];
                            if(isCleaner(j, i)) { // 현재 위치가 공기청정기라면 정화되어 나옴
                                arr[t+1][ny][nx] = 0;
                            } else if(isCleaner(nx, ny)) { // 다음 위치가 공기청정기라면 정화 (유지)
                                arr[t+1][ny][nx] = -1;
                            } else if(arr[0][ny][nx]>=0) { // 둘 다 아니라면 먼지는 이동
                                arr[t+1][ny][nx] = arr[t][i][j];
                            }
                        }
                    }
                }
            }
        }
//        printArr(2*T);

        // 남은 먼지 구하기
        int rst = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                rst+=arr[2*T][i][j];
            }
        }
        System.out.println(rst+2); // 공기청정기 2개 고려
    }

    private static boolean isCleaner(int x, int y) {
        return (x==wx1 && y==wy1) || (x==wx2 && y==wy2);
    }

    private static void initWind() {
        // 반시계방향
        int tx = wx1, ty = wy1;
        while(tx<C-1) {
            wind[ty][tx] = 3;
            tx++;
        }
        while(ty>0) {
            wind[ty][tx] = 0;
            ty--;
        }
        while(tx>0) {
            wind[ty][tx] = 2;
            tx--;
        }
        while(ty<wy1) {
            wind[ty][tx] = 1;
            ty++;
        }

        // 시계방향
        tx = wx2; ty = wy2;
        while(tx<C-1) {
            wind[ty][tx] = 3;
            tx++;
        }
        while(ty<R-1) {
            wind[ty][tx] = 1;
            ty++;
        }
        while(tx>0) {
            wind[ty][tx] = 2;
            tx--;
        }
        while(ty>wy2) {
            wind[ty][tx] = 0;
            ty--;
        }
    }

    private static void printArr(int t) {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                System.out.print(arr[t][i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

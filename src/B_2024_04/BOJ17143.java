package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1. 상어 이동 (RxCxM)
//2. 이동후 장아먹는 처리 -> Queue[][];
//3. 상어 제거 후 잡았다면 크기 합 구하기 (R)
//WA1) C=100일경우 curTime이 100+1에 접근하므로 ArrayIndexOfBoundsException
public class BOJ17143 {
    static int R, C, M;
    static Shark[][][] board;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        R = Integer.parseInt(line_1[0]);
        C = Integer.parseInt(line_1[1]);
        M = Integer.parseInt(line_1[2]);
        board = new Shark[R+1][C+1][102]; // 좌표, 시간 // WA1
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int r = Integer.parseInt(line_M[0]);
            int c = Integer.parseInt(line_M[1]);
            int s = Integer.parseInt(line_M[2]);
            int d = Integer.parseInt(line_M[3]); // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
            int z = Integer.parseInt(line_M[4]);

            board[r][c][1] = new Shark(r, c, s, d, z, 1);
        }

        // 1. 낚시, 2. 상어 이동
        for(int curTime=1; curTime<=C; curTime++) {
//            for(int i=1; i<=R; i++) {
//                for(int j=1; j<=C; j++) {
//                    if(board[i][j][curTime]!=null) {
//                        System.out.print(board[i][j][curTime].z+" ");
//                    } else {
//                        System.out.print(0+" ");
//                    }
//                }
//                System.out.println();
//            }
//            System.out.println();
            // 낚시
            for(int i=1; i<=R; i++) {
                if(board[i][curTime][curTime]!=null) {
                    rst+=board[i][curTime][curTime].z;
//                    System.out.println("curTime="+curTime+", shark="+board[i][curTime][curTime].z);
                    board[i][curTime][curTime] = null;
                    break;
                }
            }
            // 상어 이동
            for(int i=1; i<=R; i++) {
                for(int j=1; j<=C; j++) {
                    if(board[i][j][curTime]!=null) { // 이번에 이동할 상어라면
                        Shark newShark = move(board[i][j][curTime]);
                        int newX = newShark.c;
                        int newY = newShark.r;
                        // 이미 상어가 있다면 비교하여 잡아먹기
                        if(board[newY][newX][curTime+1]!=null) {
                            board[newY][newX][curTime+1] = newShark.z>board[newY][newX][curTime+1].z?newShark:board[newY][newX][curTime+1];
//                            System.out.println(newShark.z+" <-eat-> "+board[newY][newX][curTime+1].z);
                        } else {
                            board[newY][newX][curTime+1] = newShark;
                        }
                    }
                }
            }

        }

        System.out.println(rst);
    }

    // 속력만큼 이동한 후의 상어
    private static Shark move(Shark shark) {
        for(int i=0; i<shark.s; i++) { // 속도만큼 상어 이동
            if(shark.d==1) {
                if(shark.r==1) { // 위로 이동중 맨 위에 도달
                    shark.d = 2;
                    shark.r = 2;
                } else {
                    shark.r--;
                }
            } else if(shark.d==2) {
                if(shark.r==R) { // 아래로 이동중 바닥에 도달
                    shark.d = 1;
                    shark.r = R-1;
                } else {
                    shark.r++;
                }
            } else if(shark.d==3) {
                if(shark.c==C) { // 오른쪽 이동중 맨 오른쪽 도달
                    shark.d = 4;
                    shark.c = C-1;
                } else {
                    shark.c++;
                }
            } else if(shark.d==4) {
                if(shark.c==1) { // 왼쪽 이동중 맨 왼쪽 도달
                    shark.d = 3;
                    shark.c = 2;
                } else {
                    shark.c--;
                }
            }
        }
        shark.time++;
        return shark;
    }

    static class Shark {
        int r;
        int c;
        int s; // 속력
        int d; // 방향
        int z; // 크기
        int time; // 시간에 따른 위치 분리

        public Shark(int r, int c, int s, int d, int z, int time) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.time = time;
        }
    }
}
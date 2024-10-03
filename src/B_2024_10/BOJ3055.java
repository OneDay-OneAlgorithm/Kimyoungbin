package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// S->D 도달 가능?
// 순서: 물 -> 고슴도치
public class BOJ3055 {
    static int R, C;
    static int[][] map; //0: 빈공간, 1: 물, 2: 고슴도치, 3: 비버굴, 4: 돌
    static boolean[][][] visited;
    static final int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int tx, ty;
    static PriorityQueue<Info> q = new PriorityQueue<>(new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            if(o1.time==o2.time) {
                return o1.type-o2.type; // 시간이 같다면 물이 먼저 이동
            }
            return o1.time-o2.time; // 시간순
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[3][R][C];

        for(int i=0; i<R; i++) {
            String[] line_R = br.readLine().split("");
            for(int j=0; j<C; j++) {
                String input = line_R[j];
                if(input.equals(".")) {
                    map[i][j] = 0;
                } else if(input.equals("*")) { // 물
                    map[i][j] = 1;
                    visited[1][i][j] = true;
                    q.add(new Info(j, i, 0, 1));
                } else if(input.equals("S")) { // 고슴도치
                    map[i][j] = 2;
                    visited[2][i][j] = true;
                    q.add(new Info(j, i, 0, 2));
                } else if(input.equals("D")) {
                    tx = j;
                    ty = i;
                    map[i][j] = 3;
                } else {
                    map[i][j] = 4;
                }
            }
        }

        while(!q.isEmpty()) {
            Info cur = q.poll();

            // cur.type==1 (물)
            if(cur.type==1) {
                for(int i=0; i<4; i++) {
                    int nx = cur.x+dx[i];
                    int ny = cur.y+dy[i];

                    if(0<=nx && nx<C && 0<=ny && ny<R && !visited[cur.type][ny][nx] && map[ny][nx]!=3 && map[ny][nx]!=4) {
                        visited[cur.type][ny][nx] = true;
                        q.add(new Info(nx, ny, cur.time+1, cur.type));
                    }
                }
            }

            // cur.type==2 (고슴도치)
            else {
                for(int i=0; i<4; i++) {
                    int nx = cur.x+dx[i];
                    int ny = cur.y+dy[i];

                    if(0<=nx && nx<C && 0<=ny && ny<R && !visited[cur.type][ny][nx] && !visited[1][ny][nx] && map[ny][nx]!=4) {
                        if(map[ny][nx]==3) {
                            System.out.println(cur.time+1);
                            return;
                        }
                        visited[cur.type][ny][nx] = true;
                        q.add(new Info(nx, ny, cur.time+1, cur.type));
                    }
                }
            }
        }

        System.out.println("KAKTUS");

    }

    static class Info {
        int x;
        int y;
        int time;
        int type;
        public Info(int x, int y, int time, int type) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.type = type;
        }
    }
}

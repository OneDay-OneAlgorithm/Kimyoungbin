package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {
    static int L, R, C;
    static int[][][] arr; // 0:빈칸, 1:막힘
    static boolean[][][] visited;
    static int[] dx = {1,0,-1,0,0,0}, dy = {0,1,0,-1,0,0,0}, dh = {0,0,0,0,1,-1};
    static Queue<Info> queue;
    static int sx, sy, sh, ex, ey, eh;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0) {
                break;
            }

            arr = new int[L][R][C];

            for(int h=0; h<L; h++) {
                for(int i=0; i<R; i++) {
                    String line_R = br.readLine();
                    for(int j=0; j<C; j++) {
                        char ch = line_R.charAt(j);
                        if(ch=='S') {
                            sx = j;
                            sy = i;
                            sh = h;
                            arr[h][i][j] = 0;
                        } else if(ch=='E') {
                            ex = j;
                            ey = i;
                            eh = h;
                            arr[h][i][j] = 0;
                        } else if(ch=='.') {
                            arr[h][i][j] = 0;
                        } else {
                            arr[h][i][j] = 1;
                        }
                    }
                }
                br.readLine();
            }

            int rst = bfs();
            if(rst!=-1) {
                System.out.println("Escaped in "+rst+" minute(s).");
            } else {
                System.out.println("Trapped!");
            }
        }

    }

    private static int bfs() {
        queue = new LinkedList<>();
        visited = new boolean[L][R][C];
        queue.add(new Info(sx, sy, sh, 0));
        visited[sh][sy][sx] = true;

        while(!queue.isEmpty()) {
            Info cur = queue.poll();
            if(cur.x==ex && cur.y==ey && cur.h==eh) {
                return cur.time;
            }

            for(int i=0; i<6; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                int nh = cur.h+dh[i];
                if(0<=nx && nx<C && 0<=ny && ny<R && 0<=nh && nh<L && !visited[nh][ny][nx] && arr[nh][ny][nx]!=1) {
                    queue.add(new Info(nx, ny, nh, cur.time+1));
                    visited[nh][ny][nx] = true;
                }
            }
        }

        return -1;
    }

    static class Info {
        int x;
        int y;
        int h;
        int time;
        public Info(int x, int y, int h, int time) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }
    }
}

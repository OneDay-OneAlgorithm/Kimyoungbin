package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 불 -> 지훈이 순서대로 bfs (지훈이 먼저하면 불이 잡아먹을 수 있음)
// J는 하나만 주어진다 -> 불 시작점이 여러개일 수 있음
// MLE) 지훈이 visited 배열 추가
public class BOJ4179 {
    static int R, C;
    static int[][] maze; // 0: 빈공간, 1:벽, 2: 불
    static Queue<Info> fq = new LinkedList<>(); // 불 bfs 정보
    static Queue<Info> jq = new LinkedList<>(); // 지훈 bfs 정보
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        R = Integer.parseInt(line_1[0]);
        C = Integer.parseInt(line_1[1]);
        maze = new int[R][C];
        visited = new boolean[R][C];
        for(int i=0; i<R; i++) {
            String line_R = br.readLine();
            for(int j=0; j<C; j++) {
                char here = line_R.charAt(j);
                if(here=='#')
                    maze[i][j] = 1;
                else if(here=='.')
                    maze[i][j] = 0;
                else if(here=='J') {
                    jq.add(new Info(j, i, 0));
                } else if(here=='F') {
                    maze[i][j] = 2;
                    fq.add(new Info(j, i, 0));
                }
            }
        }

        int time = bfs();
        if(time==1_000_000) System.out.println("IMPOSSIBLE");
        else System.out.println(time);
    }

    private static int bfs() {
        int time = 0;
        while(time<1_000_000) {
            // 불 확산
            while(!fq.isEmpty()) {
                if(fq.peek().time!=time) {
                    break;
                }

                Info cur = fq.poll();
                for(int i=0; i<4; i++) {
                    int nx = cur.x+dx[i];
                    int ny = cur.y+dy[i];
                    if(0<=nx && nx<C && 0<=ny && ny<R && maze[ny][nx]==0) {
                        fq.add(new Info(nx, ny, cur.time+1));
                        maze[ny][nx] = 2;
                    }
                }
            }

            // 지훈이 이동
            while(!jq.isEmpty()) {
                if(jq.peek().time!=time) {
                    break;
                }

                Info cur = jq.poll();
                for(int i=0; i<4; i++) {
                    int nx = cur.x+dx[i];
                    int ny = cur.y+dy[i];
                    if(nx<0 || nx>=C || ny<0 || ny>=R) {
                        return cur.time+1;
                    } else if(maze[ny][nx]==0 && !visited[ny][nx]) {
                        jq.add(new Info(nx, ny, cur.time+1));
                        visited[ny][nx] = true;
                    }
                }
            }
            time++;
        }

        return time;
    }

    static class Info {
        int x;
        int y;
        int time;

        public Info(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
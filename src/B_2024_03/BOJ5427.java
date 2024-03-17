package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 출구가 1개라는 조건이 없다.. -> 목표치를 설정을 안하고 그냥 상근이도 bfs시켜주어야 할듯.
// 상근이를 bfs시킬때는 visited체크를 해주고, 불이 번지는건 맵을 바꿔주면 된다.
// 불이 붙으려는 칸으로 이동 불가능, 불이 붙어오는 동시에 이동가능 -> 불을 먼저 움직여야 함
public class BOJ5427
{
    static int w, h;
    static int[][] map; // 0: 빈공간, 1: 불, 2: 벽
    static int sx, sy; // 상근이 시작위치
    static boolean[][] visited; // 상근이 방문여부 체크
    static Queue<Fire> fq = new LinkedList<>();
    static Queue<Sangkun> sq = new LinkedList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int rst; // 빠져나오는데 가장 빠른 시간
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            String[] line_1 = br.readLine().split(" ");
            w = Integer.parseInt(line_1[0]);
            h = Integer.parseInt(line_1[1]);
            map = new int[h][w];
            visited = new boolean[h][w];
            rst = Integer.MAX_VALUE;

            for (int i = 0; i < h; i++) {
                String[] str = br.readLine().split("");
                for (int j = 0; j < w; j++) {
                    switch (str[j]) {
                        case "." -> map[i][j] = 0;
                        case "*" -> {
                            map[i][j] = 1;
                            // 큐에 불 추가
                            fq.add(new Fire(j, i, 0));
                        }
                        case "#" -> map[i][j] = 2;
                        case "@" -> {
                            map[i][j] = 0;
                            sx = j;
                            sy = i;
                        }
                    }
                }
            }

            // 상근이 큐에 집어넣기
            sq.add(new Sangkun(sx, sy, 0));

            int time = 0;
            while (time < 1000001) {
                // 불 먼저 번지게 하고
                while (!fq.isEmpty()) {
                    if (fq.peek().time != time) {
                        break;
                    }

                    Fire fire = fq.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = fire.x + dx[i];
                        int ny = fire.y + dy[i];
                        if (0 <= nx && nx < w && 0 <= ny && ny < h && map[ny][nx] == 0) {
                            map[ny][nx] = 1;
                            fq.add(new Fire(nx, ny, fire.time + 1));
                        }
                    }
                }

                // 상근이가 갈수 있는 곳 계산
                while (!sq.isEmpty()) {
                    if (sq.peek().time != time) {
                        break;
                    }

                    Sangkun sk = sq.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = sk.x + dx[i];
                        int ny = sk.y + dy[i];
                        // 상근이 탈출
                        if (0 > nx || nx >= w || 0 > ny || ny >= h) {
                            rst = Math.min(rst, time + 1);
                        }
                        if (0 <= nx && nx < w && 0 <= ny && ny < h && !visited[ny][nx] && map[ny][nx] == 0) {
                            visited[ny][nx] = true;
                            sq.add(new Sangkun(nx, ny, sk.time + 1));
                        }
                    }
                }

                time++;
            }

            System.out.println(rst != Integer.MAX_VALUE ? rst : "IMPOSSIBLE");
        }
    }

    static class Sangkun {
        int x;
        int y;
        int time;

        public Sangkun(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Fire {
        int x;
        int y;
        int time;

        public Fire(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
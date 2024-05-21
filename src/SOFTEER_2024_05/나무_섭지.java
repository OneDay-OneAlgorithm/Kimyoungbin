package SOFTEER_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 유령, 남우 각각이 모든칸에 대해 도달할 수 있는 최소시간 표시한 후
// 출구로부터 유령시간>남우시간인 길을 따라 남우의 출발자리까지 갈 수 있으면 가능하다. -> 그냥 출구로 들어가는 4방향 조사하면 될듯?
// TLE1) bfs while문 전에 시간정보 저장
// TLE2) 출구에서 먼 유령부터 bfs할 경우 모든 유령에 대해서 거리가 갱신되면서 O((N*M)*유령개수) 가능.
// TLE3) 유령이 100만마리에 가까울경우, 가까운순 정렬해도 TLE 가능? -> 가장 가까운 유령 한마리만 계산해야겠네 -> 거리는 제일 가까운데 남우를 막지 못하는 케이스가 분명 있을거리 이건 아님
// TLE4) 문제가 더러우니 더럽게 풀기 -> 가까운순 100마리의 유령만 고려
// RE5) 정렬하면 런타임에러 나는 테케가 몇개 있는데, 정렬 안하고 하면 맞는경우도 있음
// RE6) Math.abs로 정렬
// RE7) multi-source bfs 사용
public class 나무_섭지 {
    static int N, M, gcount = 0; // 유령 개수
    static int[] namwoo = new int[2]; //시작 x, y좌표
    static ArrayList<int[]> ghost = new ArrayList<>(); //유령 x, y좌표 // TLE2) 출구에서 가까운 유령부터 나오게끔 정렬
    static int[] exit = new int[2];
    static int[][] map; // 0: 빈공간, 1:벽, 2: 출구
    static int[][] nTime, gTime;
    static Queue<Info> queue;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static final int INF = 0x3f3f3f3f;
    static boolean rst;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        map = new int[N][M];
        nTime = new int[N][M];
        gTime = new int[N][M];
        visited = new boolean[N][M];

        // 도달시간 배열 초기화
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                nTime[i][j] = INF;
                gTime[i][j] = INF;
            }
        }

        // map 초기화, 남우와 유령 시작위치 저장
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                String str = line_N[j];
                if(str.equals(".")) map[i][j] = 0;
                else if(str.equals("#")) map[i][j] = 1;
                else if(str.equals("N")) {
                    map[i][j] = 0;
                    namwoo[0] = j;
                    namwoo[1] = i;
                } else if(str.equals("G")) {
                    map[i][j] = 0;
                    ghost.add(new int[]{j, i});
                } else if(str.equals("D")) {
                    map[i][j] = 2;
                    exit[0] = j;
                    exit[1] = i;
                }
            }
        }

        // 출구에서 가까운 유령부터 bfs하도록 정렬
        // Collections.sort(ghost, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] a1, int[] a2) {
        //         return Math.abs(a1[0]-exit[0])+Math.abs(a1[1]-exit[1]) - Math.abs(a2[0]-exit[0])+Math.abs(a2[1]-exit[1]); // RE5) Math.pow쓰고 형변환 하는대신 무식하게 연산, RE6) Math.abs
        //     }
        // });

        // 남우 위치에서 시작하면서 최소 도달시간 저장
        bfs1(namwoo[0], namwoo[1]);
        // 귀신 위치에서 시작하면서 최소 도달시간 저장 // TLE3
        // int s = ghost.size()>100?100:ghost.size();
        // int s = ghost.size();
        // for(int i=0; i<s; i++) {
        //     bfs2(ghost.get(i)[0], ghost.get(i)[1]);
        // }
        // bfs2(ghost.get(0)[0], ghost.get(0)[1]);

        // 출구에서 출발해서 남우 시작위치까지 도달 가능하면 탈출 가능
        // dfs(exit[0], exit[1]);

        // *** RE7) multi-source BFS ***
        queue = new LinkedList<>();
        for(int i=0; i<ghost.size(); i++) {
            int x = ghost.get(i)[0];
            int y = ghost.get(i)[1];
            queue.add(new Info(x, y, 0));
            gTime[y][x] = 0;
        }
        bfs2();

        // 말고 그냥 출구주변 4자리 조사하기
        checkAvailable(exit[0], exit[1]);

        System.out.println(rst?"Yes":"No");

    }

    private static void checkAvailable(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<M && 0<=ny && ny<N) {
                // System.out.println("nTime:"+nTime[ny][nx]+", gTime:"+gTime[ny][nx]);
                if(nTime[ny][nx]<gTime[ny][nx])
                    rst = true;
            }
        }
    }

    // // 출구부터 남우 시작위치까지 dfs
    // private static void dfs(int x, int y) {
    //     if(x==namwoo[0] && y==namwoo[1]) {
    //         rst = true;
    //     }

    //     for(int i=0; i<4; i++) {
    //         int nx = x+dx[i];
    //         int ny = y+dy[i];

    //         if(0<=nx && nx<M && 0<=ny && ny<N && !visited[ny][nx] && true) {
    //             visited[ny][nx] = true;
    //             dfs(nx, ny);
    //             visited[ny][nx] = false;
    //         }
    //     }
    // }

    // 남우 도달시간 저장
    private static void bfs1(int x, int y) {
        queue = new LinkedList<>();

        queue.add(new Info(x, y, 0));
        nTime[y][x] = 0; // TLE1

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(0<=nx && nx<M && 0<=ny && ny<N && nTime[ny][nx]>cur.time+1 && map[ny][nx]==0) {
                    nTime[ny][nx] = cur.time+1;
                    queue.add(new Info(nx, ny, cur.time+1));
                }
            }
        }
    }

    // 유령 도달시간 저장
    private static void bfs2() {

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(0<=nx && nx<M && 0<=ny && ny<N && gTime[ny][nx]>cur.time+1) {
                    gTime[ny][nx] = cur.time+1;
                    queue.add(new Info(nx, ny, cur.time+1));
                }
            }
        }
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

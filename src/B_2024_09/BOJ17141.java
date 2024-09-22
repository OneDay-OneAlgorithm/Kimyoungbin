package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// search) 연구소 3
// TLE1) setVirus 2중for문 반복 최소화
// TLE2) 조합이 아니라, 바이러스의 가능한 위치를 리스트에 담아서 dfs
// TLE3) cur+1이 아니라 i+1로 변환
public class BOJ17141 {
    static int N, M;
    static int[][] arr; // 0: 빈칸, 1:벽, 2: 바이러스 가능, 3: 바이러스
    static Queue<Node> queue;
    static ArrayList<Node> virus; // TLE2)
    static boolean[][] visited;
    static int emptyCnt = 0; // 빈칸
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        virus = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]!=1) emptyCnt++;
                if(arr[i][j]==2) virus.add(new Node(j, i, 0));
            }
        }

        // 바이러스 배치
        setVirus(0, 0);

        System.out.println(minTime==Integer.MAX_VALUE?-1:minTime);
    }

    // TLE1)
    private static void setVirus(int count, int cur) {
        if(count==M) {
            // bfs
            int time = bfs();
            minTime = Math.min(time, minTime);
            return;
        }

        for(int i=cur; i<virus.size(); i++) {
            Node node = virus.get(i);
            arr[node.y][node.x] = 3;
            setVirus(count+1, i+1); // TLE3)
            arr[node.y][node.x] = 2;
        }
    }

    private static int bfs() {
        int time, count = 0;
        queue = new LinkedList<>();
        visited = new boolean[N][N];
        // init
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j]==3) {
                    queue.add(new Node(j, i, 0));
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            count++; // 감염 개수 추가
            if(count==emptyCnt) {
                time = cur.time;
                return time;
            }

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<N && !visited[ny][nx] && arr[ny][nx]!=1) {
                    queue.add(new Node(nx, ny, cur.time+1));
                    visited[ny][nx] = true;
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Node {
        int x;
        int y;
        int time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}

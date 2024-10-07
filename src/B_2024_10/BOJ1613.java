package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 방향 비순환 그래프 (DAG)
// 전후 관계니까 위상 정렬 같지만 순서를 정의할 수 없는 경우가 있다. (https://www.acmicpc.net/board/view/123596)
// 위상 정렬은 그래프 내의 일부 노드간의 상대적인 순서를 알 수 있을뿐, 모든 노드간의 순서를 알 수는 없다.

// s=50000이므로 bfs를 2*5만번 돌리면 될듯 (400*2*50000)
// -> BFS 시간복잡도가 O(N)이 아니라 O(N+E)이므로 O(50000*2*50000)

// TLE1) 시작점 visited 미초기화.. 근데 이거가지고 TLE가 나나?
// TLE2) 중복 탐색을 줄일 수 있는 방법 => s마다 bfs를 하지 않고, n개의 노드에 대해서 bfs를 미리 돌려서 정보를 저장해놓으면 되지 않나?
public class BOJ1613 {
    static int n, k, s;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static boolean[][] connected; // visited[i][j]: i에서 시작해서 j에 도달할 수 있는지
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        connected = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++) {
            connected[i][i] = true;
        }

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
        }

        // 미리 bfs탐색으로 connected정보 완성
        for(int i=1; i<=n; i++) {
            bfs(i);
        }

        s = Integer.parseInt(br.readLine());
        for(int i=0; i<s; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(connected[start][end]) {
                System.out.println(-1);
            } else if(connected[end][start]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }

    // start에서 시작해서 end로 갈 수 있느냐
    private static void bfs(int start) {
        queue = new LinkedList<>();
        queue.add(start);
        visited = new boolean[n+1];

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i=0; i<list[cur].size(); i++) {
                int next = list[cur].get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    connected[start][next] = true;
                    queue.add(next);
                }
            }
        }

    }
}

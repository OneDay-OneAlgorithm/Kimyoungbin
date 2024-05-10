package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// search) 단순 bfs로는 TLE -> 중량에 대해 이분탐색하며 BFS로 가능여부 확인
// MLE) visited체크 안해줌
public class BOJ1939 {
    static int N, M, start, end;
    static ArrayList<Bridge>[] list;
    static Queue<Integer> queue;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int A = Integer.parseInt(line_M[0]);
            int B = Integer.parseInt(line_M[1]);
            int C = Integer.parseInt(line_M[2]);

            list[A].add(new Bridge(B, C));
            list[B].add(new Bridge(A, C));
        }
        String[] line_3 = br.readLine().split(" ");
        start = Integer.parseInt(line_3[0]);
        end = Integer.parseInt(line_3[1]);

        int left = 0, right = 1_000_000_000;
        int maxVal = 0;
        while(left<=right) {
            int mid = (left+right)/2;
            if(bfs(mid)) {
                left = mid+1;
                maxVal = Math.max(mid, maxVal);
            } else {
                right = mid-1;
            }
        }

        System.out.println(maxVal);
    }

    // bfs를 통해서 주어진 중량으로 최대중량을 초과하지 않고 start에서 end까지 도달할 수 있는지 확인
    private static boolean bfs(int weight) {
        boolean available = false;
        queue = new LinkedList<>();
        visited = new boolean[N+1];

        queue.add(start);
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(cur==end) {
                return true;
            }

            for(int i=0; i<list[cur].size(); i++) {
                int next = list[cur].get(i).n;
                int cost = list[cur].get(i).cost;

                if(!visited[next] && cost>=weight) {
                    visited[next] = true; // MLE
                    queue.add(next);
                }
            }
        }

        return available;
    }

    static class Bridge {
        int n;
        int cost;

        public Bridge(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }
    }
}
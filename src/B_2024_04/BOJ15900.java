package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// search) 루트 ~ 리프 길이합이 짝수면 패배, 홀수면 승리
public class BOJ15900 {
    static int N, depthSum = 0;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++) {
            String[] line_N = br.readLine().split(" ");
            int a = Integer.parseInt(line_N[0]);
            int b = Integer.parseInt(line_N[1]);
            list[a].add(b);
            list[b].add(a);
        }

        // root
        visited[1] = true;
        dfs(1, 0);

        System.out.println(depthSum%2!=0?"Yes":"No");
    }

    private static void dfs(int cur, int depth) {
        int connected = 0;
        for(int i=0; i<list[cur].size(); i++) {
            if(!visited[list[cur].get(i)]) {
                connected++;
            }
        }
        if(connected==0) {
            depthSum += depth;
        }

        for(int i=0; i<list[cur].size(); i++) {
            int next = list[cur].get(i);
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, depth+1);
                visited[next] = false;
            }
        }
    }
}
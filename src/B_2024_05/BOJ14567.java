package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 트리 depth의 최대값. 위상정렬
public class BOJ14567 {
    static int N, M;
    static int[] indegree; // 진입차수 배열
    static LinkedList<Integer>[] list;
    static Queue<Class> queue = new LinkedList<>();
    static int[] rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        indegree = new int[N+1];
        list = new LinkedList[N+1];
        rst = new int[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            list[start].add(end);
            indegree[end]++;
        }
        // 큐 초기화
        for(int i=1; i<=N; i++) {
            if(indegree[i]==0) {
                queue.add(new Class(i, 1));
                rst[i] = 1;
            }
        }

        // 진입차수 0 처리
        while(!queue.isEmpty()) {
            Class cur = queue.poll();
            for(int i=0; i<list[cur.no].size(); i++) {
                int next = list[cur.no].get(i);
                indegree[next]--;
                if(indegree[next]==0) {
                    queue.add(new Class(next, cur.depth+1));
                    rst[next] = cur.depth+1;
                }
            }
        }

        for(int i=1; i<=N; i++) {
            System.out.print(rst[i]+" ");
        }
    }

    static class Class {
        int no;
        int depth;

        public Class(int no, int depth) {
            this.no = no;
            this.depth = depth;
        }
    }
}
package B_2023_09;

import java.io.*;
import java.util.*;

public class BOJ1058
{
    static int N;
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static int rst = Integer.MIN_VALUE;
    static Queue<Node> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //연결리스트 초기화
        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        for(int i=1; i<=N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<N; j++) {
                if(input[j].equals("Y"))
                    list[i].add(j+1);
            }
        }

        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            rst = Math.max(rst, bfs(i));
        }

        System.out.println(rst);

    }

    private static int bfs(int start) {
        int cnt = 0;
        queue.add(new Node(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()) {
            Node poll = queue.poll();
            for(int i=0; i<list[poll.n].size(); i++) {
                int next = list[poll.n].get(i);
                if(!visited[next] && poll.depth<2) {
                    queue.add(new Node(next, poll.depth+1));
                    visited[next] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static class Node {
        int n;
        int depth;

        public Node(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}



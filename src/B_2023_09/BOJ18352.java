package B_2023_09;

import java.util.*;
import java.io.*;

public class BOJ18352
{
    static int N, M, K, X;
    static LinkedList<Integer>[] list;
    static Queue<Node> queue = new LinkedList<>();
    static boolean[] visited;
    static ArrayList<Integer> rst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        K = Integer.parseInt(line_1[2]);
        X = Integer.parseInt(line_1[3]);

        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        visited = new boolean[N+1];

        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            list[start].add(end);
        }

        visited[X] = true;
        queue.add(new Node(X, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int n = node.n;
            for(int i=0; i<list[n].size(); i++) {
                int next = list[n].get(i);
                if(!visited[next]) {
                    queue.add(new Node(next, node.depth+1));
                    visited[next] = true;
                    if(node.depth==K-1)
                        rst.add(next);
                }
            }
        }

        int[] sortedRst = new int[rst.size()];
        for(int i=0; i<rst.size(); i++) {
            sortedRst[i] = rst.get(i);
        }
        if(sortedRst.length==0) {
            System.out.println(-1);
        } else {
            Arrays.sort(sortedRst);
            for(int i=0; i<rst.size(); i++) {
                System.out.println(sortedRst[i]);
            }
        }
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


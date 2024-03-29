package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// n<=99999, root부터 bfs
// 정점에 부여된값이 k인 노드의 깊이를 출력하라 (정점 번호와 정점에 부여된 번호는 일치하지 않을 수 있다)
public class BOJ25511 {
    static ArrayList<Integer>[] list;
    static int n, k; // 정점, 찾는정점
    static Queue<Node> queue = new LinkedList<>();
    static int[] number; //number[i]: 정점 i에 부여된 번호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        k = Integer.parseInt(line_1[1]);
        list = new ArrayList[n];
        for(int i=0; i<n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++) {
            String[] line_n = br.readLine().split(" ");
            int start = Integer.parseInt(line_n[0]);
            int end = Integer.parseInt(line_n[1]);
            list[start].add(end);
        }
        // 정점에 부여된값이 k인 노드의 깊이를 출력하라 (정점 번호와 정점에 부여된 번호는 일치하지 않을 수 있다)
        number = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();

        bfs();
    }

    private static void bfs() {
        queue.add(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node find = queue.poll();
            if(number[find.n]==k) {
                System.out.println(find.depth);
            }
            for(int i=0; i<list[find.n].size(); i++) {
                int next = list[find.n].get(i);
                queue.add(new Node(next, find.depth+1));
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
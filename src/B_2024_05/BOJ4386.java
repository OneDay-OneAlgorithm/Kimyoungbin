package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

// WA) union함수 잘못 작성
public class BOJ4386 {
    static int n;
    static Node[] nodes;
    static LinkedList<Line> list = new LinkedList<>();
    static int[] parent;
    static double rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        parent = new int[n];

        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for(int i=0; i<n; i++) {
            String[] line_N = br.readLine().split(" ");
            double x = Double.parseDouble(line_N[0]);
            double y = Double.parseDouble(line_N[1]);
            nodes[i] = new Node(x, y);
        }

        // 간선 정보 저장
        for(int i=0; i<n; i++) {
            for (int j = i+1; j<n; j++) {
                Node n1 = nodes[i];
                Node n2 = nodes[j];
                double dist = getDist(n1.x, n1.y, n2.x, n2.y);
                list.add(new Line(i, j, dist));
            }
        }

        // 간선 오름차순 정렬
        Collections.sort(list, new Comparator<Line>() {
            @Override
            public int compare(Line l1, Line l2) {
                return (int)(l1.dist - l2.dist);
            }
        });

        // 시작 두 점 union
        // 간선 낮은것부터 union여부 체크하면서 비용 합산
        for(int i=0; i<list.size(); i++) {
            Line line = list.get(i);
            // 연결되어 있지 않은 점 사이 간선이라면 연결 후 비용 합산
            if(find(line.start)!=find(line.end)) {
                union(line.start, line.end);
//                System.out.print("line.start = " + line.start);
//                System.out.println(", line.end = " + line.end);
                rst+=line.dist;
            }
        }

        System.out.println(rst);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[find(b)] = find(a);
        } else {
            parent[find(a)] = find(b);
        }
    }

    private static int find(int a) {
        if(parent[a]==a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    private static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x2-x1), 2)+Math.pow(Math.abs(y2-y1), 2));
    }

    // 별 좌표정보
    static class Node {
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line {
        int start; // 시작점 번호
        int end; // 종료점 번호
        double dist;

        public Line(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}
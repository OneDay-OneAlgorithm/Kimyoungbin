package yet;

import java.io.*;
import java.util.*;

// 이동가능한 칸: 오른쪽 or 아래
// 스프링클러만 아니면 단순 bfs면 되는데..
// sum배열에 스프링클러값까지 적용한 최대값을 저장
public class 나무_수확 {
    static int n;
    static int[][] arr, sum;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static Queue<Node> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        sum = new int[n][n]; // arr[i][j]까지 열매수확량 최대값
        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(line_n[j]);
            }
        }
        sum[0][0] = arr[0][0];

        queue.add(new Node(0, 0, arr[0][0]*2, arr[0][0]));
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            // if(cur.x==n-1 && cur.y==n-1) {
            //     maxVal = Math.max(maxVal, cur.max);
            // }

            for(int i=0; i<2; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<n) {
                    // int nsum = cur.sum+arr[ny][nx];
                    int nmax = cur.max<arr[ny][nx]?arr[ny][nx]:cur.max;
                    int nsum = nmax>cur.max?cur.sum-cur.max+arr[ny][nx]*2:cur.sum+arr[ny][nx]; // max값이 갱신될 경우를 분기
                    if(nsum > sum[ny][nx]) {
                        sum[ny][nx] = nsum;
                        queue.add(new Node(nx, ny, nsum, nmax));
                    }
                }
            }
        }

        System.out.println(sum[n-1][n-1]);
    }

    static class Node {
        int x;
        int y;
        int sum; // 현재까지 열매수확량
        int max; // 현재까지 지나온 열매수확량 중 최대
        public Node(int x, int y, int sum, int max) {
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.max = max;
        }
    }
}
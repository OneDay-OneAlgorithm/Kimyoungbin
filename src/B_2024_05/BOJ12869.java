package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1. Greedy - 무조건 가장 많이남은 순으로 공격? -> 반례 존재
// 2. Backtracking - 931 913 391 319 193 139, N에따라 다름

// TLE1) 계속 1을주면 시간초과 가능 -> 하나 죽으면 배열크기 변화
// DFS를 하면 처음에 rst=60에 가깝게 탐색할 가능성이 있음 (3^60) -> BFS도 어차피 끝까지 가야 걸러낼 수 있으므로 무의미
// search-DP) BFS visited 이용 -> arr0, arr1, arr2의 정보를 담은 3차원 배열 사용 -> 최대 O(60*60*60)
public class BOJ12869 {
    static int N, rst=61;
    static Integer[] arr;
    static int[][] val1 = {{9,3,1}, {9,1,3}, {3,9,1}, {3,1,9}, {1,9,3}, {1,3,9}};
    static int[][] val2 = {{9,3}, {3,9}};
    static int[][] val3 = {{9}};
    static int[][][] dp = new int[61][61][61];
    static Queue<Info> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).map(s->Integer.parseInt(s)).toArray(Integer[]::new);

        bfs();

        System.out.println(rst);
    }

    private static void bfs() {
        if(N==3) {
            queue.add(new Info(arr[0], arr[1], arr[2], 0));

            while(!queue.isEmpty()) {
                Info cur = queue.poll();
                if(cur.i0<=0 && cur.i1<=0 && cur.i2<=0) {
                    rst = Math.min(rst, cur.cnt);
                    return;
                }

                for(int i=0; i<6; i++) {
                    int nexti0 = Math.max(cur.i0 - val1[i][0], 0);
                    int nexti1 = Math.max(cur.i1 - val1[i][1], 0);
                    int nexti2 = Math.max(cur.i2 - val1[i][2], 0);
                    if(dp[nexti0][nexti1][nexti2]==0) {
                        dp[nexti0][nexti1][nexti2] = dp[cur.i0][cur.i1][cur.i2]+1;
                        queue.add(new Info(nexti0, nexti1, nexti2, cur.cnt + 1));
                    }
                }
            }
        } else if(N==2) {
            queue.add(new Info(arr[0], arr[1], 0, 0));

            while(!queue.isEmpty()) {
                Info cur = queue.poll();
                if(cur.i0<=0 && cur.i1<=0) {
                    rst = Math.min(rst, cur.cnt);
                    return;
                }

                for(int i=0; i<2; i++) {
                    int nexti0 = Math.max(cur.i0 - val2[i][0], 0);
                    int nexti1 = Math.max(cur.i1 - val2[i][1], 0);
                    if(dp[nexti0][nexti1][0]==0) {
                        dp[nexti0][nexti1][0] = dp[cur.i0][cur.i1][0]+1;
                        queue.add(new Info(nexti0, nexti1, 0, cur.cnt+1));
                    }
                }
            }
        } else if(N==1) {
            queue.add(new Info(arr[0], -1, 0, 0));

            while(!queue.isEmpty()) {
                Info cur = queue.poll();
                if(cur.i0<=0) {
                    rst = Math.min(rst, cur.cnt);
                    return;
                }

                for(int i=0; i<1; i++) {
                    if(cur.cnt<rst) {
                        int nexti0 = Math.max(cur.i0 - val3[i][0], 0);
                        if(dp[nexti0][0][0]==0) {
                            dp[nexti0][0][0] = dp[cur.i0][0][0]+1;
                            queue.add(new Info(nexti0, 0, 0, cur.cnt+1));
                        }
                        queue.add(new Info(cur.i0-val3[i][0], 0, 0, cur.cnt+1));
                    }
                }
            }
        }
    }

    static class Info {
        int i0;
        int i1;
        int i2;
        int cnt;

        public Info(int i0, int i1, int i2, int cnt) {
            this.i0 = i0;
            this.i1 = i1;
            this.i2 = i2;
            this.cnt = cnt;
        }
    }
}

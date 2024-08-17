package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// search) 위상 정렬, 중간부품간 사이클이 없다.
public class BOJ2637 {
    static int N, M;
    static int[] parent; // 위상정렬 배열 -> parent[i]: i를 만들기 위해서 필요한 재료의 종류 수
    static boolean[] 기본부품;
    static ArrayList<Info>[] list;
    static Queue<Info> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 부품 번호(1~N)
        M = Integer.parseInt(br.readLine()); // 부품간의 관계 개수
        기본부품 = new boolean[N+1]; // 기본 부품인지 판단
        Arrays.fill(기본부품, true);
        parent = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // X를 만드는데 Y가 K개 필요하다
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            기본부품[X] = false;
            parent[Y]++;
            list[X].add(new Info(Y, K));
        }

        int[] results = topologySort(N);
        for(int i=1; i<=N; i++) {
            if(기본부품[i])
                System.out.println(i+" "+results[i]);
        }
    }

    /**
     * 일반적인 위상 정렬과 다르게, 역순으로 넣는다.
     */
    public static int[] topologySort(int n) {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(n, 1));
        // 재료가 몇개 필요한지 저장하는 배열
        int[] counter = new int[n+1];
        counter[n] = 1;

        while(!q.isEmpty()) {
            Info cur = q.poll();

            for(int i=0; i<list[cur.num].size(); i++) {
                Info next = list[cur.num].get(i); // cur을 만들기 위해서 필요한 재료 정보
                counter[next.num]+=(counter[cur.num]*next.cnt); // 역순으로 가면서 재료가 몇 개 필요한지를 추가 (곱)
                parent[next.num]--;
                if(parent[next.num]==0) {
                    q.offer(new Info(next.num, counter[next.num]));
                }
            }
        }
        return counter;
    }

    static class Info {
        int num;
        int cnt;

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
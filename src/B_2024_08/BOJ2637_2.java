package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2637_2 {
    static int N, M;
    static int[] parent; // 위상정렬
    static boolean[] basic; // 기본부품인지
    static ArrayList<Info>[] list;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        basic = new boolean[N+1];
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(basic, true);
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()); // 만들것
            int Y = Integer.parseInt(st.nextToken()); // 재료
            int K = Integer.parseInt(st.nextToken()); // 개수
            basic[X] = false;
            parent[Y]++; // 위상정렬
            list[X].add(new Info(Y, K));
        }

        int[] counter = new int[N+1];
        q.add(N);
        counter[N] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i=0; i<list[cur].size(); i++) {
                Info next = list[cur].get(i);
                counter[next.n] += (counter[cur]*next.cnt);
                parent[next.n]--;
                if(parent[next.n]==0)
                    q.add(next.n);
            }
        }

        for(int i=1; i<=N; i++) {
            if(basic[i])
                System.out.println(i+" "+counter[i]);
        }
    }

    static class Info {
        int n;
        int cnt;
        public Info(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
}
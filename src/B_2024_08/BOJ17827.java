package B_2024_08;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ17827 {
    static int N, M, V, K;
    static int[] C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드의 개수
        M = Integer.parseInt(st.nextToken()); // 질문의 횟수
        V = Integer.parseInt(st.nextToken()); // 노드의 번호
        C = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            K = Integer.parseInt(br.readLine()); // K번 이동
            if(K+1<V) {
                bw.write(C[K+1]+"\n");
                continue;
            }

            K-=(V-1); // 사이클내 이동 횟수
            int cycleStd = N-(V-1); // 사이클 크기

            bw.write(C[V+K%cycleStd]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
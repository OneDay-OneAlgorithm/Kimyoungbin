package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// WA1) 지뢰 힘이 같다면? 어떤 순서대로 터트려야 하지
// search) 그냥 방향성 (어느 한쪽에서도 연쇄적으로 터지지 못할 경우 == 좌우보다 모두 클경우)
public class BOJ2232 {
    static int N;
    static int[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        graph = new int[N];

        for(int i=0; i<N; i++) {
            graph[i]=Integer.parseInt(br.readLine());
        }
        //개수가 1개인 경우
        if(graph.length==1)
            System.out.println(1);
        else {
            if(graph[0]>=graph[1])
                System.out.println(1);
            for(int i=1; i<=N-2; i++){
                if(graph[i-1]<=graph[i] && graph[i]>=graph[i+1])
                    sb.append(i+1).append("\n");
            }
            if(graph[N-2]<=graph[N-1]){
                sb.append(N).append("\n");
            }
            System.out.println(sb);
        }
    }
}
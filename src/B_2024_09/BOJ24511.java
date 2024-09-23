package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// TLE1) 단순 구현시 O(NM)이라 시간초과 남 -> 하나의 큐로 구현
public class BOJ24511 {
    static int N, M;
    static Queue<Integer> queue;
    static int[] info, B;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();
        info = new int[N];
        B = new int[N];

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        for(int i=N-1; i>=0; i--) {
            if(info[i]==0) {
                queue.add(B[i]);
            }
        }

        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
            sb.append(queue.poll()).append(" ");
        }
        System.out.println(sb);
    }

}

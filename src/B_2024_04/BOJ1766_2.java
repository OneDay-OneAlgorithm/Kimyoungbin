package B_2024_04;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

// 위상 정렬 - PriorityQueue
public class BOJ1766_2
{
    static int N, M;
    static ArrayList<Integer>[] list;
    static int[] D; // 진입차수 배열
    static Queue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 문제 개수
        M = Integer.parseInt(line_1[1]); // 정보
        list = new ArrayList[N+1];
        D = new int[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_2 = br.readLine().split(" ");
            int first = Integer.parseInt(line_2[0]);
            int second = Integer.parseInt(line_2[1]);
            list[first].add(second);
            D[second]++;
        }
        for(int i=1; i<=N; i++) {
            if(D[i]==0) {
                pq.add(i); // 먼저 풀어야하는 문제가 없는 경우 큐에 넣기
            }
        }

        while(!pq.isEmpty()) {
            int next = pq.peek();
            if(D[next]==0) { // 먼저 풀어야하는 문제가 없으면
                pq.poll();
                bw.write(next+" ");
                for(int i=0; i<list[next].size(); i++) {
                    D[list[next].get(i)]--;
                    if(D[list[next].get(i)]==0) { // 이번 문제를 풂으로서 선행문제가 없어지면 큐에 넣기
                        pq.add(list[next].get(i));
                    }
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
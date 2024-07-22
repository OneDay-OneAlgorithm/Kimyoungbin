package B_2024_07;

import java.io.*;
import java.util.*;

// 위상 정렬 + 사이클 판별(사이클이 있으면 정렬이 안됨 -> 정렬후 길이가 N과 같은지 확인)
// 중복된 입력 제거
public class BOJ2623 {
    static int N, M;
    static int[] before;
    static LinkedList<Integer>[] list;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[][] isDuplicate; // 솔직히 문제 좀 그렇다
    static int printNum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        before = new int[N+1];
        list = new LinkedList[N+1];
        isDuplicate = new boolean[N+1][N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int k = Integer.parseInt(line_M[0]);
            for(int j=1; j<k; j++) {
                int start = Integer.parseInt(line_M[j]);
                int end = Integer.parseInt(line_M[j+1]);
                list[start].add(end);
                if(!isDuplicate[start][end]) { // 중복된 입력 제거
                    before[end]++;
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(before[i]==0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append("\n");
            printNum++;

            for(int i=0; i<list[cur].size(); i++) {
                int next = list[cur].get(i);
                before[next]--;
                if(before[next]==0) {
                    pq.add(next);
                }
            }
        }

        // 사이클이 있으면 정렬이 안됨 -> 정렬후 길이가 N과 같은지 확인
        if(printNum==N)
            System.out.println(sb);
        else
            System.out.println(0);
    }
}
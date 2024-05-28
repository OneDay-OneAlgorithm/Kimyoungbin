package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// search) 에드몬드-카프 알고리즘 (A에서 B까지 최대 유량 구하기)
// 1. BFS를 통해서 시작->도착점으로 갈 수 있는 모든 경로를 찾는다.
// 2. 경로를 거슬러 가며 해당 경로로 보낼 수 있는 최대 유량을 구한다.
// 3. 도착점에서 시작점까지 거슬러 올라가며 잔여 용량을 계산한다.
// 4. 1~3을 반복한다.

// WA1) 'Z'=90, 'a'=97 (이어져있지 않음)
// WA2) 중복 파이프가 있을 수 있다..
public class BOJ6086 {
    static int N, rst = 0;
    static int[] prev = new int[52];
    static int[][] arr = new int[52][52]; // 남은 파이프 유량
    static Queue<Integer> queue;
    static final int START = 0, END = 'Z'-'A', MAX = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int s = charToInt(line_N[0].charAt(0));
            int e = charToInt(line_N[1].charAt(0));
            int val = Integer.parseInt(line_N[2]);
            arr[s][e] += val; // WA2)
            arr[e][s] += val;
        }

        while(true) {
            // 재탐색을 위한 경로 초기화
            Arrays.fill(prev, -1);
            bfs();

            // 경로가 없다면 반복문 탈출
            if(prev[END]==-1) break;
            // 경로 재탐색을 통해 잔여유량 갱신
            rst += calculateFlow();
        }
        System.out.println(rst);
    }

    // 경로 재탐색을 통해 잔여유량을 갱신한다
    private static int calculateFlow() {
        int water = MAX;

        int cur = END;
        // 경로 재탐색하며 가장 작은 유량 계산
        while(cur!=START) {
            water = Math.min(water, arr[prev[cur]][cur]);
            cur = prev[cur];
        }
        // 잔여 용량 계산
        cur = END;
        while(cur!=START) {
            arr[prev[cur]][cur]-=water;
            arr[cur][prev[cur]]+=water; // 음의 유량 고려 (상쇄)
            cur = prev[cur];
        }

        return water;
    }

    // 일반적인 bfs와 다르게, 용량이 꽉 찬 파이프는 지나갈 수 없으며, 재탐색을 위한 경로를 저장한다.
    private static void bfs() {
        queue = new ArrayDeque<>();
        queue.add(START);
        prev[START] = START;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur==END) break;

            for(int i=0; i<52; i++) {
                // 파이프 용량이 남아있고, 방문하지 않은 노드일 경우
                if(arr[cur][i]>0 && prev[i]==-1) {
                    queue.add(i);
                    prev[i] = cur;
                }
            }
        }
    }

    // WA1) 'A'~'z'가 이어져있지 않음
    static int charToInt(char c) {
        if('A'<=c && c<='Z') return c-'A';
        if('a'<=c && c<='z') return c-'a'+26;
        return -1;
    }
}

package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 왜 이게 TL이야?
public class BOJ1092
{
    static int N, M;
    static int[] cranes;
    static int[] boxes;
    static int[] craneIdx;
    static boolean[] visited; // 박스를 옮겼는지 체크
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cranes = new int[N];
        craneIdx = new int[N];
        String[] line_C = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            cranes[i] = Integer.parseInt(line_C[i]);
        }
        M = Integer.parseInt(br.readLine());
        boxes = new int[M];
        visited = new boolean[M];
        Arrays.fill(craneIdx, M-1); // 짐을 옮길 수 있는지 맨 뒤부터 검사
        String[] line_B = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            boxes[i] = Integer.parseInt(line_B[i]);
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);


        // N*M이 작으므로 그냥 큰 크레인부터 돌려가면서 박스 처리
        int time = 0;
        int movedBox = 0;
        boolean flag = true; // 박스를 배로 옮길 수 없는지 체크
        while(movedBox<M) {
            if(!flag) {
                System.out.println(-1);
                return;
            }
            flag = false;
            // N개의 크레인이 동작 가능한지를 검사한 후 time 증가
            for(int i=N-1; i>=0; i--) {
                // 동일한 로직
                while(craneIdx[i]>=0) {
                    if(!visited[craneIdx[i]] && boxes[craneIdx[i]]<=cranes[i]) {
                        visited[craneIdx[i]] = true;
                        movedBox++;
                        flag = true;
                        break;
                    }
                    craneIdx[i]--;
                }
            }
            time++;
        }
        System.out.println(time);
    }
}
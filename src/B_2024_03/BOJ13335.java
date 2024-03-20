package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ13335
{
    static int n, w, L; // 트럭 수, 다리 길이, 최대하중
    static int[] truck;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        w = Integer.parseInt(line_1[1]);
        L = Integer.parseInt(line_1[2]);
        truck = new int[n];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            truck[i] = Integer.parseInt(line_2[i]);
        }

        int bridge = 0; // 다리 하중
        int time = 0; // 소요 시간
        int idx = 0; // 올라갈 트럭

        while(idx<n) {
            int next = truck[idx];

            // 트럭이 추가로 올라갈 수 있다면
            if(bridge+truck[idx]<=L) {
                queue.add(next);
                bridge+=truck[idx];
                idx++;
            } else { // 트럭이 못올라가면
                queue.add(0);
            }

            // 다리가 꽉 찼으면
            if(queue.size()>=w) {
                bridge-=queue.poll();
            }
            time++;
        }

        // 마지막 트럭이 올라간상태에서 종료
        System.out.println(time+w);
    }
}
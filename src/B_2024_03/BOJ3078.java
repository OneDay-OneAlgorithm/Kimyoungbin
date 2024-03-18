package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 등수의 차이가 K 이하이면서 이름의 길이가 같은 사람 쌍 구하기
// 큐 + 배열?
public class BOJ3078
{
    static int N, K;
    static Queue<Integer> queue = new LinkedList<>();
    static int[] nameLen = new int[21]; // 현재 큐에있는 같은길이 이름 사람 수
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);

        for(int i=0; i<N; i++) {
            int length = br.readLine().length(); // 새로 들어온 이름 길이
            if(queue.size()>K) {
                int delLen = queue.poll();
                nameLen[delLen]--;
            }
            rst += nameLen[length]; // 현재 큐에있는 같은이름 사람수 더하기

            // 본인 추가
            queue.add(length);
            nameLen[length]++;
        }

        System.out.println(rst);
    }
}
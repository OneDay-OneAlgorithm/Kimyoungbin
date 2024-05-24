package SOFTEER_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 배열을 이용한 트리 문제
// 사람을 큐 2개로 표현 (왼쪽이 올린 업무, 오른쪽이 올린 업무)
// 부서장 포함 모든 직원은 날짜에 따라 왼쪽 or 오른쪽 직원이 올린 업무를 처리
// 부하가 올린 업무는 다음날에 처리
// 높이 H면 2^H번부터 말단직원
public class 업무_처리 {
    static int H, K, R, rst = 0;
    static int N;
    static Queue<Integer>[][] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        H = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        R = Integer.parseInt(line_1[2]);
        N = (int)Math.pow(2, H+1); // N:직원수 (1번~2^(H+1)-1번까지 존재)

        list = new LinkedList[N][2];
        for(int i=1; i<N; i++) {
            list[i][0] = new LinkedList<>(); // 왼쪽 직원이 올린 업무
            list[i][1] = new LinkedList<>(); // 오른쪽 직원이 올린 업무
        }

        // 말단직원 업무 주입
        for(int i=(int)Math.pow(2, H); i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<K; j++) {
                if(j%2==0)
                    list[i][0].add(Integer.parseInt(line_N[j]));
                else
                    list[i][1].add(Integer.parseInt(line_N[j]));
            }
        }

        // 상사는 다음날에 일을 처리하므로 앞에서부터 처리하면 됨
        int day = 1;
        while(day<=R) {
            // 부서장 짝수날
            if(day%2==0 && !list[1][1].isEmpty()) {
                rst+=list[1][1].poll();
            } else if(!list[1][0].isEmpty()){
                rst+=list[1][0].poll();
            }

            // 업무 수행, 올리기
            for(int i=2; i<N; i++) {
                if(day%2==0) { // 짝수날엔 오른쪽 처리
                    if(!list[i][1].isEmpty()) {
                        // i가 짝수면 왼쪽직원, 홀수면 오른쪽직원
                        if(i%2==0)
                            list[i/2][0].add(list[i][1].poll());
                        else
                            list[i/2][1].add(list[i][1].poll());
                    }
                } else {
                    if(!list[i][0].isEmpty()) {
                        // i가 짝수면 왼쪽직원, 홀수면 오른쪽직원
                        if(i%2==0)
                            list[i/2][0].add(list[i][0].poll());
                        else
                            list[i/2][1].add(list[i][0].poll());
                    }
                }
            }

            day++;
        }

        System.out.println(rst);
    }
}

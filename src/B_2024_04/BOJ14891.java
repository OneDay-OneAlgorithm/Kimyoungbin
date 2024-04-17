package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 톱니바퀴 구현 -> Deque 2개 (첫번째: 오른쪽 출구, 두번째: 왼쪽)
// 실제 회전 전에 3개 구간의 회전여부는 정해져있다
public class BOJ14891 {
    static int K;
    static Deque<Integer>[][] list = new Deque[5][2];
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1; i<=4; i++) {
            for(int j=0; j<2; j++) {
                list[i][j] = new LinkedList<>();
            }
        }
        for(int i=1; i<=4; i++) {
            // 2->1->0->7
            String[] status = br.readLine().split("");
            for(int j=2; j>=0; j--) {
                list[i][0].addLast(Integer.parseInt(status[j]));
            }
            list[i][0].addLast(Integer.parseInt(status[7]));
            // 6->5->4->3
            for(int j=6; j>=3; j--) {
                list[i][1].addLast(Integer.parseInt(status[j]));
            }
        }
        K = Integer.parseInt(br.readLine());
        for(int k=0; k<K; k++) {
//            System.out.println("=======");
            boolean[] section = new boolean[5]; // 3개의 구역에 대해서 회전여부 체크, 양 옆 마진
            boolean[] isRotated = new boolean[5]; // 4개의 톱니바퀴가 돌아갔는지 여부 체크
            for(int i=1; i<=3; i++) {
                section[i] = list[i][0].peekFirst()!=list[i+1][1].peekFirst();
//                System.out.print(section[i]);
            }
//            System.out.println();
            String[] method = br.readLine().split(" ");
            int no = Integer.parseInt(method[0]); // 회전 톱니바퀴 번호
            int dir = Integer.parseInt(method[1]); // 회전 방향
            int tmpdir = dir;
            // 대상 톱니바퀴 회전
            rotate(no, dir);
            isRotated[no] = true;
//            System.out.println("KK");
            // 왼쪽에 위치한 톱니바퀴 순차 회전
            for(int i=no-1; i>=1; i--) {
                if(section[i] && isRotated[i+1]) { // 서로 다른 극이고, 이전 톱니바퀴가 움직여야 함
                    rotate(i, tmpdir*=-1);
                    isRotated[i] = true;
                }
            }
//            System.out.println("KK");
            tmpdir = dir;
            // 오른쪽에 위치한 톱니바퀴 순차 회전
            for(int i=no+1; i<=4; i++) {
                if(section[i-1] && isRotated[i-1]) { // 서로 다른 극이고, 이전 톱니바퀴가 움직여야 함
                    rotate(i, tmpdir*=-1);
                    isRotated[i] = true;
                }
            }
//            System.out.println("=======");
        }

        int[] score = {0,1,2,4,8};
        for(int i=1; i<=4; i++) {
            // 3번째 꺼낸 원소가 12시에 위치함
            list[i][0].pollLast();
            int plus = list[i][0].pollLast()==1?score[i]:0;
            rst+=plus;
        }

        System.out.println(rst);
    }

    // i번 톱니바퀴를 시계방향(1) 또는 반시계방향(-1)로 회전
    private static void rotate(int n, int dir) {
//        System.out.println("n = " + n + ", dir = " + dir);
        // 시계방향 회전
        if(dir==1) {
            list[n][1].addLast(list[n][0].pollFirst());
            list[n][0].addLast(list[n][1].pollFirst());
        } else { // 반시계방향 회전
            list[n][0].addFirst(list[n][1].pollLast());
            list[n][1].addFirst(list[n][0].pollLast());
        }
    }
}
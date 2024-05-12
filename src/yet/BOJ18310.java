package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 중간좌표에서 가장 가까운 집 - 평균값으로부터 좌우탐색
// WA 1) 4, 1 1 2 10 케이스에서 답이 1, 2 모두 되지만 1이어야 함
// WA 2) 왜 평균값은 불가능한가? -> 반례: 5, 1 7 8 9 10 (평균값에 가까운것이 답이 아니라는 반례가 있다)
public class BOJ18310 {
    static int N;
    static int[] arr;
    static boolean[] town = new boolean[100001];
    static double avg;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
            town[arr[i]] = true;
            avg += arr[i];
        }
        avg/=N;

        int l=0, r=100000;
        long min = Long.MAX_VALUE;
        // 왼쪽 탐색
        for(int i=(int)avg; i>=0; i--) {
            if(town[i]) {
//                System.out.println("min = " + min);
                if(min<getDist(i)) { // 이전에 구한 답이 정답일 경우 (이번에 구한 값이 이전보다 클 경우)
                    break;
                }
                l = i;
//                System.out.println("l = " + l);
                min = getDist(i);
            }
        }
        for(int i=(int)avg+1; i<=100000; i++) {
            if(town[i]) {
                r = i;
                break;
            }
        }

//        System.out.println("======");
        System.out.println("l = " + l);
        System.out.println("r = " + r);
        System.out.println((avg-l)<=(r-avg)?l:r);
    }

    // n에 안테나를 설치했을 때 거리 총합
    private static long getDist(int n) {
        long rst = 0;
        for(int i=0; i<N; i++) {
            rst+=Math.abs(arr[i]-n);
        }
        return rst;
    }
}
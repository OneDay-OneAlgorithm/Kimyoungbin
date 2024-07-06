package B_2024_07;

import java.util.*;
import java.io.*;

// 반례가 어려운 문제 - search
// 반례) 3개짜리일 때, 3번째보다 2번쨰값이 크다면 2번째값이 3번째값보다 작거나 같도록 만들어야 함
public class BOJ18185 {
    static int N, cost = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        while(true) {
            if(!buy3()) {
                if(!buy2()) {
                    buy1();
                    break;
                }
            }
        }

        System.out.println(cost);
    }


    private static boolean buy3() {
        boolean flag = false;
        for(int i=0; i<N-2; i++) {
            if(arr[i]>0 && arr[i+1]>0 && arr[i+2]>0) {
                if(arr[i+1]>arr[i+2]) {
                    while(arr[i+1]>arr[i+2] && arr[i]>=1 && arr[i+1]>=1) {
                        arr[i]--;
                        arr[i+1]--;
                        cost += 5;
                        flag = true;
                    }
                    return flag;
                } else {
                    int min = Math.min(Math.min(arr[i], arr[i+1]), arr[i+2]);
                    arr[i]-=min;
                    arr[i+1]-=min;
                    arr[i+2]-=min;
                    cost += (7*min);
                    flag = true;
                }
            }
        }
        return flag;
    }

    private static boolean buy2() {
        boolean flag = false;
        for(int i=0; i<N-1; i++) {
            if(arr[i]>0 && arr[i+1]>0) {
                int min = Math.min(arr[i], arr[i+1]);
                arr[i]-=min;
                arr[i+1]-=min;
                cost += (5*min);
                flag = true;
            }
        }
        return flag;
    }

    private static boolean buy1() {
        boolean flag = false;
        for(int i=0; i<N; i++) {
            if(arr[i]>0) {
                int min = arr[i];
                arr[i]-=min;
                cost += (3*min);
                flag = true;
            }
        }
        return flag;
    }

}
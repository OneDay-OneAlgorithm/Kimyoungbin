package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//풀이 방법: (가장 작은 높이 ~ 가장 큰 높이) 사이에 드는 cost와 가능 여부를 일일히 체크하고 가장 작은 것을 구한다.
public class BOJ18111
{
    static int N, M, B;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[][] arr;
    static int minCost = Integer.MAX_VALUE;
    static int minHeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        B = Integer.parseInt(line_1[2]);

        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(line_N[j]);
                if(arr[i][j]<min) min = arr[i][j];
                if(arr[i][j]>max) max = arr[i][j];
            }
        }

        for(int h=min; h<=max; h++) {
            int curCost = getCost(h, B);
            if((minCost>=curCost) && curCost>=0) { //minCost >= curCost로 해주어야 업데이트가 됨
                minCost = curCost;
                minHeight = h;
            }
        }

        System.out.println(minCost + " " + minHeight);
    }

    //targetHeight: 목표로 하는 높이
    private static int getCost(int targetHeight, int availableBlock) {
        int cost = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 블록 제거 (cost:2)
                if(arr[i][j]-targetHeight>0) {
                    cost += (arr[i][j]-targetHeight)*2;
                    availableBlock+=(arr[i][j]-targetHeight);
                }
                // 블록 추가 (cost:1)
                else {
                    cost += targetHeight-arr[i][j];
                    availableBlock-=(targetHeight-arr[i][j]);
                }
            }
        }
        if(availableBlock<0) return -1;
        return cost;
    }
}

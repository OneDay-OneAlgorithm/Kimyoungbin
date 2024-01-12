package P_2024_01;

import java.util.*;

// 10개 과녁에 대해서 점수획득/미획득으로 백트래킹
class 양궁대회 {
    int 최고점수차 = Integer.MIN_VALUE;
    int[] array = new int[11];
    int[] rst = new int[11];
    public int[] solution(int n, int[] info) {
        int 어피치 = 0;
        for(int i=0; i<info.length; i++) {
            if(info[i]!=0) 어피치+=(10-i);
        }

        dfs(info, 0, n, 어피치, 0);
        System.out.println(최고점수차);
        if(최고점수차>0) return rst;
        else return new int[]{-1};
    }

    // n: 남은 화살 개수
    private void dfs(int[] info, int depth, int n, int 어피치, int 라이언) {
        if(n<=0 || depth==11) { // 화살을 더 이상 쓸수 없거나, 더이상 맞출 수 있는 점수가 없으면
            if(최고점수차<라이언-어피치) {
                최고점수차 = 라이언-어피치;
                array[10] = n; // 케이스 4) 화살이 남으면 낮은점수에 쏴주면 된다.
                rst = array.clone();
            } else if(최고점수차==라이언-어피치) { // 케이스 3) 동일점수일 경우 낮은 점수를 더 많이 맞춘경우로 리턴
                for(int i=10; i>=0; i--) {
                    if(rst[i]<array[i]) {
                        rst = array.clone();
                        break;
                    } else if(rst[i]>array[i]) {
                        break;
                    }
                }
            }
            return;
        } else {
            // 점수 딸 수 있는경우 따기
            if(n-info[depth]-1>=0) {
                array[depth] = info[depth]+1; // 어파치보다 1개를 더 쏘아야함
                // 어피치가 맞춘경우 점수 빼주기, 안맞춘경우 점수 유지
                int 다음어피치 = info[depth]!=0?어피치-(10-depth):어피치;
                dfs(info, depth+1, n-info[depth]-1, 다음어피치, 라이언+(10-depth));
                array[depth] = 0;
            }
            // 점수 안따기
            dfs(info, depth+1, n, 어피치, 라이언);
        }
    }

}
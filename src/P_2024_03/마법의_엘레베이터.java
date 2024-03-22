package P_2024_03;

//10^n꼴로 표현되므로 각 자리수 k마다 더하거나 빼서 10^(k+1)로 나누어 떨어지게 해야함
//storey가 1억 미만이므로 자리수마다 2개의 분기가 있으므로 2^9? 백트래킹으로 풀 수 있다.
import java.util.*;
public class 마법의_엘레베이터 {
    int ans = Integer.MAX_VALUE;
    int 자릿수;
    public int solution(int storey) {
        자릿수 = (int)(Math.log10(storey)+1);
        getRst(0, 0, storey);
        return ans;
    }

    //use:마법의 돌 사용개수
    //depth: 일의자리부터 0으로 맞춰주기
    //val: 엘레베이터 위치
    private void getRst(int use, int depth, int val) {
        if(depth==자릿수+1 || val==0) {
            System.out.println("***use="+use+", depth="+depth+", val="+val);
            ans = Math.min(ans, use);
            return;
        }


        int 이번자릿수 = (int)Math.pow(10, depth);
        int 하강 = val%(이번자릿수*10)/이번자릿수; // 이번 자릿수를 0으로 만들기 위해서 빼주는 횟수
        int 상승 = 10 - 하강; // 이번 자릿수를 0으로 만들기 위해서 더해주는 횟수

        System.out.println("use="+use+", depth="+depth+", val="+val);
        System.out.println("이번자릿수="+이번자릿수+", 하강="+하강+", 상승="+상승);

        getRst(use+하강, depth+1, val-하강*이번자릿수);
        getRst(use+상승, depth+1, val+상승*이번자릿수);
    }
}

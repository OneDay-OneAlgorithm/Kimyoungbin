package P_2024_01;

import java.util.*;

class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int ans = 0;
        String[] arr = Integer.toString(n, k).split("0");

        for(String data: arr){
            if(data.equals("")) continue;
            long num = Long.parseLong(data);
            if(isPrime(num)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isPrime(long a) {
        if(a<2) return false;

        for(int i=2; i<=Math.sqrt(a); i++) {
            if(a%i==0) {
                return false;
            }
        }
        return true;
    }
}

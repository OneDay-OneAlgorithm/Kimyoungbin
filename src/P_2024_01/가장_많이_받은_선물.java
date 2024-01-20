package P_2024_01;

import java.util.*;

class 가장_많이_받은_선물 {
    HashMap<String, Integer> map = new HashMap<>();
    int[][] 선물기록;
    int[] 선물지수;
    int[] 다음달받을선물;
    public int solution(String[] friends, String[] gifts) {
        선물기록 = new int[friends.length][friends.length];
        선물지수 = new int[friends.length];
        다음달받을선물 = new int[friends.length];
        for(int i=0; i<friends.length; i++) {
            map.put(friends[i], i);
        }
        for(int i=0; i<gifts.length; i++) {
            String sender = gifts[i].split(" ")[0];
            String receiver = gifts[i].split(" ")[1];
            선물기록[map.get(sender)][map.get(receiver)]++;
            선물지수[map.get(sender)]++;
            선물지수[map.get(receiver)]--;
        }

        for(int i=0; i<friends.length; i++) {
            for(int j=0; j<friends.length; j++) {
                if(선물기록[i][j]==선물기록[j][i]) {
                    if(선물지수[i]>선물지수[j]) {
                        다음달받을선물[i]++;
                    } else if(선물지수[i]<선물지수[j]) {
                        다음달받을선물[j]++;
                    }
                } else {
                    if(선물기록[i][j]>선물기록[j][i]) {
                        다음달받을선물[i]++;
                    } else {
                        다음달받을선물[j]++;
                    }
                }
            }
        }

        int rst = Integer.MIN_VALUE;
        for(int i=0; i<friends.length; i++) {
            rst = Math.max(rst, 다음달받을선물[i]);
        }
        return rst/2; // 2번씩 계산되는것 제외
    }
}

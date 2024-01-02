package P_2024_01;

import java.util.*;

class 압축 {
    HashMap<String, Integer> map = new HashMap<>();
    List<Integer> list = new LinkedList<>();
    public int[] solution(String msg) {
        // initialize
        for(int i=1; i<=26; i++) {
            String strkey = String.valueOf((char)('A'+i-1));
            map.put(strkey, i);
        }

        for(int i=0; i<msg.length(); ) {
            int len = 1;
            int val = -1;
            // 사전에 포함되어 있는 가장 긴 문자열 찾기
            while(map.containsKey(msg.substring(i, i+len))) {
                val = map.get(msg.substring(i, i+len));

                // 맨 마지막 글자까지 간 경우는 더하면 안됨
                if(i+len>=msg.length()) {
                    list.add(val);
                    return printrst();
                }
                len++;
            }

            // 사전에 새로운 단어 추가
            map.put(msg.substring(i, i+len), map.size()+1);
            // 출력 저장
            list.add(val);
            // 사전에 n글자가 새로 저장되었다면 n-1
            i+=(len-1);
        }

        return printrst();
    }

    private int[] printrst() {
        // convert List<Integer> to int[]
        Object[] objrst = list.toArray();
        int[] rst = new int[objrst.length];
        for(int i=0; i<objrst.length; i++) {
            rst[i] = (int)objrst[i];
        }
        return rst;
    }
}
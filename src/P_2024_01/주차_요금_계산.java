package P_2024_01;

import java.util.*;

class 주차_요금_계산 {
    int[] 주차시간 = new int[10000];
    boolean[] 주차여부 = new boolean[10000]; //기본요금이 0일경우 대비
    int[] fee = new int[10000];
    HashMap<Integer, Integer> car = new HashMap<>(); // <차량변호, 입차시각>
    public List<Integer> solution(int[] fees, String[] records) {
        List<Integer> ans = new LinkedList<>();
        int 기본시간 = fees[0];
        int 기본요금 = fees[1];
        int 단위시간 = fees[2];
        int 단위요금 = fees[3];

        for(String record: records) {
            String[] line = record.split(" ");
            int 시각 = Integer.parseInt(line[0].split(":")[0])*60 + Integer.parseInt(line[0].split(":")[1]);
            int 차량번호 = Integer.parseInt(line[1]);
            int 내역 = line[2].equals("IN")?1:0;

            // 주차 시간만 계산
            if(내역==1) { // 입차
                car.put(차량번호, 시각);
                주차여부[차량번호] = true;
            } else { // 출차
                int 입차시각 = car.get(차량번호);
                주차시간[차량번호] += (시각-입차시각);
                car.remove(차량번호); // 출차처리시 remove
            }
        }

        // 출차처리 안된 차량의 경우 23:59로 계산
        for(int i=0; i<10000; i++) {
            if(car.containsKey(i)) {
                int 입차시각 = car.get(i);
                주차시간[i] += 23*60+59-입차시각;
                car.remove(i);
            }
        }

        // 주차비 계산
        for(int i=0; i<10000; i++) {
            if(주차시간[i]!=0) {
                int 추가요금 = 주차시간[i]<=기본시간?0:(int)Math.ceil((주차시간[i]-기본시간)/(double)단위시간)*단위요금;
                fee[i] = (기본요금+추가요금);
            }
        }

        for(int i=0; i<10000; i++) {
            if(주차여부[i])
                ans.add(fee[i]);
        }
        return ans;
    }
}
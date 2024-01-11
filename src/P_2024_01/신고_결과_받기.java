package P_2024_01;

import java.util.*;

class 신고_결과_받기 {
    HashMap<String, Integer> userMap = new HashMap<>();
    public int[] solution(String[] id_list, String[] report, int k) {
        int userNum = id_list.length;
        boolean[][] reportArr = new boolean[userNum][userNum];
        boolean[] isBanned = new boolean[userNum];
        int[] rst = new int[userNum];
        int userCnt = 0;

        // 유저 id를 고유 int pk로 매핑
        for(String id: id_list) {
            userMap.put(id, userCnt++);
        }
        // 누가 누굴 신고했는지에 대한 정보 수집
        for(String r: report) {
            String[] rlin = r.split(" ");
            int reportBy = userMap.get(rlin[0]);
            int reportTo = userMap.get(rlin[1]);
            reportArr[reportBy][reportTo] = true;
        }
        // 밴된 유저 정보 정리
        for(int i=0; i<userNum; i++) {
            int cnt = 0;
            for(int j=0; j<userNum; j++) {
                if(reportArr[j][i]) cnt++;
            }
            isBanned[i] = cnt>=k?true:false;
        }
        // 유저별 메일 받은 횟수 리턴
        for(int i=0; i<userNum; i++) {
            int userReportCnt = 0;
            for(int j=0; j<userNum; j++) {
                if(reportArr[i][j] && isBanned[j]) { // 신고를 한 유저가 정지를 당해야
                    userReportCnt++;
                }
            }
            rst[i] = userReportCnt;
        }

        return rst;
    }
}
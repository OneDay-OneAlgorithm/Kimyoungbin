package SWEA_2024_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class No2
{
    static int N, day;
    static int[] cur, next; // 현재, 다음 순서에 학생들이 갖고 있는 족보 정보
    static int[] day1; // 홀수날에 info[i]=j면 i번학생은 다음날 j번학생에게 족보 전달
    static int[] day2; // 짝수날에 info[i]=j면 i번학생은 다음날 j번학생에게 족보 전달
    static boolean[] seen; // 볼 수 있는 족보를 모두 봤는지 체크
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            day1 = new int[N+1];
            day2 = new int[N+1];
            cur = new int[N+1];
            next = new int[N+1];
            seen = new boolean[N+1];

            // input
            String[] day_1 = br.readLine().split(" ");
            for(int i=1; i<=N; i++) {
                day1[i] = Integer.parseInt(day_1[i-1]);
            }
            String[] day_2 = br.readLine().split(" ");
            for(int i=1; i<=N; i++) {
                day2[i] = Integer.parseInt(day_2[i-1]);
            }

            for(int i=1; i<=N; i++) {
                cur[i] = day1[i];
                if(day%2==1) {
                    if(cur[i]==day1[i]) seen[i] = true;
                } else {
                    if(cur[i]==day2[i]) seen[i] = true;
                }
                if(cur[i]==i) seen[i]=true;
            }

            day = 1;
            while(!allSeen() && day<6) {
                printHint();
                // 족보 전달
                proceed();
                // 정보 체크
                for(int i=1; i<=N; i++) {
                    if(cur[i]==i) seen[i]=true;
                }
                day++;
            }

            // 표준출력(화면)으로 답안을 출력합니다.
            System.out.println("#" + test_case+" "+day);
        }
    }

    private static boolean allSeen() {
        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(seen[i]) {
                cnt++;
            }
        }

        return cnt==N;
    }

    // cur 배열 업데이트
    private static void proceed() {
        // 족보 전달
        if(day%2==0) {
            for(int i=1; i<=N; i++) {
                next[day1[i]] = cur[i];
            }
        } else {
            for(int i=1; i<=N; i++) {
                next[day2[i]] = cur[i];
            }
        }
        // next -> cur 복제
        for(int i=1; i<=N; i++) {
            cur[i] = next[i];
        }
    }

    private static void printHint() {
        for (int i : cur) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}

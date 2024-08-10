package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// E = (모든 알고리즘 - 공통 알고리즘)*그룹원
public class BOJ14572 {
    static int N, K, D, algoCnt;
    static Info[] arr;
    static int[] algo;
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사람 수
        K = Integer.parseInt(st.nextToken()); // 알고리즘 수
        D = Integer.parseInt(st.nextToken()); // 실력 차이 최대값

        arr = new Info[N];
        algo = new int[K+1]; // algo[i]: 알고리즘 i를 아는 사람의 수
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 아는 알고리즘
            int d = Integer.parseInt(st.nextToken()); // 실력
            arr[i] = new Info(d, M);
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i].list.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 실력 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> o1.skill-o2.skill);

        int l = 0, r = 0;
        algoCnt = 0; // 한명이라도 알고있는 알고리즘 수
        for(int i=0; i<arr[0].count; i++) {
            int algoNum = arr[r].list.get(i);
            algoCnt++;
            algo[algoNum]++;
        }
        while(true) {
//            System.out.println("l = " + l + ", r = " + r);
            // 잘하는 학생-못하는 학생 차이가 D 이하
            if(arr[r].skill-arr[l].skill<=D) { // 잘하는 사람 포함
                rst = Math.max(rst, getEfficiency(algoCnt, algoAllKnows(r-l+1), r-l+1));
                // 잘하는 사람 한명 포함하고, 범위 초과시 종료
                r++;
                if(r>=N) break;

                for(int i=0; i<arr[r].count; i++) {
                    int algoNum = arr[r].list.get(i);
                    if(algo[algoNum]==0) {
                        algoCnt++;
                    }
                    algo[algoNum]++;
                }
            } else { // 못하는 사람 제거
                for(int i=0; i<arr[l].count; i++) {
                    int algoNum = arr[l].list.get(i);
                    if(algo[algoNum]==1) {
                        algoCnt--;
                    }
                    algo[algoNum]--;
                }
                l++;
            }
        }

        System.out.println(rst);
    }

    // (한명이라도 알고있는 알고리즘 수 - 모두 다 알고있는 알고리즘 수)*사람수
    private static long getEfficiency(int maxKnows, int allKnows, int people) {
        return (long)(maxKnows-allKnows)*people;
    }

    private static int algoAllKnows(int people) {
        int cnt = 0;
        for(int i=1; i<=K; i++) {
            if(algo[i]==people) {
                cnt++;
            }
        }
        return cnt;
    }

    static class Info {
        int skill; // 실력
        int count; // 알고리즘 수
        ArrayList<Integer> list; // 알고있는 알고리즘
        public Info(int skill, int count) {
            this.skill = skill;
            this.count = count;
            list = new ArrayList<>();
        }
    }
}
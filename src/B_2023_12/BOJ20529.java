package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 아무리 사람이 많아도 16개, mbti 하나가 3개이상 있는건 의미가 없으므로 (16*3)C3
// 동일한 mbti 개수는 3개까지만 세면 된다.
public class BOJ20529
{
    static int T, N;
    static int[][][][] mbti; // E-I, S-N, T-F, J-P
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            mbti = new int[2][2][2][2];
            list = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            String[] MBTIs = br.readLine().split(" ");

            for(int i=0; i<N; i++) {
                String MBTI = MBTIs[i];
                int a = MBTI.charAt(0)=='E'?1:0;
                int b = MBTI.charAt(1)=='S'?1:0;
                int c = MBTI.charAt(2)=='T'?1:0;
                int d = MBTI.charAt(3)=='J'?1:0;
                if(mbti[a][b][c][d]<3) {
                    list.add(MBTI);
                }
                mbti[a][b][c][d]++;
            }

            System.out.println(getMinDist());
        }
    }

    // (16*3)C3
    private static int getMinDist() {
        if(list.size()==1) return 0;
        if(list.size()==2) return getDiff(list.get(0), list.get(0), list.get(1));

        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<list.size(); i++) {
            for(int j=i+1; j<list.size(); j++) {
                for(int k=j+1; k<list.size(); k++) {
                    String mbti1 = list.get(i);
                    String mbti2 = list.get(j);
                    String mbti3 = list.get(k);
                    minDiff = Math.min(minDiff, getDiff(mbti1, mbti2, mbti3));
                }
            }
        }
        return minDiff;
    }

    // 세 사람간의 심리적 거리 구하기
    private static int getDiff(String mbti1, String mbti2, String mbti3) {
        int rst = 0;
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) rst++;
        }
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti3.charAt(i)) rst++;
        }
        for (int i = 0; i < 4; i++) {
            if (mbti2.charAt(i) != mbti3.charAt(i)) rst++;
        }
        return rst;
    }
}
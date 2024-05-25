package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 서류순 정렬 후, 면접이 더 우수한 사람을 고르기 반복
// TLE1) linkedlist라서 get()시간복잡도 터질듯
public class BOJ1946 {
    static int N, rst;
    static ArrayList<Man> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            for(int i=0; i<N; i++) {
                String[] line_N = br.readLine().split(" ");
                int rank1 = Integer.parseInt(line_N[0]);
                int rank2 = Integer.parseInt(line_N[1]);
                list.add(new Man(rank1, rank2));
            }

            // rank1 정렬
            Collections.sort(list, new Comparator<Man>() {
                @Override
                public int compare(Man m1, Man m2) {
                    return m1.rank1-m2.rank1;
                }
            });

            int i = 1;
            rst = 1;
            int minRank2 = list.get(0).rank2;
            while(i<N) {
                Man cur = list.get(i);
                if(cur.rank2<minRank2) {
                    minRank2 = cur.rank2;
                    rst++;
                }
                i++;
            }
            System.out.println(rst);
        }
    }

    static class Man {
        int rank1;
        int rank2;

        public Man(int rank1, int rank2) {
            this.rank1 = rank1;
            this.rank2 = rank2;
        }
    }
}

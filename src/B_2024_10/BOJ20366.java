package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// search) 눈덩이 2개 조합+정렬(N^2)후 투 포인터로 겹치지 않는 차의 최소값 구하기
// WR1,2) 식 제대로 안씀
// WR3) 값비교해야되는데 인덱스 비교함
// WR4) 값의 합이 아닌 인덱스 합 구함
public class BOJ20366 {
    static int N;
    static int[] arr;
    static ArrayList<Snowman> list;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                list.add(new Snowman(i, j));
            }
        }

        // 눈사람 크기순대로 정렬
        Collections.sort(list, new Comparator<Snowman>() {
            @Override
            public int compare(Snowman o1, Snowman o2) {
                return (arr[o1.idx1]+arr[o1.idx2])-(arr[o2.idx1]+arr[o2.idx2]); // WR1)
            }
        });

        for(int i=0; i<list.size()-1; i++) {
            Snowman s1 = list.get(i);
            Snowman s2 = list.get(i+1);

            if(isNotDuplicate(s1, s2)) {
                rst = Math.min(rst, getVal(s1, s2));
            }
        }

        System.out.println(rst);
    }

    private static int getVal(Snowman s1, Snowman s2) {
        return (arr[s2.idx1]+arr[s2.idx2])-(arr[s1.idx1]+arr[s1.idx2]); // WR2, WR3, WR4)
    }

    private static boolean isNotDuplicate(Snowman s1, Snowman s2) {
        return s1.idx1 != s2.idx1 && s1.idx1 != s2.idx2 && s1.idx2 != s2.idx1 && s1.idx2 != s2.idx2;
    }

    static class Snowman {
        int idx1;
        int idx2;
        public Snowman(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }
}

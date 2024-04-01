package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// WR1) 캐시를 통해 '가장 먼저 만나는'땅 번호 출력
// WR2) 똑같은 땅을 요구할 수 있음
public class BOJ20364 {
    static int N, Q;
    static boolean[] tree;
    static int[] ducks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        Q = Integer.parseInt(line_1[1]);
        tree = new boolean[N+1];
        ducks = new int[Q];
        for(int i=0; i<Q; i++) {
            ducks[i] = Integer.parseInt(br.readLine());
            check(ducks[i]);
        }
    }

    // target부터 위로 올라가면서 확인
    private static void check(int target) {
        int cur = target;
        int cache = 0;

        while(cur!=1) {
            if(tree[cur]) { // 현재 땅이 이미 점유된경우 갈 수 없음
                cache = cur; // 가장 먼저 마주치는 땅을 구해야하므로 캐시에 저장
            }
            cur/=2; // 부모
        }

        tree[target] = true;
        System.out.println(cache);
    }
}
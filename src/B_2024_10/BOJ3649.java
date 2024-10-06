package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// search) 투포인터 방향을 양끝에서 잡아주면 됨
// 투 포인터
// 구멍은 두개의 조각으로 막아야 한다.
// |l1-l2|가 가장 큰것을 출력하려면 l을 먼저 늘려야돼? 아니면 r을 먼저 줄여야돼..?
public class BOJ3649 {
    static int x, n, cnt;
    static int[] len;
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        loop1:
        while((s = br.readLine())!=null) {
            cnt = 0;
            x = Integer.parseInt(s)*10_000_000; // 센티미터->나노미터로 변환
            n = Integer.parseInt(br.readLine());
            len = new int[n];
            for(int i=0; i<n; i++) {
                len[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(len);

            // 레고 2개를 구할 수 없다면
            if(n<2) {
                System.out.println("danger");
                continue;
            }

            // l은 only 증가, r은 only 감소
            int l = 0, r = n-1;
            while(l<r) {
                if(len[l]+len[r]==x) {
                    System.out.println("yes "+len[l]+" "+len[r]);
                    cnt++;
                    continue loop1;
                } else if(len[l]+len[r]>x) {
                    r--;
                } else {
                    l++;
                }
            }

            System.out.println("danger");
        }
    }
}

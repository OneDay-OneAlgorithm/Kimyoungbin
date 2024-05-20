package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// backtracking
// WA1) ascii 순서 출력
public class BOJ7490 {
    static int T, N;
    static char[] rst;
    static char[] way = {' ', '+', '-'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            rst = new char[N-1]; // 수식 결과 저장할 배열

            // +1에서 시작
            dfs(2, 0, 1, 0);
            System.out.println();
        }
    }

    // type -> 이전 부호 저장 (0:+, 1:-)
    private static void dfs(int depth, int val, int tmp, int type) {
        if(depth==N+1) {
            val = type==0?val+tmp:val-tmp;

            if(val==0) {
                for(int i=1; i<N; i++) {
                    System.out.print(i);
                    System.out.print(rst[i-1]);
                }
                System.out.println(N);
            }
            return;
        }

        for(int i=0; i<3; i++) {
            rst[depth-2] = way[i];
            if(i==1) {
                int nextval = type==0?val+tmp:val-tmp;
                dfs(depth+1, nextval, depth, 0);
            } else if(i==2) {
                int nextval = type==0?val+tmp:val-tmp;
                dfs(depth+1, nextval, depth, 1);
            } else if(i==0){
                dfs(depth+1, val, tmp*10+depth, type);
            }
        }
    }
}
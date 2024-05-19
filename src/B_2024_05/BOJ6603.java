package B_2024_05;

import java.io.*;

// kC6
public class BOJ6603 {
    static int k;
    static int[] S, rst;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String[] line_T = br.readLine().split(" ");
            if(line_T.length==1) {
                break;
            }
            k = Integer.parseInt(line_T[0]);
            S = new int[k];
            rst = new int[6];
            for(int i=0; i<k; i++) {
                S[i] = Integer.parseInt(line_T[i+1]);
            }
            // 오름차순이므로 정렬 불필요
            dfs(0, 0);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int start, int depth) throws IOException {
        if(depth==6) {
            for(int i=0; i<6; i++) {
                bw.write(rst[i]+" ");
            }
            bw.write("\n");
            return;
        }

        for(int i=start; i<S.length; i++) {
            rst[depth] = S[i];
            dfs(i+1, depth+1);
        }
    }
}
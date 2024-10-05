package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30802 {
    static int N, T, P;
    static int[] ts = new int[6];
    static int trst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++) {
            ts[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for(int i=0; i<6; i++) {
            if(ts[i]%T==0) trst+=ts[i]/T;
            else trst+=ts[i]/T+1;
        }
        System.out.println(trst);
        System.out.println(N/P+" "+N%P);
    }
}

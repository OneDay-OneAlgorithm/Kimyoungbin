package B_2024_03;

import java.io.*;

public class BOJ1484 {
    static int G;
    static int l=1, r=1; //옛날 몸무게, 현재 몸무게
    static boolean isAvailable = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        G = Integer.parseInt(br.readLine());

        while(true) { // G가 최대 100,000이므로 제곱의 차가 최대 100,000
            int 차이 = 제곱의차(r, l);
            if(r-l==1 && 차이>G) {
                break;
            }

            if(차이==G) {
                bw.write(r+"\n");
                l++;
                r++;
                isAvailable = true;
            } else if(차이>G) {
                l++;
            } else if(차이<G){
                r++;
            }
        }

        if(!isAvailable) {
            bw.write("-1");
        }

        bw.flush();
        bw.close();
    }

    private static int 제곱의차(int a, int b) {
        return a*a-b*b;
    }
}
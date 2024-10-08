package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ2407 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        BigInteger n1 = new BigInteger("1");
        BigInteger n2 = new BigInteger("1");

        for(int i=0; i<m; i++){
            n1 = n1.multiply(new BigInteger(String.valueOf(n-i)));
            n2 = n2.multiply(new BigInteger(String.valueOf(i+1)));
        }

        BigInteger answer = n1.divide(n2);

        System.out.println(answer);
    }
}

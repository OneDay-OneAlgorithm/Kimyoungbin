package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ27866
{
    static String S;
    static int i;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        i = Integer.parseInt(br.readLine());
        System.out.println(S.charAt(i-1));
    }
}

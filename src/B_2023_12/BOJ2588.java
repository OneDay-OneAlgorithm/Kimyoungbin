package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2588
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();

        int l3 = Integer.parseInt(l1)*(l2.charAt(2)-'0');
        int l4 = Integer.parseInt(l1)*(l2.charAt(1)-'0');
        int l5 = Integer.parseInt(l1)*(l2.charAt(0)-'0');
        int l6 = l3+l4*10+l5*100;

        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l5);
        System.out.println(l6);
    }
}

package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ25304
{
    static int X, N;
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int a = Integer.parseInt(line_N[0]);
            int b = Integer.parseInt(line_N[1]);
            sum += (a*b);
        }

        if(sum==X) System.out.println("Yes");
        else System.out.println("No");
    }
}

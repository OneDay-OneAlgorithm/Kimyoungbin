package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1527
{
    static int A, B;
    static int rst=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);

        dfs(0);
        System.out.println(rst);
    }

    private static void dfs(long n) {
        if(n>B) return;
        if(A<=n) rst++;
        dfs(n*10+4);
        dfs(n*10+7);
    }
}
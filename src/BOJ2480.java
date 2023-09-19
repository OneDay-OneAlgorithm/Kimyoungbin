import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2480
{
    static int A, B, C;
    static int rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);
        C = Integer.parseInt(line_1[2]);

        if(A==B && B==C) {
            rst = 10000+(A*1000);
        } else if(A==B || A==C) {
            rst = 1000+(A*100);
        } else if(B==C) {
            rst = 1000+(B*100);
        } else {
            rst = Math.max(A, Math.max(B, C))*100;
        }

        System.out.println(rst);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11382
{
    static long A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);
        C = Integer.parseInt(line_1[2]);
        System.out.println(A+B+C);
    }
}

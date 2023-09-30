import java.io.*;
import java.util.*;

public class BOJ2525
{
    static int A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);
        C = Integer.parseInt(br.readLine());

        int startTime = A*60+B;
        System.out.println(((startTime+C)/60)%24+" "+(startTime+C)%60);
    }
}

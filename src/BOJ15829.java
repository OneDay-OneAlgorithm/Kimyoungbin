import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15829
{
    static int L, a;
    static String str;
    static long r = 1, M = 1234567891, H = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        str = br.readLine();

        for(int i=0; i<str.length(); i++) {
            a = str.charAt(i)-'a'+1;
            H = (H + ((a%M)*(r%M))%M)%M;
            r = (r*31)%M;
        }

        System.out.println(H);
    }
}

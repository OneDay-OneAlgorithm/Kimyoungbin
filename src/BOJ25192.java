import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ25192
{
    static int N;
    static Set<String> set = new HashSet<>();
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            if(input.equals("ENTER")) {
                rst+=set.size();
                set = new HashSet<>();
            } else {
                set.add(input);
            }
        }
        rst+=set.size();
        System.out.println(rst);
    }
}

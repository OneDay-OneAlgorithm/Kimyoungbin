package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ4158
{
    static int N, M;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            int rst = 0;
            String[] line_1 = br.readLine().split(" ");
            N = Integer.parseInt(line_1[0]);
            M = Integer.parseInt(line_1[1]);
            if(N==0 && M==0) break;

            set = new HashSet<>();
            for(int i=0; i<N; i++) {
                set.add(Integer.parseInt(br.readLine()));
            }
            for(int i=0; i<M; i++) {
                if(set.contains(Integer.parseInt(br.readLine()))) {
                    rst++;
                }
            }
            System.out.println(rst);
        }
    }

}
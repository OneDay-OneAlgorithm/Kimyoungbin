package B_2024_05;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ2776 {
    static int T, N, M;
    static int[] arr1, arr2;
    static HashSet<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            set = new HashSet<>();
            N = Integer.parseInt(br.readLine());
            arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            for(int i: arr1) {
                set.add(i);
            }
            M = Integer.parseInt(br.readLine());
            arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            for(int i: arr2) {
                int ans = set.contains(i)?1:0;
                bw.write(ans+"\n");
            }
            bw.flush();
        }
        bw.close();
    }
}
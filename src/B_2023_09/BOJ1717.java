package B_2023_09;

import java.io.*;
import java.util.*;

public class BOJ1717
{
    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        parent = new int[n+1];

        //init
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }

        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            int cal = Integer.parseInt(line_m[0]);
            int a = Integer.parseInt(line_m[1]);
            int b = Integer.parseInt(line_m[2]);

            if(cal==0) {
                union(a, b);
            } else {
                if(isInSameGroup(a, b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static boolean isInSameGroup(int a, int b) {
        return find(a)==find(b);
    }

    private static void union(int a, int b) {
        parent[find(b)] = find(a);
    }

    private static int find(int a) {
        if(a==parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}

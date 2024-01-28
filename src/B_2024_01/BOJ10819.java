package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// greedy 아님 (BOJ1026이랑 느낌이 비슷하긴 한데 얘랑 풀이가 다름)
// N이 최대 8이므로 8!으로 풀어야하는듯?
public class BOJ10819
{
    static int N;
    static int[] A;
    static int[] rst;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rst = new int[N];
        visited = new boolean[N];
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if(depth==N) {
            max = Math.max(getRst(), max);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                rst[depth] = A[i];
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }

    // |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
    private static int getRst() {
        int S = 0;
        for(int i=0; i<N-1; i++) {
            S += Math.abs(rst[i]-rst[i+1]);
        }
        return S;
    }
}
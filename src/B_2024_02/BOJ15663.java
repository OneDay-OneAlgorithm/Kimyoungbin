package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15663
{
    static int N, M;
    static int[] arr;
    static int[] rst;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        rst = new int[M];
        visited = new boolean[N];
        Arrays.sort(arr);

        dfs(0);
    }

    private static void dfs(int depth) {
        if(depth==M) {
            printRst();
            return;
        }

        int before = 0;
        for(int i=0; i<N; i++) {
            if(!visited[i] && before!=arr[i]) { // 같은 레벨에서 동일한 숫자가 오면 안된다. 순열이므로 같은 레벨에서 동일숫자는 연속으로 나타나게 됨을 이용
                visited[i] = true;
                rst[depth] = arr[i];
                before = arr[i];
                dfs(depth+1);
                visited[i] = false;
            }
        }

    }

    private static void printRst() {
        for(int i=0; i<M; i++) {
            System.out.print(rst[i]+" ");
        }
        System.out.println();
    }
}
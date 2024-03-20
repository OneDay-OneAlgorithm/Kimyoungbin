package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1158
{
    static int N, K;
    static Queue<Integer> queue = new LinkedList<>();
    static int[] rst;
    static int idx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        rst = new int[N];

        for(int i=1; i<=N; i++) {
            queue.add(i);
        }

        int cnt=1;
        while(!queue.isEmpty()) {
            if(cnt++%K==0) {
                delete();
            } else {
                pass();
            }
        }

        printRst();
    }

    private static void printRst() {
        System.out.print("<");
        for(int i=0; i<N-1; i++) {
            System.out.print(rst[i]+", ");
        }
        System.out.print(rst[N-1]);
        System.out.println(">");
    }

    private static void pass() {
        queue.add(queue.poll());
    }

    private static void delete() {
        rst[idx++] = queue.poll();
    }
}
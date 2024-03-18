package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

// search) 처음에는 높이가 0이되는것만 고려했는데, 그림자가 낮아지면 안됨
public class BOJ1863
{
    static int n;
    static Stack<Integer> stack = new Stack<>();
    static int[][] arr;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];

        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(line_n[0]);
            arr[i][1] = Integer.parseInt(line_n[1]);
        }

        // x좌표 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i=0; i<n; i++) {
            int next = arr[i][1];
            while(!stack.isEmpty() && stack.peek()>=next) {
                if(stack.peek()>next) {
                    rst++;
                }
                stack.pop();
            }
            stack.add(next);
        }

        // 스택에 남아있는것 제거
        while (!stack.isEmpty()) {
            if(stack.pop()!=0) {
                rst++;
            }
        }

        System.out.println(rst);
    }
}
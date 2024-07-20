package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// search
public class BOJ2374 {
    static int N, past=-1, max = Integer.MIN_VALUE;
    static long cost=0;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int cur = Integer.parseInt(br.readLine());
            max = Math.max(cur, max);
            if(cur==past) {
                continue;
            }
            past = cur;

            if(stack.isEmpty()) {
                stack.add(cur);
            }
            else if(stack.peek()>cur) {
                stack.pop();
                stack.add(cur);
            } else if(stack.peek()<cur){
                int pop = stack.pop();
                cost+=(cur-pop);
                stack.add(cur);
            }
        }

        while(!stack.isEmpty()) {
            int pop = stack.pop();
            cost += (max-pop);
        }

        System.out.println(cost);
    }

}
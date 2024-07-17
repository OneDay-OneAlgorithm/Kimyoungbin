package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BOJ27497 {
    static int N;
    static Deque<String> deque = new ArrayDeque<>();
    static Stack<Integer> stack = new Stack<>(); // 앞:0, 뒤:1
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            String command = line_N[0];
            switch(command) {
                case "1": {
                    String block = line_N[1];
                    deque.addLast(block);
                    stack.add(1);
                    break;
                }
                case "2": {
                    String block = line_N[1];
                    deque.addFirst(block);
                    stack.add(0);
                    break;
                }
                case "3": {
                    if(stack.isEmpty()) break;
                    int remove = stack.pop();
                    if(remove==0) {
                        deque.removeFirst();
                    } else {
                        deque.removeLast();
                    }
                    break;
                }
            }
        }

        while(!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }
        sb = sb.isEmpty()?new StringBuilder("0"):sb;
        System.out.println(sb);
    }
}
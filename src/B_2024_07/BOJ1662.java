package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// stack+idea (오큰수, 같은수로 만들기 등)
// 기존에 스택에 들어있던 수(숫자 1개)와, 스택에 새로 넣은 수(숫자 개수)를 구분하기 위해서 기존 스택의 수는 X로 명명
public class BOJ1662 {
    static String[] input;
    static Stack<String> stack = new Stack<>();
    static int rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split("");
        for(int i=0; i<input.length; i++) {
            if(input[i].equals("(")) {
                stack.pop();
                stack.add(input[i-1]);
                stack.add(input[i]);
            } else if(input[i].equals(")")) {
                int cnt = 0;
                while(true) {
                    String pop = stack.pop();
                    if(pop.equals("(")) {
                        int front = Integer.parseInt(stack.pop());
                        cnt*=front;
                        stack.add(String.valueOf(cnt));
                        break;
                    }
                    if(pop.equals("X"))
                        cnt++;
                    else
                        cnt+=Integer.parseInt(pop);
                }

            } else {
                stack.add("X");
            }
        }

        while(!stack.isEmpty()) {
            String pop = stack.pop();
            if(pop.equals("X")) {
                rst++;
            } else {
                rst+=Integer.parseInt(pop);
            }
        }

        System.out.println(rst);
    }

}
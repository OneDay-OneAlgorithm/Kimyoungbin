package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 고민 포인트 1. 스택을 1개쓸건지 2개쓸건지
// 2. 계산 시점. 여는괄호때 넣을건지 닫는괄호때 넣을건지
// 3. '1이 아닐때 넣는다'라고 조건을 달기에는 예외가 분명 나옴. 이미 들어가있는 값이 1이라면?
// 4. 1을 넣어야돼? 아니면 2 or 3을 넣어야돼?

// sol) 스택은 1개만 사용하고, 숫자를 ()이나 []형태일때 2나 3으로 치환해준다.
// 즉, 괄호를 숫자로 치환해가는 방식으로 계산해나간다.
public class BOJ2504
{
    static Stack<String> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split("");

        // 올바른 입력인지 검증
        try {
            for (int i = 0; i < line_1.length; i++) {
                switch (line_1[i]) {
                    case "(":
                        stack.push("(");
                        break;
                    case "[":
                        stack.push("[");
                        break;
                    case ")":
                        if(!stack.pop().equals("(")) throw new RuntimeException();
                        break;
                    case "]":
                        if(!stack.pop().equals("[")) throw new RuntimeException();
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
            if(!stack.isEmpty()) throw new RuntimeException();
        } catch(Exception e) {
            System.out.println(0);
            return;
        }

        // 값 계산
        // 닫는괄호-여는괄호: 합, 닫는괄호-닫는괄호: 곱
        for(int i=0; i<line_1.length; i++) {
            switch(line_1[i]) {
                case "(":
                    stack.add("(");
                    break;
                case "[":
                    stack.add("[");
                    break;
                case ")":
                    if(stack.peek().equals("(")) {
                        stack.pop();
                        stack.add("2"); //()의 형태일때 2로 치환해준다.
                        break;
                    }
                    int tmp1 = 0;
                    while(!stack.peek().equals("(")) { //괄호 안의 값은 모두 더해준다.
                        tmp1 += Integer.parseInt(stack.pop());
                    }
                    stack.pop();
                    stack.add(String.valueOf(tmp1*2)); //모두 더해준뒤 2를 곱해준다.
                    break;
                case "]":
                    if(stack.peek().equals("[")) {
                        stack.pop();
                        stack.add("3"); //[]의 형태일때 3으로 치환해준다.
                        break;
                    }
                    int tmp2 = 0;
                    while(!stack.peek().equals("[")) { //괄호 안의 값은 모두 더해준다.
                        tmp2 += Integer.parseInt(stack.pop());
                    }
                    stack.pop();
                    stack.add(String.valueOf(tmp2*3)); //모두 더해준뒤 3을 곱해준다.
                    break;
            }
        }
        int rst = 0;
        while (!stack.isEmpty()) {
//            System.out.println(stack.peek());
            rst += Integer.parseInt(stack.pop());
        }
        System.out.println(rst);
    }
}
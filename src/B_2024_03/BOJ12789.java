package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// N에서 가는방법, N을 안누르고 A에서 B로 증/감만 통해서 가는방법
public class BOJ12789
{
    static int N;
    static Queue<Integer> main = new LinkedList<>();
    static Stack<Integer> sub = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            main.add(Integer.parseInt(line_2[i]));
        }

        int cur = 1;
        while(cur<N) {
            int mf = !main.isEmpty()?main.peek():-1;
            int sf = !sub.isEmpty()?sub.peek():-1;

            if(mf!=-1) { // 메인줄에 다음사람이 있다면
                if(mf==cur) { // 메인줄에 다음차례 사람이 있다면
                    main.poll();
                    cur++;
                } else if(sf!=cur) { // 메인줄에 없다면 서브줄 확인후 서브줄로 보내기
                    sub.add(main.poll());
                } else {
                    sub.pop();
                    cur++;
                }
            } else { // 메인줄은 비었을 때
                if(sf==cur) { // 서브줄에 다음 사람이 있다면
                    sub.pop();
                    cur++;
                } else { // 서브줄에 다음사람이 없다면
                    System.out.println("Sad");
                    return;
                }
            }
        }
        System.out.println("Nice");
    }
}
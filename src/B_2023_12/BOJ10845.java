package B_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ10845
{
    static int N;
    static Deque<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            String command = line_N[0];
            switch(command) {
                case "push":
                    queue.addLast(Integer.parseInt(line_N[1]));
                    break;
                case "front":
                    if(queue.isEmpty()) {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(queue.peekFirst());
                    break;
                case "back":
                    if(queue.isEmpty()) {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(queue.peekLast());
                    break;
                case "pop":
                    if(queue.isEmpty()) {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(queue.pop());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    System.out.println(queue.isEmpty()?1:0);
                    break;
            }
        }
    }
}

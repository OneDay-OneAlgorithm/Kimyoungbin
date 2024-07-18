package B_2024_07;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

// MLE1) 메모리 제한이 빡센듯 -> LinkedList대신 ArrayDeque 변경
public class BOJ2346 {
    static int N;
    static Deque<Info> deque = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            deque.addLast(new Info(i+1, Integer.parseInt(line_2[i])));
        }

        while(!deque.isEmpty()) {
            Info next = deque.peekFirst();
            bw.write(next.no+" ");
            if(next.paper<0) {
                deque.pollFirst();
                if(!deque.isEmpty())
                    deque.addFirst(deque.removeLast());
                next.paper = -next.paper;
                next.paper--;
                while(!deque.isEmpty() && next.paper>0) {
                    deque.addFirst(deque.removeLast());
                    next.paper--;
                }
            } else {
                deque.pollFirst();
                next.paper--;
                while(!deque.isEmpty() && next.paper>0) {
                    deque.addLast(deque.removeFirst());
                    next.paper--;
                }
            }
        }
        bw.flush();
        bw.close();
    }

    static class Info {
        int no;
        int paper;
        public Info(int no, int paper) {
            this.no = no;
            this.paper = paper;
        }
    }
}
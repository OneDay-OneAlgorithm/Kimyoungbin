package LG_2024_10;

import java.io.*;
import java.util.*;

public class 업다운_챌린지 {
    static int N, K;
    static int[] arr;
    static Deque<Integer> deque = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        Arrays.sort(arr);
        for(int i=0; i<N; i++) {
            deque.addLast(arr[i]);
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(c==1) {
                while(!deque.isEmpty() && deque.peekFirst()<=x) {
                    deque.pollFirst();
                }
            } else  {
                while(!deque.isEmpty() && deque.peekLast()>=x) {
                    deque.pollLast();
                }
            }
        }

        System.out.println(deque.size());
    }
}
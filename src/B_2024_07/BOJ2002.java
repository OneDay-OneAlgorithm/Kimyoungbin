package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// 출제자가 운전을 많이 안해본듯 함
public class BOJ2002 {
    static int N, inc = 0, cur = 0;
    static int[] arr;
    static boolean[] check;
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        check = new boolean[N];
        for(int i=0; i<N; i++) {
            map.put(br.readLine(), i);
        }
        for(int i=0; i<N; i++) {
            int no = map.get(br.readLine());
            arr[i] = no;
        }
        for(int i=0; i<N; i++) {
            if(arr[i]<=cur) {
                check[cur] = true;
                while(cur<N && check[cur]) {
                    cur++;
                }
            } else {
                check[arr[i]] = true;
                inc++;
            }
        }
        System.out.println(inc);
    }
}
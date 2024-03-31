package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ27172 {
    static int N;
    static int[] arr;
    static int[] rst;
    static int[] card = new int[1000001];
    static int[] who; // 해당 카드값을 가지고 있는 사람의 번호 (arr이랑 반대)
    static int SIZE = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        rst = new int[N];
        who = new int[SIZE+1];
        for(int i=0; i<N; i++) {
            card[arr[i]]++;
            who[arr[i]] = i;
        }
        for(int i=0; i<N; i++) {
            for(int j=arr[i]*2; j<=SIZE; j+=arr[i]) { // 배수 확인
                if(card[j]!=0) {
                    rst[i]++;
                    rst[who[j]]--;
                }
            }
        }

        for (int i : rst) {
            System.out.print(i+" ");
        }
    }
}
package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 양/음 절대값의 최대를 마지막으로 (단방향)
public class BOJ1461
{
    static int N, M;
    static int[] books;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 책의 개수
        M = Integer.parseInt(line_1[1]); // 한번에 들 수 있는 책 수
        books = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray(); // size: 0~N-1
        Arrays.sort(books);

        // 최대거리 구하기
        int l, r;
        int maxDist = Math.abs(books[0])>Math.abs(books[N-1])?books[0]:books[N-1];
        if(maxDist<0) {
            l = 0+M;
            r = N-1;
        } else {
            l = 0;
            r = N-1-M;
        }

        // 음수부분 합 구하기
        for(int i=l; i<N && books[i]<0; i+=M) {
            rst+=(-books[i])*2;
        }
        // 양수부분 합 구하기
        for(int i=r; i>=0 && books[i]>0; i-=M) {
            rst+=books[i]*2;
        }

        System.out.println(rst+Math.abs(maxDist));
    }
}
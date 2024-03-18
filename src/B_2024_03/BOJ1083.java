package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 그리디로 풀 수 없는 문제같은데? 왜 이게 그리디야
// search) 남은 S동안 교환가능한 가장 큰 수를 찾아서 교환한다.
public class BOJ1083
{
    static int N, S, count;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        S = Integer.parseInt(br.readLine());
        count = S; // 교환 가능한 시도수

        int start = 0; // 가장 큰 수를 앞에서부터 쌓아놓은 개수
        while(count>0 && start<N) {
            int maxIdx = findMax(start, start+count); // start~(start+count) 인덱스 사이에 앞으로 가져올 수 있는 가장 큰 수의 인덱스 찾기
            sort(start, maxIdx); // 가장 큰 수를 start위치까지 당겨오기
            count -= (maxIdx-start); // 당겨온 횟수만큼 소모값 제거
            start++;
        }

        printrst();
    }

    // start ~ end idx중에 가장 큰 수의 인덱스 반환
    private static int findMax(int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxIdx = Integer.MIN_VALUE;
        for(int i=start; i<=end; i++) {
            if(i>=N) {
                break;
            }
            if(max<A[i]) {
                maxIdx = i;
                max = A[i];
            }
        }
        return maxIdx;
    }

    // end 위치에 있는 수를 start까지 당겨오기
    private static void sort(int start, int end) {
        for(int i=end-1; i>=start; i--) {
            swap(i, i+1);
        }
    }

    private static void printrst() {
        for (int i : A) {
            System.out.print(i+" ");
        }
    }

    private static void swap(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
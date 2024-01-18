package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// DP or Greedy -> Greedy로 가능한가?
public class BOJ11062
{
    static int N;
    static int[] cards;
    static boolean flag; // false: 근우, true: 명우
    static int scoreG, scoreM;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            cards = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
            scoreG = 0;
            scoreM = 0;
            game();
        }
    }

    private static void game() {
        int left = 0, right = cards.length-1;
        while(left<right) {
        }
    }
}
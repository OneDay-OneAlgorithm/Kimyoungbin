package B_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 9655 돌게임
// 구글링) N=1부터 규칙성을 찾기, N=?일때 다음턴인 사람을 P, 다음턴이 아닌 사람을 NP라고 하면
// N=1 -> P win, 1(P) = win
// N=2 -> P는 1개밖에 가져갈 수 없으므로 NP win, 2(P) = lose
// N=3 -> P win, 3(P) = win
// N=4 -> P가 1개를 가져가면 N=3일때랑 같아져서 NP win, P가 3개를 가져가면 N=1일때랑 같아져서 NP win, 4(P) = lose
// N=5 -> P가 1개를 가져가면 N=4일때랑 같아져서 5(P) = 4(NP) = win, P가 3개를 가져가면 N=2일떄랑 같아져서 5(P) = 2(NP) = win
// N=6 -> P가 1개를 가져가면 N=5일때랑 같아져서 6(P) = 5(NP) = lose, P가 3개를 가져가면 N=3일때랑 같아져서 6(P) = 3(NP) = lose.. 반복
public class BOJ9655
{
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String rst = N%2!=0?"SK":"CY";
        System.out.println(rst);
    }
}
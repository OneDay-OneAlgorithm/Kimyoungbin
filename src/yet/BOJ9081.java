package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 순열X -> Ad hoc
// 문제 1. 동일한 단어가 여러개 존재하는 경우 (ex.HELLO) -> 이전과 비교해야되나?
// 문제 2. 단어 길이가 최대 99이므로 99! -> 시간초과
public class BOJ9081
{
    static int T;
    static boolean flag;
    static String str;
    static char[] word;
    static boolean[] visited;
    static char[] rst;
    static String pastWord;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            str = br.readLine();
            word = str.toCharArray();
            Arrays.sort(word);
            visited = new boolean[word.length];
            rst = new char[word.length];
            pastWord = null;

            flag = false;
            permutation(0);
        }
    }

    // 순열
    private static void permutation(int depth) {
        if(depth==word.length) {
            String rstWord = String.valueOf(rst);
//            if(rstWord.equals(pastWord)) { // 동일한 단어가 연속으로 나오는 경우 체크
//                // do anything
//                System.out.println("AAAAAAAAAAAAAAAAAAA");
//            } else if(rstWord.equals(str)) { // 찾는 단어를 찾으면 flag 설정
//                flag = true;
//            } else if(flag) { // 다음단어 출력, flag 해제
//                System.out.println(rstWord);
//                flag = false;
//            }
//            pastWord = rstWord;
//            return;
            System.out.println(rstWord);
        }

        for(int i=0; i<word.length; i++) {
            if(!visited[i]) {
                rst[depth] = word[i];
                visited[i] = true;
                permutation(depth+1);
                visited[i] = false;
            }
        }
    }
}
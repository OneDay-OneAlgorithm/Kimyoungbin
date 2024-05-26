package B_2024_05;

import java.io.*;
import java.util.Arrays;

// MLE1) search - 문자 사용여부를 판단하는 used를 알파벳 개수로 판단
// -> 근데 단어 길이가 20보다 작거나 같아서 내 풀이에서는 의미 없음
// -> 모든 출력문자열을 set이 담고있으니깐 set의 문제일수도 있겠다. 나머지는 가능성 없음
// search) 접근방식 차이 (나 = 주어진 문자열 정렬 후 문자열 기준으로 백트래킹, 정답 = 알파벳 개수만 count후 다음에 들어갈 수 있는 알파벳 출력)
public class BOJ6443 {
    static int N;
    static char[] str;
    static char[] rst;
    static int[] alphacnt;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int t=0; t<N; t++) {
            str = br.readLine().toCharArray();
            Arrays.sort(str);
            rst = new char[str.length];
            alphacnt = new int[26];

            // 알파벳 개수 count
            for(int i=0; i<str.length; i++) {
                alphacnt[str[i]-'a']++;
            }

            dfs(0);
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) throws IOException{
        if(depth==str.length) {
            for(int i=0; i<rst.length; i++) {
                bw.write(rst[i]);
            }
            bw.write("\n");
            return;
        }

        for(int i=0; i<26; i++) {
            if(alphacnt[i]>0) {
                rst[depth] = (char)('a'+i);
                alphacnt[i]--;
                dfs(depth+1);
                alphacnt[i]++;
            }
        }
    }
}

package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 옵션 한개씩 확인
// 1. 단어의 첫글자 확인
// 2. 왼쪽에서부터 한글자씩 확인
// 3. 하나도 불가능하면 적용 X
public class BOJ1283
{
    static int N;
    static int[][] alp = new int[26][2]; // 0: 옵션 번호, 1: 저장 알파벳 인덱스
    static String[] options;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        options = new String[N];
        for(int i=0; i<26; i++) {
            alp[i][0] = -1;
        }

        outer:
        for(int i=0; i<N; i++) {
            options[i] = br.readLine();
            String ol = options[i].toLowerCase();
            String[] words = ol.split(" ");
            int wordIdx = 0;

            // 단어 첫글자 확인
            for(String s: words) {
                char ch = s.charAt(0);
                if(alp[ch-'a'][0]==-1) { // 지정안된 글자가 있다면
                    alp[ch-'a'][0] = i;
                    alp[ch-'a'][1] = wordIdx;
                    continue outer;
                }
                wordIdx+=s.length()+1; // 띄어쓰기 포함
            }

            // 차례대로 단축키 미지정 글자 여부 확인
            for(int j=0; j<ol.length(); j++) {
                char ch = ol.charAt(j);
                if(ch!=' ' && alp[ch-'a'][0]==-1) {
                    alp[ch-'a'][0] = i;
                    alp[ch-'a'][1] = j;
                    continue outer;
                }
            }
        }

        // print
        for(int i=0; i<N; i++) {
            boolean 단축키 = false;
            for(int j=0; j<26; j++) {
                if(alp[j][0]==i) {
                    int tar = alp[j][1];
                    System.out.println(options[i].substring(0, tar) + "[" + options[i].charAt(tar) + "]" + options[i].substring(tar+1));
                    단축키 = true;
                }
            }
            if(!단축키) System.out.println(options[i]);
        }
    }
}
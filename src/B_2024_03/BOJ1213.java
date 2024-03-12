package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팰린드롬: 홀수인 알파벳이 0종류 or 1종류만 존재할 수 있고, 홀수인 알파벳은 가운데에 들어갈 1개를 빼놓고 나머지 배열해주기
public class BOJ1213
{
    static int[] alphabet;
    static char[] name;
    static char center = '.';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        alphabet = new int[26];
        name = br.readLine().toCharArray();
        for(int i=0; i<name.length; i++) {
            alphabet[name[i]-'A']++;
        }
        for(int i=0; i<alphabet.length; i++) {
            char ch = (char)('A'+i);
            if(alphabet[i]%2!=0) { // 홀수개일 경우
                if(center!='.') { // 홀수인 알파벳이 여러개
                    System.out.println("I'm Sorry Hansoo");
                    return;
                } else {
                    center = ch;
                }
            }
            for(int j=0; j<alphabet[i]/2; j++) {
//                    System.out.println("ch = "+ch+", alphabet[name[j]/2] = "+alphabet[j]/2);
                sb.append(ch);
            }
        }
        if(center!='.') {
            sb.append(center);
        }
        for(int i=alphabet.length-1; i>=0; i--) {
            char ch = (char)('A'+i);
            for(int j=0; j<alphabet[i]/2; j++) {
                sb.append(ch);
            }
        }

        System.out.println(sb);
    }
}


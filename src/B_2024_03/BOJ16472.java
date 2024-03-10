package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//고민거리
// l~r범위를 l<=x<=r? l<=x<r? 후자는 문제조건에 문자열길이가 1인경우 문제발생, 전자는 nullable 문제 발생해서 if문 분기처리 필요
// 분기조건 설정 -> 1. 알파벳이 더 들어갈 수 있는지를 기준으로 분기, 2. 다음 알파벳이 이미 존재하는건지
public class BOJ16472
{
    static int N;
    static char[] str;
    static int l, r, sort; // l<=x<=r
    static int maxLen = Integer.MIN_VALUE; // 최대 문자열 길이
    static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine().toCharArray();
        l = 0; r = 0; sort = 0;

        while(r<str.length) {
            // 새 알파벳이 더 들어갈 수 있음 -> 동일한 알파벳 나오는동안 r을 이동 -> r을 이동하기위해서는 끝문자가 아니어야함
            if(sort<N) {
                char next = str[r];
                int nextcnt = 0; // 다음 나오는 연속된 알파벳 개수
                int getnext = map.containsKey(next)?map.get(next):0; // 이미 존재하는 알파벳이 또 나오는건지 여부
                if(getnext==0) { // 새로운 알파벳이 나오는 경우
                    sort++;
                }

                while(r<str.length && next==str[r]) {
                    r++;
                    nextcnt++;
                }
                map.put(next, getnext+nextcnt);
            }
            // 새 알파벳이 더 들어갈 수 없음
            // 다음 알파벳이 새로운경우 동일한 알파벳 나오는동안 l을 이동, 원래 존재하는 알파벳일경우 동일한 알파벳 나오는동안 r을 이동
            else {
                // 다음 알파벳이 기존 존재할 경우, r을 증가
                char next = str[r];
                int nextcnt = 0;
                int getnext = map.containsKey(next)?map.get(next):0;
                if(getnext!=0) {
                    while(r<str.length && next==str[r]) {
                        r++;
                        nextcnt++;
                    }
                    map.put(next, getnext+nextcnt);
                }
                // 다음 알파벳이 새로운것일 경우, l을 이동하여 제거
                else {
                    char past = str[l];
                    int pastcnt = 0;
                    while (l<r && past==str[l]) {
                        l++;
                        pastcnt++;
                    }
                    map.put(past, map.get(past)-pastcnt);
                    if (map.get(past) <= 0) { // 하나도 존재하지 않는경우 map에서 제거
                        map.remove(past);
                        sort--;
                    }
                }
            }

            // 최대 문자열 길이 계산
            maxLen = Math.max(maxLen, r-l);
        }

        System.out.println(maxLen);
    }
}


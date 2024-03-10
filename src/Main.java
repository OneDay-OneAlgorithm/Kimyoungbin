import java.io.*;
import java.util.*;

public class Main
{
    static int N;
    static int l = 0, r = 0, sort = 0;
    static char[] arr;
    static int[] alphabet = new int[26];
    static int maxLen = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        alphabet[arr[r]-'a']++;
        sort++;
        while(r<arr.length-1) {
            r++;
            if(alphabet[arr[r]-'a']++ == 0) sort++;
            // 알파벳 종류조건을 만족할때까지 l을 제거
            while(sort>N && l<r) {
                if(--alphabet[arr[l]-'a'] == 0) sort--;
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
        }
        System.out.println(maxLen);
    }
}


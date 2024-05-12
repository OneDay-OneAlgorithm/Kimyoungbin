package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//WA) acbccba => 오른쪽 -> 왼쪽으로 불일치를 확인하는 순서가 존재하기 때문에 문제가 발생할 수 있다. 왼쪽을 삭제할지 오른쪽을 삭제할지를 결정해야 함
// -> 두 칸 앞까지 학인하는 방법이 있는데, l과 r이 1차이일 경우 index 문제 발생 가능
public class BOJ17609 {
    static char[] str;
    static int chance;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            str = br.readLine().toCharArray();
            int l = 0, r = str.length-1;
            chance = 0;
            while(l<r) {
                if(str[l]==str[r]) { // 좌우 일치
                    l+=1;
                    r-=1;
                } else if(str[l]==str[r-1] && str[l+1]==str[r-2]) { // 오른쪽 불일치 제거
                    l+=1;
                    r-=2;
                    chance++;
                } else if(str[l+1]==str[r] && str[l+2]==str[r-1]) { // 왼쪽 불일치 제거
                    l+=2;
                    r-=1;
                    chance++;
                } else { // 양측 불일치
                    l+=1;
                    r-=1;
                    chance=INF;
                }
            }
            chance = Math.min(chance, 2);
            System.out.println(chance);
        }
    }
}
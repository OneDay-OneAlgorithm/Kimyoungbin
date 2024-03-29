package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// search) 길게 한줄로 만들면 리프 2개, 그 이상은 끝에 떼서 중간에 붙여주는 방법으로 증가시킬 수 있음
public class BOJ14244 {
    static int n, m;
    static int save;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);

        for(int i=0; i<=n-m; i++) {
            System.out.println(i+" "+(i+1));
        }
        save = n-(m-2);
        for(int i=0; i<m-2; i++) {
            System.out.println(1+" "+save++);
        }
    }
}
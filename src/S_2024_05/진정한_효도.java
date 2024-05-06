package S_2024_05;

import java.io.*;
import java.util.*;

public class 진정한_효도 {
    static int rst = Integer.MAX_VALUE;
    static int[][] arr = new int[3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //init
        for(int i=0; i<3; i++) {
            String[] line_i = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(line_i[j]);
            }
        }

        // 가로
        for(int i=0; i<3; i++) {
            // 높이 세팅
            for(int j=1; j<=3; j++) {
                int cost = 0;
                for(int k=0; k<3; k++) {
                    cost+=Math.abs(j-arr[i][k]);
                }
                rst = Math.min(rst, cost);
            }
        }
        // 세로
        for(int i=0; i<3; i++) {
            // 높이 세팅
            for(int j=1; j<=3; j++) {
                int cost = 0;
                for(int k=0; k<3; k++) {
                    cost+=Math.abs(j-arr[k][i]);
                }
                rst = Math.min(rst, cost);
            }
        }

        System.out.println(rst);
    }
}

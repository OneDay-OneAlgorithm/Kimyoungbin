package SOFTEER_2024_05;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

// 파라미터 탐색
public class 성적_평가 {
    static int N;
    static Integer[][] score; // 1,2,3,total
    static HashMap<Integer, Integer>[] map = new HashMap[4]; // key번째 사람의 점수는 val, 1,2,3,total
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        score = new Integer[4][N];

        for(int i=0; i<4; i++) {
            map[i] = new HashMap<>();
        }

        for(int i=0; i<3; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                int sj = Integer.parseInt(line_N[j]);
                score[i][j] = sj;
                map[i].put(j, sj);
            }
        }

        for(int i=0; i<N; i++) {
            score[3][i] = score[0][i]+score[1][i]+score[2][i];
            map[3].put(i, score[3][i]);
        }

        Arrays.sort(score[0], Collections.reverseOrder());
        Arrays.sort(score[1], Collections.reverseOrder());
        Arrays.sort(score[2], Collections.reverseOrder());
        Arrays.sort(score[3], Collections.reverseOrder());

        for(int j=0; j<4; j++) {
            for(int i=0; i<N; i++) {
                int findscore = map[j].get(i);
                int rank = binarySearch(score[j], findscore);
                bw.write((rank+1)+" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    // arr: 내림차순
    // parametric search) 제일 왼쪽에 있는 수 찾기
    private static int binarySearch(Integer[] arr, int target) {
        int l=0, r=arr.length-1;
        int rst = Integer.MAX_VALUE;
        while(l<=r) {
            int mid = (l+r)/2;
            if(arr[mid]<=target) {
                r = mid-1;
                rst = Math.min(rst, mid);
            } else if(arr[mid]>target) {
                l = mid+1;
            }
        }
        return rst;
    }
}

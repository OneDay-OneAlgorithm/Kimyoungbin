package B_2024_04;

import java.io.*;
import java.util.ArrayList;

// search) 위상 정렬 연습
public class BOJ2252 {
    static int N, M;
    static int[] D; // 진입 차수 배열
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        D = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            list[start].add(end);
            D[end]++;
        }

        // 진입차수 = 0인 항목들 빼기
        boolean flag = true;
        while(flag) {
            flag = false;
            for(int i=1; i<=N; i++) {
                if(D[i]==0) {
                    D[i]=-1;
                    bw.write(i+" ");
                    for(int j=0; j<list[i].size(); j++) {
                        D[list[i].get(j)]--;
                    }
                    flag = true;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
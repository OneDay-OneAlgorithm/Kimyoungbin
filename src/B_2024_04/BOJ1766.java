package B_2024_04;

import java.io.*;
import java.util.ArrayList;

// 위상 정렬
public class BOJ1766
{
    static int N, M;
    static ArrayList<Integer>[] list;
    static int[] D; // 진입차수 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 문제 개수
        M = Integer.parseInt(line_1[1]); // 정보
        list = new ArrayList[N+1];
        D = new int[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_2 = br.readLine().split(" ");
            int first = Integer.parseInt(line_2[0]);
            int second = Integer.parseInt(line_2[1]);
            list[first].add(second);
            D[second]++;
        }

        boolean flag = true;
        while(flag) {
            flag = false;
            for(int i=1; i<=N; i++) {
                if(D[i]==0) {
                    bw.write(i+" ");
                    for(int j=0; j<list[i].size(); j++) {
                        D[list[i].get(j)]--;
                    }
                    D[i]--;
                    flag = true;
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
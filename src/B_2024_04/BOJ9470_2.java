package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 근원이 되는 노드가 여러개이므로 bfs는 불가능해보임
// 들어오는 간선의 숫자와, 개수까지 파악해야 strahler순서를 파악할 수 있음
public class BOJ9470_2 {
    static int T, K, M, P;
    static ArrayList<Integer>[] list;
    static int[] info;
    static int[] strahler;
    static ArrayList<Integer>[] reverse;
    static int rst;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            String[] line_1 = br.readLine().split(" ");
            K = Integer.parseInt(line_1[0]); // 테스트케이스 번호
            M = Integer.parseInt(line_1[1]); // 노드의 수
            P = Integer.parseInt(line_1[2]); // 간선의 수
            list = new ArrayList[M+1];
            info = new int[M+1]; // 진입차수 배열
            strahler = new int[M+1]; // strahler 순서 정보
            reverse = new ArrayList[M+1]; // 들어오는 간선 정보
            for(int i=1; i<=M; i++) {
                list[i] = new ArrayList<>();
                reverse[i] = new ArrayList<>();
            }

            for(int i=0; i<P; i++) {
                String[] line_P = br.readLine().split(" ");
                int start = Integer.parseInt(line_P[0]);
                int end = Integer.parseInt(line_P[1]);
                list[start].add(end);
                reverse[end].add(start);
                info[end]++;
            }

            for(int i=1; i<=M; i++) {
                if(info[i]==0) {
                    queue.add(i);
                }
            }

            while(!queue.isEmpty()) {
                int i = queue.poll();
                int maxStrahler = 1; // 최대 strahler '값'
                int maxStrahlerCount = 0; // i로 들어오는 간선의 시작점 중 최대 strhaler값을 가지는 점의 개수
                for(int j=0; j<reverse[i].size(); j++) {
                    int from = reverse[i].get(j);

                    if(strahler[from]>maxStrahler) {
                        maxStrahler = strahler[from];
                        maxStrahlerCount = 1;
                    } else if(strahler[from]==maxStrahler) {
                        maxStrahlerCount++;
                    }
                }
                if(maxStrahlerCount==0) {
                    strahler[i] = 1;
                } else if(maxStrahlerCount==1) {
                    strahler[i] = maxStrahler;
                } else {
                    strahler[i] = maxStrahler+1;
                }

                // 진입차수 배열 세팅
                for(int j=0; j<list[i].size(); j++) {
                    info[list[i].get(j)]--;
                    if(info[list[i].get(j)]==0) {
                        queue.add(list[i].get(j));
                    }
                }
                info[i]--;

            }

            rst = 0;
            for(int i=1; i<=M; i++) {
                rst = Math.max(rst, strahler[i]);
            }
            System.out.println(K+" "+rst);
        }
    }
}
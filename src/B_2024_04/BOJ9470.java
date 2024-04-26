package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 근원이 되는 노드가 여러개이므로 bfs는 불가능해보임
// 들어오는 간선의 숫자와, 개수까지 파악해야 strahler순서를 파악할 수 있음
public class BOJ9470 {
    static int T, K, M, P;
    static ArrayList<Integer>[] list;
    static int[] info;
    static int[] strahler;
    static ArrayList<Integer>[] reverse;
    static int rst;
    static boolean[] root; // 근원노드인지 아닌지
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
            root = new boolean[M+1];
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

            for(int i=0; i<=M; i++) {
                if(info[i]==0) {
                    root[i] = true;
                }
            }

            // 근원노드 초기화 (root를 따로 두지 않으면 진행중에 info[i]==0이 되어 문제가 발생하는 경우가 있다.
            for(int i=1; i<=M; i++) {
                if(root[i]) {
                    strahler[i] = 1;
                    // 진입차수 배열 세팅
                    for(int j=0; j<list[i].size(); j++) {
                        info[list[i].get(j)]--;
                    }
                    info[i]--;
                }
            }


            boolean flag = true;
            while(flag) {
                flag = false;
                for(int i=1; i<=M; i++) {
                    if(info[i]==0) {
                        flag = true;
                        // strahler 순서 설정
                        // maxStrahler값을 0으로 설정하면, 근원노드에서 shrahler[i]값이 0이 되어버린다.
                        // maxStrahler값을 1로 설정하면, 근원노드로부터 들어올 때 maxStrahlerCount값이 1 크게나온다.
                        // 따라서 어쩔 수 없이 초기 세팅을 분리하고 maxStrahler값을 0으로 설정하였다.
                        // 그랬더니 1->2를 거쳐서 info[3]이 0이 되고, 그 상태에서 strahler[3]까지 0이 되는 문제가 발생
                        // 초기 설정은 info를 체크하는게 아니라 근원노드만을 체크해야 함 -> 새로운 배열을 만들어줌
                        int maxStrahler = 0; // 최대 strahler '값'
                        int maxStrahlerCount = 1; // i로 들어오는 간선의 시작점 중 최대 strhaler값을 가지는 점의 개수
                        for(int j=0; j<reverse[i].size(); j++) {
                            int past = reverse[i].get(j);

                            if(strahler[past]>maxStrahler) {
                                maxStrahler = strahler[past];
                                maxStrahlerCount = 1;
                            } else if(strahler[past]==maxStrahler) {
                                maxStrahlerCount++;
                            }
                        }
                        strahler[i] = maxStrahlerCount==1?maxStrahler:maxStrahler+1;

                        // 진입차수 배열 세팅
                        for(int j=0; j<list[i].size(); j++) {
                            info[list[i].get(j)]--;
                        }
                        info[i]--;
                    }
                }
            }


            rst = 0;
            for(int i=1; i<=M; i++) {
                rst = Math.max(rst, strahler[i]);
            }
            System.out.println(K+" "+rst);

        }

    }
}
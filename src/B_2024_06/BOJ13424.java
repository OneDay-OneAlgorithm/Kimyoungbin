package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

// N개의 방에 대해서 친구들이 있는 곳까지 거리를 계산: floyd(N^3+N)
// WA1) 오버플로
// WA2) floyd 식 틀림
public class BOJ13424 {
    static int N, M, K;
    static LinkedList<Integer> friends;
    static int[][] dist;
    static PriorityQueue<Info> pq;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            String[] line_1 = br.readLine().split(" ");
            N = Integer.parseInt(line_1[0]); // 방의 개수
            M = Integer.parseInt(line_1[1]); // 길의 개수
            friends = new LinkedList<>(); // 친구들이 있는 방의 정보
            pq = new PriorityQueue<>(new Comparator<>() { // 이동 거리 총합 최소 -> 방번호 최소 순 출력
                @Override
                public int compare(Info i1, Info i2) {
                    if(i1.dist==i2.dist) return i1.room-i2.room;
                    else return i1.dist-i2.dist;
                }
            });

            // 초기화
            dist = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    dist[i][j] = INF;
                    if(i==j) {
                        dist[i][j] = 0;
                    }
                }
            }
            // 길 정보 받기
            for(int i=0; i<M; i++) {
                String[] line_M = br.readLine().split(" ");
                int s = Integer.parseInt(line_M[0])-1;
                int e = Integer.parseInt(line_M[1])-1;
                int w = Integer.parseInt(line_M[2]);
                dist[s][e] = w;
                dist[e][s] = w;
            }
            // 친구 정보 저장
            K = Integer.parseInt(br.readLine());
            String[] line_last = br.readLine().split(" ");
            for(int i=0; i<K; i++) {
                friends.add(Integer.parseInt(line_last[i])-1);
            }

            // floyd
            for(int k=0; k<N; k++) {
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                    }
                }
            }

            for(int i=0; i<N; i++) { // 모든 방에 대해서
                int sum = 0;
                for(int j=0; j<friends.size(); j++) { // 친구들이 있는 방까지 거리 구하기
                    sum += dist[i][friends.get(j)];
                }
                pq.add(new Info(i, sum));
            }

            System.out.println(pq.poll().room+1);
        }
    }

    static class Info {
        int room;
        int dist;
        public Info(int room, int dist) {
            this.room = room;
            this.dist = dist;
        }
    }

}
package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// floyd + 경로 저장
// n이 한자리수인지 모르므로, String으로 하면 안된다.
// WA1) 중복 경로일경우 짧은경로 저장
// WA2) 갈 수 없는 경로인경우 way 출력시 0 출력
// WA3) 갈 수 없는 경로인경우 dist 출력시 0 출력
public class BOJ11780 {
    static int n, m;
    static int[][] dist;
    static ArrayList<Integer>[][] way;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = INF;
                }
            }
        }
        way = new ArrayList[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                way[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(dist[s][e]>w) {
                dist[s][e] = w;
                way[s][e] = new ArrayList<>();
                way[s][e].add(s);
                way[s][e].add(e);
            }
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(dist[i][k]+dist[k][j]<dist[i][j]) {
                        dist[i][j] = dist[i][k]+dist[k][j];

                        // 경로 갱신
                        way[i][j] = new ArrayList<>();
                        for(int l=0; l<way[i][k].size()-1; l++) {
                            int cur = way[i][k].get(l);
                            way[i][j].add(cur);
                        }
                        for(int l=0; l<way[k][j].size(); l++) {
                            int cur = way[k][j].get(l);
                            way[i][j].add(cur);
                        }
                    }
                }
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(dist[i][j]==INF) { // WA3
                    sb.append(0).append(" ");
                } else {
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(way[i][j].isEmpty()) { // WA2 제거
                    sb.append("0").append("\n");
                } else {
                    sb.append(way[i][j].size()).append(" ");
                    for(int k=0; k<way[i][j].size(); k++) {
                        int cur = way[i][j].get(k);
                        sb.append(cur).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}

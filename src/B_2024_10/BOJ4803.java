package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// union-find
// 트리 개수를 셀때 cycle 체크 필요
// WA1) 출력 오류
// WA2) union을 하면서 cycle을 체크하고 있는데, 1 2가 입력되기 전까지는 트리 루트가 2일 것으로 예상 (union 이후 부모 노드가 바뀔 수 있다) - search
// 4 4
//2 3
//2 4
//3 4
//1 2
//0 0
public class BOJ4803 {
    static int n, m;
    static int[] parent;
    static boolean[] cycle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int caseNo = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parent = new int[n+1];
            cycle = new boolean[n+1];

            for(int i=1; i<=n; i++) {
                parent[i] = i;
            }

            if(n==0 && m==0) {
                break;
            }

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                // union 이전 cycle 판별하여 표시
//                if(find(parent[s])==find(parent[e])) {
//                    cycle[find(parent[s])] = true; // 루트에 사이클 정보 표시
//                }
                union(s, e);
            }

            int cnt = 0;
            boolean[] visited = new boolean[n+1];
            for(int i=1; i<=n; i++) {
                // 사이클이 아니고, 새로운 루트일 경우
                if(!cycle[find(parent[i])] && !visited[find(parent[i])]) {
                    visited[find(parent[i])] = true;
                    cnt++;
                }
            }

            if(cnt==0) {
                System.out.println("Case "+caseNo+": No trees.");
            } else if(cnt==1) {
                System.out.println("Case "+caseNo+": There is one tree.");
            } else {
                System.out.println("Case "+caseNo+": A forest of "+cnt+" trees.");
            }
            caseNo++;
        }
    }

    // 작은번호가 트리 상단으로 가도록
//    private static void union(int a, int b) {
//        if(a<b) {
//            parent[find(b)] = find(a);
//        } else {
//            parent[find(a)] = find(b);
//        }
//    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        // 둘이 이미 같은 트리에 속해 있으면 사이클 발생
        if (a == b) {
            cycle[a] = true; // 사이클 정보를 루트에 저장
            return;
        }

        // 작은 번호가 트리 상단으로 가도록 함
        if (a < b) {
            parent[b] = a;
            // 합칠 때 둘 중 하나가 사이클이 있었으면 합친 결과도 사이클이 되어야 함
            if (cycle[b]) cycle[a] = true;
        } else {
            parent[a] = b;
            if (cycle[a]) cycle[b] = true;
        }
    }

    private static int find(int a) {
        if(a==parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}

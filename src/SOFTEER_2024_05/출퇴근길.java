package SOFTEER_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// DFS, Idea - 역방향 리스트
// 역방향 탐색시 목표에 도달했다고 탐색을 종료하면 안됨

// https://softeer.ai/practice/6248
// 출퇴근길에 모두 포함될 수 있는 정점의 개수
// 출근길과 퇴근길 각각 BFS를 통해서 방문가능한 점을 모두 구하고 겹치는 점 개수 구하기 (단 s,e는 제외)

// WA1) 그렇게하면 목적지에 도달하지 않아도 visited=true로 체크될 수 있음 -> dfs로 boolean리턴 하도록?
// -> dfs로는 "시작점에서 도착점까지 도달할 수 있는 경우 중에서 지나칠 수 있는 모든 점"을 구하지 못한다.
// -> 왜냐면 무한루프를 방지하기위해서는 visited체크를 해야되는데, 이게 한번 갔다가 돌아올 수 있는 점이 있거든 (예제 2에서 6->3->1->7->1->5)
// -> 이 케이스에서 1을 방문처리해버리면 7갔다가 다시 돌아오는게 안돼

// search) 이게 문제가 start에서 방문할 수 있는 모든 정점을 구하고, 그 모든 정점들에서 end까지 갈 수 있는지를 체크해야하는데,
// n이 최대 10만이라 그럼 n^2이돼서 TLE
// 그래서 idea -> 역방향 연결리스트를 만들어서 start->정점에대해 일반 연결리스트로 방문체크하고, 역방향 연결리스트로 end->정점을 체크해주면 이게 모든 정점에서 end까지 갈 수 있는지를 체크한것과 동일

// WA2)
// search) (출근길 경로에 S는 여러 번 등장해도 되고, 퇴근길 경로에 T는 여러 번 등장해도 된다.) 라는 조건이 역방향 연결리스트 dfs에서 왜 영향을 끼치는거지?
// -> end에서 역방향으로 연결되어있지 않아도, start에서 갈 수 있는 점이면 무조건 방문 가능한 점임.
// -> 그러면 출발점으로 들어올 수 있는 점들을 또 구해줘서 답에 포함시켜야돼?
// -> 역방향 dfs에서 출발점을 여러번 방문할 수 있다고 했으므로 [출발점을 처음부터 visited처리하면 안되고, 출발점에 갔다고 탐색을 종료시키면 안됨]
public class 출퇴근길 {
    static int n, m, s, e, rst = 0;
    static ArrayList<Integer>[] list, rlist;
    static boolean[] visited1, visited2, visited3, visited4; // 출근, 퇴근
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        list = new ArrayList[n+1];
        rlist = new ArrayList[n+1];
        visited1 = new boolean[n+1];
        visited2 = new boolean[n+1];
        visited3 = new boolean[n+1];
        visited4 = new boolean[n+1];

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            rlist[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            int start = Integer.parseInt(line_m[0]);
            int end = Integer.parseInt(line_m[1]);
            list[start].add(end);
            rlist[end].add(start);
        }
        String[] line_se = br.readLine().split(" ");
        s = Integer.parseInt(line_se[0]);
        e = Integer.parseInt(line_se[1]);

        // 출근길 DFS1) 집 -> 모든 정점 방문 가능 여부 체크
        visited1[s] = true;
        visited1[e] = true;
        dfs1(s);

        // 출근길 DFS2) 회사 -> 모든 정점 역방문 가능 여부 체크
//        visited2[s] = true;
        visited2[e] = true;
        dfs2(e);

        // 퇴근길 DFS3) 회사 -> 모든 정점 방문 가능 여부 체크
        visited3[s] = true;
        visited3[e] = true;
        dfs3(e);

        // 퇴근길 DFS4) 집 -> 모든 정점 역방문 가능 여부 체크
        visited4[s] = true;
//        visited4[e] = true;
        dfs4(s);


        for(int i=1; i<=n; i++) {
            if((visited1[i] && visited2[i]) && (visited3[i] && visited4[i])) {
                rst++;
            }
        }
        System.out.println(rst-2); // s,e 제외
    }

    private static void dfs1(int cur) {
        if(cur==e) {
            return;
        }

        for(int i=0; i<list[cur].size(); i++) {
            int next = list[cur].get(i);
            if(!visited1[next]) {
                visited1[next] = true;
                dfs1(next);
            }
        }
    }

    private static void dfs2(int cur) {

        for(int i=0; i<rlist[cur].size(); i++) {
            int next = rlist[cur].get(i);
            if(!visited2[next]) {
                visited2[next] = true;
                dfs2(next);
            }
        }
    }

    private static void dfs3(int cur) {
        if(cur==s) {
            return;
        }

        for(int i=0; i<list[cur].size(); i++) {
            int next = list[cur].get(i);
            if(!visited3[next]) {
                visited3[next] = true;
                dfs3(next);
            }
        }
    }

    private static void dfs4(int cur) {

        for(int i=0; i<rlist[cur].size(); i++) {
            int next = rlist[cur].get(i);
            if(!visited4[next]) {
                visited4[next] = true;
                dfs4(next);
            }
        }
    }

}

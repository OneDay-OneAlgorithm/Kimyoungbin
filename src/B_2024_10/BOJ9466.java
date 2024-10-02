package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 사이클이 존재해야 한 팀이 될 수 있음
// 사이클이 완전해야 방문처리를 완료 -> dfs
// TLE1) tmp를 배열 -> set으로 변환
// TLE2) dfs가 두번 도니까, 그거 한번으로 처리
public class BOJ9466 {
    static int n, cnt;
    static int[] arr;
    static boolean[] visited; // 검사가 끝난 곳
    static HashSet<Integer> set; // 임시 방문처리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            n = Integer.parseInt(br.readLine());
            cnt = 0;
            arr = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            visited = new boolean[n+1];

            for(int i=1; i<=n; i++) {
                if(arr[i]==i) {
                    visited[i] = true;
                    cnt++;
                }
            }
            for(int i=1; i<=n; i++) {
                if(!visited[arr[i]]) {
                    set = new HashSet<>();
                    dfs(i);
                }
            }

            System.out.println(n-cnt);
        }
    }

    // 임시 방문 처리하면서 dfs
    // 시작점으로부터 사이클이 존재할 경우 true 반환
    private static void dfs(int cur) {
        if(visited[cur]) { // 이미 방문된경우 탐색 종료
            return;
        }
        if(set.contains(cur)) { // 사이클에 포함된 경우 방문처리
            visited[cur] = true;
            cnt++;
        }

        set.add(cur);
        dfs(arr[cur]);
        visited[cur] = true;
        set.remove(cur);
    }
}

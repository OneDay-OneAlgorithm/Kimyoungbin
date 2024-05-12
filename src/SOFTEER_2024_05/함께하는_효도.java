package SOFTEER_2024_05;

import java.io.*;

// m명의 친구, n*n크기 땅, 3초 제한
// 열매 수확량의 최대값
// 3초 제한이므로 한사람당 4^3=64가지 경우. 친구도 최대 3명이므로 64^3 - brute force
public class 함께하는_효도 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited; // 열매 수확 여부
    static Node[] friends;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        arr = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        friends = new Node[m]; // 0~m-1번 친구

        for(int i=1; i<=n; i++) {
            String[] line_n = br.readLine().split(" ");
            for(int j=1; j<=n; j++) {
                arr[i][j] = Integer.parseInt(line_n[j-1]);
            }
        }

        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            int y = Integer.parseInt(line_m[0]);
            int x = Integer.parseInt(line_m[1]);
            friends[i] = new Node(x, y);
            visited[y][x] = true;
        }


        // friends[0]부터 한명씩 탐색
        dfs(friends[0].x, friends[0].y, 0, 0, 0);

        // friends정보 더해주기
        for(int i=0; i<m; i++) {
            rst += arr[friends[i].y][friends[i].x];
        }
        System.out.println(rst);
    }

    // depth: 3칸, person: m명
    private static void dfs(int x, int y, int score, int depth, int person) {
        // m명의 친구들 모두 탐색 완료
        if(person==m-1 && depth==3) {
            rst = Math.max(rst, score);
            return;
        }

        // 다음 사람 탐색
        if(depth==3) {
            dfs(friends[person+1].x, friends[person+1].y, score, 0, person+1);
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(1<=nx && nx<=n && 1<=ny && ny<=n) {
                boolean flag = visited[ny][nx]; // 원래 방문했는지 표시
                int nscore = !flag?score+arr[ny][nx]:score; // 첫 방문시에만 수확
                visited[ny][nx] = true;
                dfs(nx, ny, nscore, depth+1, person);
                if(!flag)
                    visited[ny][nx] = false; // 원래 방문한 지점이 아닌경우 visited 초기화
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

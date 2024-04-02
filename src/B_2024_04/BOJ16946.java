package B_2024_04;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 영역의 크기를 구해놓고, 벽이 부서지면 합쳐지는 영역의 크기
// TL1) BufferedWriter 도입
// 추가) BufferedWriter + StringBuilder
public class BOJ16946 {
    static int N, M;
    static int areaNo = 2;
    static int[][] map; // 0: 탐색하지 않음, 1: 벽, 2~: 영역명
    static int[] areaSize; // areaSize[i] = areaNo가 i인 영역의 크기
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<Node> queue;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        map = new int[N][M];
        areaSize = new int[1000000];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        // 영역의 크기 구해놓기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==0) {
                    bfs(i, j);
                }
            }
        }

//        for(int i=0; i<N; i++) {
//            for(int j=0; j<M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        for(int i=2; i<10; i++) {
//            System.out.println("areaSize[i] = " + areaSize[i]);
//        }

        // 정답 출력
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==1) {
                    sb.append(getRst(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 벽을 부수고 그 위치에서 이동할 수 있는 칸의 개수 구하기
    private static int getRst(int y, int x) {
        int ans = 0;
        int[] checkDuplicate = new int[4]; // 사방에 같은거있나 체크
        Arrays.fill(checkDuplicate, -1);
        outer:
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<M && 0<=ny && ny<N && map[ny][nx]!=1) {
                for(int j=0; j<i; j++) {
                    if(checkDuplicate[j]==map[ny][nx]) {
                        continue outer;
                    }
                }
                checkDuplicate[i] = map[ny][nx];
                ans += areaSize[map[ny][nx]];
            }
        }
        return (ans+1)%10; // 자기 자신 포함
    }

    // 한 영역 탐색
    private static void bfs(int y, int x) {
        int size;
        queue = new LinkedList<>();
        queue.add(new Node(y, x));
        map[y][x] = areaNo;
        size = 1;

        while(!queue.isEmpty()) {
            Node next = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = next.x+dx[i];
                int ny = next.y+dy[i];
                if(0<=nx && nx<M && 0<=ny && ny<N && map[ny][nx]==0) {
                    map[ny][nx] = areaNo;
                    queue.add(new Node(ny, nx));
                    size++;
                }
            }
        }

        areaSize[areaNo] = size; // 현재 영역의 사이즈 저장
        areaNo++;
    }

    static class Node {
        int x;
        int y;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
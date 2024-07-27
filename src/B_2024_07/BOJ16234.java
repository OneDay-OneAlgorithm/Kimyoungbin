package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// WA1) 종료조건이 하나로 합쳐질수도 있고, 여러개일수도 있다. 그냥 이전 상태랑 비교해야됨
// WA2) 빼먹음
// WA3) x y 거꾸로 적음
// WA4) 13%, 연합의 수로 종료조건을 비교할 경우, 연합의 수가 동일하다고 배열이 바뀌지 않았다는 것을 나타내지는 않는다.
public class BOJ16234 {
    static int N, L, R;
    static int[] unitePopulation; // 해당 연합의 인구수
    static int[][] A, unite; // 연합 번호
    static int[][] pastA;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int day = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        L = Integer.parseInt(line_1[1]);
        R = Integer.parseInt(line_1[2]);

        A = new int[N][N];
        pastA = new int[N][N];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(line_N[j]);
                pastA[i][j] = A[i][j];
            }
        }

        int uniteCnt;
//        int pastUniteCnt=N*N;
        while(true) {
            uniteCnt = 0;
            visited = new boolean[N][N];
            unitePopulation = new int[5001];
            unite = new int[N][N];

            // bfs를 통해서 해당 연합번호가 가질 인구수 구하기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) {
                        uniteCnt++;
//                        printV();
//                        System.out.println("i = " + i);
//                        System.out.println("j = " + j);
                        unitePopulation[uniteCnt] = bfs(j, i, uniteCnt);
                    }
                }
            }

            // 연합번호 확인 후, 인구수 재배분
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    A[i][j] = unitePopulation[unite[i][j]];
                }
            }

//            System.out.println("uniteCnt = " + uniteCnt);
//            System.out.println("pastUniteCnt = " + pastUniteCnt);
//            printA();
//            if(uniteCnt==pastUniteCnt || uniteCnt==N*N) { // 엽합의 수가 이전과 변화가 없거나, N개가 모두 분리된 경우 WA4)
//                break;
//            }

            boolean isSame = true;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(A[i][j]!=pastA[i][j]) {
                        isSame = false;
                    }
                }
            }
            if(isSame) {
                break;
            }

//            pastUniteCnt = uniteCnt;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    pastA[i][j] = A[i][j];
                }
            }
            day++;
        }

        System.out.println(day);
    }

    // bfs를 진행하면서 unite 배열에 uniteNum 지정
    // 해당 unite에 속하는 칸들의 인구수를 리턴
    private static int bfs(int x, int y, int uniteNum) {
        int population = 0;
        int node = 0;

        // bfs를 통해서 연합의 인구수와 칸수 구하기
        queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[y][x] = true; // WA2), WA3)

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            unite[cur.y][cur.x] = uniteNum;
            population += A[cur.y][cur.x];
            node++;

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
//                System.out.println("ny = " + ny);
//                System.out.println("nx = " + nx);
                // 방문한 적 없고 인구차이를 만족한다면 연합에 포함
                if(0<=nx && nx<N && 0<=ny && ny<N) {
//                    System.out.println("N = " + N);
//                    System.out.println("!visited[ny][nx] = " + !visited[ny][nx]);
//                    System.out.println("1(L<=Math.abs(A[cur.y][cur.x]-A[ny][nx])) = " + (L<=Math.abs(A[cur.y][cur.x]-A[ny][nx])));
//                    System.out.println("2(L<=Math.abs(A[cur.y][cur.x]-A[ny][nx])) = " + (Math.abs(A[cur.y][cur.x]-A[ny][nx])<=R));
                    if(!visited[ny][nx] && L<=Math.abs(A[cur.y][cur.x]-A[ny][nx]) && Math.abs(A[cur.y][cur.x]-A[ny][nx])<=R) {

                        visited[ny][nx] = true;
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }

        return population/node;
    }

    private static void printA() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printV() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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
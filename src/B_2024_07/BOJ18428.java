package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// N이 작아서 그냥 장애물을 브루트포스로 세우고 볼 수 있는지 여부를 검사
// TLE) 선생으로부터 상하좌우 탐색으로 하면 canTeachersSeeStudents가 시간 초과 -> 선생 좌표를 큐에 저장하여 탐색속도 향상
// -> 큐에 저장하면 계속 초기화해야 하므로 Node[]배열에 저장 -> 이것도 실행 오래걸림
// 대체 선생이 학생 볼수있는지 확인하는 함수를 어떻게 만들어야함?
public class BOJ18428 {
    static int N;
    static int[][] map; // 0: 빈공간, 1: 학생, 2:선생, 3: 벽
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean rst = false;
    static LinkedList<Node> teachers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // input
        for (int i = 0; i < N; i++) {
            String[] line_N = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = line_N[j].equals("X") ? 0 : line_N[j].equals("S") ? 1 : 2;
                if (map[i][j] == 2) teachers.add(new Node(j, i));
            }
        }

        // dfs로 N*N개중 기둥 설치할 3개 고르기
        dfs(0, 0);
        System.out.println(rst ? "YES" : "NO");
    }

    private static void dfs(int depth, int loc) {
        if (depth == 3 && !canTeachersSeeStudents()) {
            rst = true;
            return;
        } else if(depth==3) {
            return;
        }

        // Tip) 2차원 배열 조합을 1차원으로 처리할 수 있다.
        for (int i = loc; i < N * N; i++) {
            int x = i % N;
            int y = i / N;
            if (map[y][x] == 0) {
                map[y][x] = 3;
                dfs(depth + 1, i + 1);
                map[y][x] = 0;
            }
        }
    }

    // 선생님이 학생을 볼 수 있는지
    private static boolean canTeachersSeeStudents() {
        for (Node cur : teachers) {
            int j = cur.x;
            int i = cur.y;
            // 선생으로부터 상하좌우 확인
            for (int k = 0; k < 4; k++) {
                int nx = j + dx[k], ny = i + dy[k];
                while (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (map[ny][nx] == 1) return true;
                    if (map[ny][nx] == 3) break;
                    nx += dx[k];
                    ny += dy[k];
                }
            }
        }
        return false;
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
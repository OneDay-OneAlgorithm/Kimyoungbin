package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 완전탐색 불가능해보임 -> DP or 역탐색
// search) BFS :: 근데 가능하다는 확신을 어떻게 내리지 -> visited두고 bfs하면 최대 500*500*500=1250만정도니까 가능
// MLE1, search) visited를 돌 2개의 개수만 알면 나머지 1개의 개수는 알 수 있다 (정해져 있다). 따라서 2차원으로만 만들면 됨
public class BOJ12886 {
    static int A, B, C;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);
        C = Integer.parseInt(line_1[2]);
        visited = new boolean[1501][1501]; // MLE 가능성? -> MLE1)

        // 같은개수로 만들려면 일단 3등분할 되어야함
        if((A+B+C)%3!=0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs()?1:0);
    }

    private static boolean bfs() {
        Queue<Group> queue = new LinkedList<>();
        queue.add(new Group(A, B, C));
        visited[A][B] = true;

        while(!queue.isEmpty()) {
            Group cur = queue.poll();

            if(cur.a==cur.b && cur.b==cur.c) {
                return true;
            }

            // A->B
            if(cur.a-cur.b>=0 && !visited[cur.a-cur.b][cur.b*2] && cur.a!=cur.b) {
                queue.add(new Group(cur.a-cur.b, cur.b*2, cur.c));
                visited[cur.a-cur.b][cur.b*2] = true;
            }
            // B->A
            if(cur.b-cur.a>=0 && !visited[cur.a*2][cur.b-cur.a] && cur.a!=cur.b) {
                queue.add(new Group(cur.a*2, cur.b-cur.a, cur.c));
                visited[cur.a*2][cur.b-cur.a] = true;
            }
            // A->C
            if(cur.a-cur.c>=0 && !visited[cur.a-cur.c][cur.b] && cur.a!=cur.c) {
                queue.add(new Group(cur.a-cur.c, cur.b, cur.c*2));
                visited[cur.a-cur.c][cur.b] = true;
            }
            // C->A
            if(cur.c-cur.a>=0 && !visited[cur.a*2][cur.b] && cur.a!=cur.c) {
                queue.add(new Group(cur.a*2, cur.b, cur.c-cur.a));
                visited[cur.a*2][cur.b] = true;
            }
            // B->C
            if(cur.b-cur.c>=0 && !visited[cur.a][cur.b-cur.c] && cur.b!=cur.c) {
                queue.add(new Group(cur.a, cur.b-cur.c, cur.c*2));
                visited[cur.a][cur.b-cur.c] = true;
            }
            // C->B
            if(cur.c-cur.b>=0 && !visited[cur.a][cur.b*2] && cur.a!=cur.b) {
                queue.add(new Group(cur.a, cur.b*2, cur.c-cur.b));
                visited[cur.a][cur.b*2] = true;
            }
        }

        return false;
    }

    static class Group {
        int a;
        int b;
        int c;

        public Group(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}

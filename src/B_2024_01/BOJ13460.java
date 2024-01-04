package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ13460
{
    static int N, M;
    static int sbx, sby, srx, sry;
    // 오른쪽, 아래, 왼쪽, 위
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] board;
    static Queue<Node> queue = new LinkedList<>();
    static int rst = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        board = new char[N][M];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                board[i][j] = line_N[j].charAt(0);
                if(board[i][j]=='R') { // [오류] 순서 바뀜
                    srx = j;
                    sry = i;
                    board[i][j] = '.'; // [오류] 위치만 잡아주기
                } else if(board[i][j]=='B') { // [오류] 순서 바뀜
                    sbx = j;
                    sby = i;
                    board[i][j] = '.'; // [오류] 위치만 잡아주기
                }
            }
        }

//        System.out.println(srx+" "+sry);
        bfs(srx, sry, sbx, sby);

        System.out.println(rst);
    }

    private static void bfs(int srx, int sry, int sbx, int sby) {
        queue.add(new Node(srx, sry, sbx, sby, 0));

        while(!queue.isEmpty()) {
            Node poll = queue.poll();
//            System.out.println("poll.x:"+poll.rx+", poll.y:"+poll.ry);
            if(poll.depth==10) { // [오류] depth==11로 잘못생각
                break;
            }
            // 오른쪽, 아래, 왼쪽, 위
            for(int i=0; i<4; i++) {
//                System.out.println("i = " + i);
//                if(isInBound(poll.rx+dx[i], poll.ry+dy[i], poll.bx+dx[i], poll.by+dy[i])) { // [참고] 없어도될듯?
//                    System.out.println(poll.rx+dx[i]+" "+poll.ry+dy[i]);
                Node next = moveGlass(poll, i);
                if (next != null) {
                    queue.add(next);
                }
//                }
            }
        }
    }

    // 해당 방향(i)으로 구슬 움직이기
    // 일단 두 구슬을 별개로 움직이고, 처음 위치를 통해서 위치를 조정하는 방식으로 해결
    private static Node moveGlass(Node node, int i) {
        int newrx = node.rx, newry = node.ry, newbx = node.bx, newby = node.by;
        boolean redOnHole = false, blueOnHole = false;
        // 빨간구슬 이동
        do {
            newrx += dx[i];
            newry += dy[i];
            if(board[newry][newrx]=='O') {
                redOnHole = true;
            }
        } while(board[newry][newrx]!='#');
        // [오류] 벽에 박힌거 빼주기
        newrx -= dx[i];
        newry -= dy[i];

        // 파란구슬 이동
        do {
            newbx += dx[i];
            newby += dy[i];
            if(board[newby][newbx]=='O') {
                blueOnHole = true;
            }
        } while(board[newby][newbx]!='#');
        // [오류] 벽에 박힌거 뺴주기
        newbx -= dx[i];
        newby -= dy[i];

        if(blueOnHole) { // 실패
            return null;
        }
        if(redOnHole) { // 성공
            rst = rst==-1?node.depth+1:rst; // [오류] bfs이므로 초기 1회만 갱신
//            System.out.println("newrx = " + newrx);
//            System.out.println("newry = " + newry);
//            System.out.println(node.depth+1);
            return null;
        }

        // 처음 구슬 위치를 바탕으로 위치 재조정
        if(newrx==newbx && newry==newby) {
            if(i==0) { // 오른쪽
                if(node.rx<node.bx) newrx--;
                else newbx--;
            } else if(i==1) { // 아래
                if(node.ry<node.by) newry--;
                else newby--;
            } else if(i==2) { // 왼쪽
                if(node.rx<node.bx) newbx++;
                else newrx++;
            } else if(i==3) { // 위
                if(node.ry<node.by) newby++; // [오류] .rx라고 씀
                else newry++;
            }
        }

        return new Node(newrx, newry, newbx, newby, node.depth+1);
    }

    private static boolean isInBound(int rx, int ry, int bx, int by) {
        if(0<=rx && rx<M && 0<=ry && ry<N && 0<=bx && bx<M && 0<=by && by<N) return true;
        else return false;
    }

    static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int depth;
        public Node(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }

}
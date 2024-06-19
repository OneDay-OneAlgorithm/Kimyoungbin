package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// search-code) 방법은 이해했으나 접근방법에 따라서 풀이 난이도가 달라진다. (내 풀이: https://www.acmicpc.net/source/79581925)
// 1. 나는 전체 순환을 1개의 rotate() 메서드로 묶었기 때문에, R이 커져서 고리마다 회전횟수가 달라지는 경우 풀이가 복잡해짐
// 2. 게다가 해당 과정에서 별도 배열을 사용했기 때문에, 고리별로 배열을 복사하는것이 매우 복잡해짐.
public class BOJ16927 {

    static int N, M, R;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 회전시켜야하는 덩어리 수 만큼 반복 (바깥쪽 -> 안쪽)
        int nN = N;
        int nM = M;
        for(int i=0; i<Math.min(M, N)/2; i++) {
            /*
             * (i, i) : 회전을 시작할 좌표
             * (nN+nM)*2-4 : 처음엔 가장 겉 테두리의 갯수, 그 다음엔 각 변 길이가 2씩 감소시킴
             */
            rotate(i, (nN+nM)*2-4);
            nN -= 2;
            nM -= 2;
        }

        print();
    }
    static void rotate(int start, int len) {

        // 나누기 연산을 사용하여 반복되는 반복을 최소화 해준다.
        int cir = R % len;

        // 새롭게 구해낸 회전 횟수 만큼 회전시킴
        for(int t=0; t<cir; t++) {

            int temp = map[start][start]; // 마지막에 넣을 값 미리 빼놓고, 역방향으로 진행시켜 별도의 배열을 둘 필요 없게 함

            int idx = 0; // 다음 값 탐색할 인덱스

            int x = start;
            int y = start;

            while(idx < 4) {

                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if(nx >= start && ny >= start && nx < N-start && ny < M-start) {
                    map[x][y] = map[nx][ny]; // 역방향 덮어씌우기
                    x = nx;
                    y = ny;
                } else {
                    idx++;
                }
            }
            map[start+1][start] = temp;	// 미리 빼놓았던 값 넣어주기
        }
    }

    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

}
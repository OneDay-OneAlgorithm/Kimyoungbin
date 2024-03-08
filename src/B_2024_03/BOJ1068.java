package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ1068
{
    static int N, deleteN;
    static LinkedList<Integer>[] list; // 자식노드 정보
    static boolean[] visited;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new LinkedList[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++) {
            list[i] = new LinkedList<>();
        }
        String[] line_2 = br.readLine().split(" ");
        deleteN = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int parentNode = Integer.parseInt(line_2[i]);
            // 지워질 시작 노드가 아니고, 루트 노드가 아닌경우 (아예 지워질노드는 연결을 처음부터 배제)
            if(i!=deleteN && parentNode!=-1) {
                list[parentNode].add(i);
            }
        }

        delete(deleteN);

        // 자식노드가 없는 노드 개수 구하기
        for(int i=0; i<N; i++) {
            if(list[i]!=null && list[i].isEmpty()) {
                rst++;
            }
        }
        System.out.println(rst);

    }

    // node포함 하위 노드 제거
    private static void delete(int node) {
        // 재귀
        for(int i=0; i<list[node].size(); i++) {
            delete(list[node].get(i));
        }
        // 제거
        list[node] = null;
    }
}


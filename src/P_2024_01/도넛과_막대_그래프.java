package P_2024_01;

import java.util.*;

// 1. 추가한 정점은 들어오는 간선의 수가 0, 나가는 간선은 2이상(조건)이다.
// 2. 시작점에서 그래프를 따라갔을 때 자기자신으로 돌아오기 전에 나가는 간선이 없는 점을 만나면 일자그래프이다.
// 3. 진행중 나가는 간선이 2개인 점이 없으면 도넛, 있으면 팔자그래프이다.
// -> 자기자신을 만나거나, 간선이 2개인점을 만나면 종료
class 도넛과_막대_그래프 {
    ArrayList<Integer>[] arr;
    int[] rst = new int[4]; // 추가점, 도넛, 막대, 8자
    int[] 들어오는간선개수 = new int[1_000_001];
    public int[] solution(int[][] edges) {
        arr = new ArrayList[1_000_001];
        for(int i=0; i<1000001; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++) {
            arr[edges[i][0]].add(edges[i][1]);
            들어오는간선개수[edges[i][1]]++;
        }

        // 추가한 정점 찾기
        for(int i=0; i<edges.length; i++) {
            if(arr[i].size()>=2 && 들어오는간선개수[i]==0) rst[0] = i;
        }

        for(int i=0; i<arr[rst[0]].size(); i++) {
            checkGraphType(i);
        }

        return rst;
    }

    // 정점 i를 포함한 그래프의 타입 결정
    private void checkGraphType(int i) {
        int 시작점 = arr[rst[0]].get(i);
        int 현재점 = 시작점;
        boolean flag;
        while(true) {
            if(arr[현재점].size()==0) { // 막대 그래프
                rst[2]++;
                return;
            }
            if(arr[현재점].size()>=2) { // 8자 그래프
                rst[3]++;
                return;
            }
            현재점 = arr[현재점].get(0);
            if(현재점==시작점) { // 도넛 그래프
                rst[1]++;
                return;
            }
        }
    }
}
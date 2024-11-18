package B_2024_11;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// 입구, 출구, 코너에 카페 위치
// 동일 x좌표에서는 증가/감소중 하나만 가능
public class BOJ11067 {
    static int T, n, m;
    static ArrayList<Info> list;
    static ArrayList<Info> rst;
    static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            n = Integer.parseInt(br.readLine()); // 카페 개수
            list = new ArrayList<>();
            rst = new ArrayList<>();
            size = new int[100_001];

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Info(x, y));
                size[x]++;
            }
            // 좌표 리스트 정렬
            Collections.sort(list, new Comparator<Info>() {
                @Override
                public int compare(Info o1, Info o2) {
                    if(o1.x==o2.x) {
                        return o1.y-o2.y;
                    } else return o1.x-o2.x;
                }
            });

            // 좌표 하나씩 뽑아가면서 번호붙이기
            int py = 0;
            for(int i=0; i<n; ) {
                Info i1 = list.get(i); // 다음 x좌표중 최소 y
                int s = i;
                int e = i+size[i1.x]-1;
                Info i2 = list.get(e); // 다음 x좌표중 최대 y
                if(i1.y<py || i2.y<py) { // y좌표가 더 작은 경우 내림차순으로 탐색
                    for(int j=e; j>=s; j--) {
                        rst.add(list.get(j));
                        py = list.get(j).y;
                    }
                } else { // y좌표가 큰 경우 오름차순 탐색
                    for(int j=s; j<=e; j++) {
                        rst.add(list.get(j));
                        py = list.get(j).y;
                    }
                }
                i+=(e-s+1);
            }


            // 결과 출력
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            for(int i=0; i<m; i++) {
                Info cur = rst.get(Integer.parseInt(st.nextToken())-1);
                bw.write(cur.x+" "+cur.y+"\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static class Info {
        int x;
        int y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
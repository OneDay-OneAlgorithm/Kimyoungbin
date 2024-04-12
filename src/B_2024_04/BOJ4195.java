package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// union-find
// 1.WA) F<=10만이므로 사람수 최대 20만
// 2.WA) union연산시 무조건 작은 것의 친구네트워크에 합연산하도록 수정
public class BOJ4195
{
    static int F;
    static int[] parent; // 대표 노드
    static int[] count; // 해당 네트워크 안에 몇명이 있는지
    static HashMap<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            parent = new int[200001];
            count = new int[200001];
            F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for(int i=1; i<=200000; i++) {
                parent[i] = i;
                count[i] = 1;
            }
            int seq = 1; // 사람 수
            for(int i=0; i<F; i++) {
                String[] line_F = br.readLine().split(" ");
                String f1 = line_F[0];
                String f2 = line_F[1];
                if(!map.containsKey(f1)) {
                    map.put(f1, seq++);
                }
                if(!map.containsKey(f2)) {
                    map.put(f2, seq++);
                }

                union(map.get(f1), map.get(f2));
                System.out.println(count[find(map.get(f1))]);
            }
        }
    }

    private static void union(int a, int b) {
        if(a<b) {
            if(find(a)!=find(b)) {
                count[find(a)]+=count[find(b)];
            }
            parent[find(b)] = find(a);
        } else {
            if(find(b)!=find(a)) {
                count[find(b)]+=count[find(a)];
            }
            parent[find(a)] = find(b);
        }
    }

    private static int find(int a) {
        if(parent[a]==a) return a;
        else return parent[a] = find(parent[a]);
    }
}
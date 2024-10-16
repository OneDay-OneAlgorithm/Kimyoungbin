package B_2024_10;

import java.io.*;
import java.util.StringTokenizer;

// segtree
public class BOJ2042 {
    static int N, M, K;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];

        for(int i=1; i<=N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegTree segTree = new SegTree(N);
        segTree.init(arr, 1, 1, N);

        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            // 원소의 범위는 2^63
            long b = Long.parseLong(st.nextToken());

            if(cmd==1){
                segTree.update(1, 1, N, a, b - arr[a]);
                arr[a] = b;
            } else {
                bw.write(segTree.sum(1, 1, N, a, (int)b)+"\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static class SegTree {
        long[] tree;
        int size;

        public SegTree(int arrSize) {
            // 트리 높이 구하기
            int h = (int)Math.ceil(Math.log(arrSize)/Math.log(2));
            // 높이를 이용한 배열 사이즈 구하기
            this.size = (int) Math.pow(2, h+1);
            tree = new long[size];
        }

        public long init(long[] arr, int node, int start, int end) {
            // 배열과 시작과 끝이 같다면 leaf노드이므로 값 저장
            if(start==end) {
                return tree[node] = arr[start];
            }

            // 재귀 - leaf노드가 아니라면 자식노드(왼쪽+오른쪽)의 합 담기
            return tree[node] = init(arr, node*2, start, (start+end)/2) + init(arr, node*2+1, (start+end)/2+1, end);
        }

        public void update(int node, int start, int end, int idx, long diff) {
            if(idx<start || end<idx) return;

            tree[node] += diff;

            if(start!=end) {
                update(node*2, start, (start+end)/2, idx, diff);
                update(node*2+1, (start+end)/2+1, end, idx, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            if(left>end || right<start) {
                return 0;
            }

            if(left<=start && end<=right) {
                return tree[node];
            }

            return sum(node*2, start, (start+end)/2, left, right) + sum(node*2+1, (start+end)/2+1, end, left, right);
        }
    }
}

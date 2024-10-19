package B_2024_10;

import java.io.*;
import java.util.StringTokenizer;

// segtree
// 곱이므로 0이 들어올 경우를 처리해주어야 함
// 구간 곱을 구할 때에는 리프 노드부터 위로 특정 가지 전체에 대해 곱을 수행해주어야 함
public class BOJ11505 {
    static int N, M, K;
    static long[] arr;
    static final long MOD = 1_000_000_007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        MultiplySegTree segTree = new MultiplySegTree(N);
        segTree.init(arr, 1, 1, N);

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==1) {
                arr[b] = c;
                segTree.update(1, 1, N, b, c);
            } else if(a==2) {
                bw.write(segTree.getMultiply(1, 1, N, b, c)+"\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static class MultiplySegTree {
        long[] tree;
        int size;

        public MultiplySegTree(int arrSize) {
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

            return tree[node] = ((init(arr, node*2, start, (start+end)/2)%MOD)*(init(arr, node*2+1, (start+end)/2+1, end)% MOD))% MOD;
        }

        // 구간 곱을 구할때는 top-down이 아니라 leaf부터 bottom-up 방식으로 구해나가야 한다. (루트부터 갱신할경우 0이 되고나서는 복구가 안됨)
        public long update(int node, int start, int end, int idx, int diff) {
            if(idx<start || end<idx) return tree[node];
            if(start==end) return tree[node] = diff;

            int mid = (start+end)/2;
            return tree[node] = (update(node*2, start, mid, idx, diff)*update(node*2+1, mid+1, end, idx, diff))%MOD;
        }

        public long getMultiply(int node, int start, int end, int left, int right) {
            if(left>end || right<start) {
                return 1;
            }

            if(left<=start && end<=right) {
                return tree[node];
            }

            return ((getMultiply(node*2, start, (start+end)/2, left, right)%MOD)*(getMultiply(node*2+1, (start+end)/2+1, end, left, right)%MOD))%MOD;
        }
    }
}

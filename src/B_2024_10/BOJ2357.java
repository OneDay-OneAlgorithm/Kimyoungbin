package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// segtree
public class BOJ2357 {
    static int N, M;
    static long[] arr;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        MaxSegTree maxSeg = new MaxSegTree(N);
        MinSegTree minSeg = new MinSegTree(N);
        maxSeg.init(arr, 1, 1, N);
        minSeg.init(arr, 1, 1, N);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.print(minSeg.getMinVal(1, 1, N, a, b)+" ");
            System.out.println(maxSeg.getMaxVal(1, 1, N, a, b));
        }
    }

    static class MinSegTree {
        long[] tree;
        int size;

        public MinSegTree(int arrSize) {
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

            // 재귀 - leaf노드가 아니라면 자식노드(왼쪽+오른쪽)중 큰 값 담기
            return tree[node] = Math.min(init(arr, node*2, start, (start+end)/2), init(arr, node*2+1, (start+end)/2+1, end));
        }

        public long getMinVal(int node, int start, int end, int left, int right) {
            if(left>end || right<start) {
                return INF;
            }

            if(left<=start && end<=right) {
                return tree[node];
            }

            return Math.min(getMinVal(node*2, start, (start+end)/2, left, right), getMinVal(node*2+1, (start+end)/2+1, end, left, right));
        }
    }

    static class MaxSegTree {
        long[] tree;
        int size;

        public MaxSegTree(int arrSize) {
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

            // 재귀 - leaf노드가 아니라면 자식노드(왼쪽+오른쪽)중 큰 값 담기
            return tree[node] = Math.max(init(arr, node*2, start, (start+end)/2), init(arr, node*2+1, (start+end)/2+1, end));
        }

        public long getMaxVal(int node, int start, int end, int left, int right) {
            if(left>end || right<start) {
                return 0;
            }

            if(left<=start && end<=right) {
                return tree[node];
            }

            return Math.max(getMaxVal(node*2, start, (start+end)/2, left, right), getMaxVal(node*2+1, (start+end)/2+1, end, left, right));
        }
    }
}

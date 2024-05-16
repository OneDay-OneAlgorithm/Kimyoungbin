package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// search) 투 포인터, while문 상세 전체 참고
// search) A, B의 모든 가능한 부배열를 구해서 정렬한 후 투 포인터 계산
// Point 1) 누적합 배열을 따로 구할필요는 없음
// Point 2) 투 포인터 사용시 같은수가 연속으로 나오는 케이스 처리 방법
// WA 1) lcnt*rcnt가 int범위를 초과할 수 있음 (lcnt, rcnt <= 1000*1001/2)
public class BOJ2143 {
    static long T;
    static int n, m;
    static long[] A, B;
    static long[] ASums, BSums;
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력값 받기
        T = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray();
        m = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray();
//        ASum = new long[n]; // A 누적합
//        BSum = new long[m]; // B 누적합
        ASums = new long[n*(n+1)/2]; // 가능한 A 누적합 -> nC2=n*(n+1)/2개
        BSums = new long[m*(m+1)/2]; // 가능한 B 누적합

        // 누적합 초기화
//        ASum[0] = A[0];
//        BSum[0] = B[0];
//        for(int i=1; i<n; i++) {
//            ASum[i] = ASum[i-1]+A[i];
//        }
//        for(int i=1; i<m; i++) {
//            BSum[i] = BSum[i-1]+B[i];
//        }

        // 가능한 모든 누적합 경우를 구한 후 정렬
        int idx = 0;
        for(int i=0; i<n; i++) {
            long sum = 0;
            for(int j=i; j<n; j++) {
//                ASums[idx++] = ASum[j]-ASum[i];
//                long av = ASum[j];
//                if(i>0) av-=ASum[i-1];
//                ASums[idx++] = av;
                sum+=A[j];
                ASums[idx++]=sum;
            }
        }
        idx = 0;
        for(int i=0; i<m; i++) {
            long sum = 0;
            for(int j=i; j<m; j++) {
//                BSums[idx++] = BSum[j]-BSum[i];
//                long bv = BSum[j];
//                if(i>0) bv-=BSum[i-1];
//                BSums[idx++] = bv;
                sum+=B[j];
                BSums[idx++]=sum;
            }
        }
        Arrays.sort(ASums);
        Arrays.sort(BSums);
//        printHint();

        // 투 포인터
        int l=0, r=m*(m+1)/2-1;
        while(l<n*(n+1)/2 && r>=0) {
            long sum = ASums[l]+BSums[r];
            // 같은 수가 연속으로 나올 경우 한번에 계산해준다
            if(sum==T) {
                int lcnt=0, rcnt=0;
                long lval = ASums[l], rval = BSums[r];
                while(l<n*(n+1)/2 && ASums[l]==lval) {
                    l++;
                    lcnt++;
                }
                while(r>=0 && BSums[r]==rval) {
                    r--;
                    rcnt++;
                }
                rst+= (long) lcnt *rcnt; // WA1
            } else if(sum<T) {
                l++;
            } else if(sum>T) {
                r--;
            }
        }
        System.out.println(rst);
    }

    private static void printHint() {
        for (long aSum : ASums) {
            System.out.print(aSum+" ");
        }
        System.out.println();
        for (long bSum : BSums) {
            System.out.print(bSum+" ");
        }
        System.out.println();
    }
}
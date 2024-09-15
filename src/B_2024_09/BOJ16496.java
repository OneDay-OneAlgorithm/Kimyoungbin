package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//TLE) 비효율적인 연산 그냥 compareTo로 개선
public class BOJ16496 {
    static int N;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new String[N];
        for(int i=0; i<N; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
//                char[] ch1 = o1.toCharArray();
//                char[] ch2 = o2.toCharArray();
//                if(Integer.parseInt(o1)==Integer.parseInt(o2)) return o2.compareTo(o1);
//
//                int i=0;
//                while(true) {
//                    if(ch1[i%ch1.length]!=ch2[i%ch2.length]) return ch2[i%ch2.length]-ch1[i%ch1.length];
//                    else i++;
//                }
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        boolean isAllZero = true;
        char[] rst = sb.toString().toCharArray();
        for (char c : rst) {
            if(c!='0') {
                isAllZero = false;
                break;
            }
        }
        System.out.println(isAllZero?0:sb);
    }
}
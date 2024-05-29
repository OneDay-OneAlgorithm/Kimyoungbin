package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// WA1) 식 오류
public class BOJ3019 {
    static int C, P;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        C = Integer.parseInt(line_1[0]);
        P = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        System.out.println(getRst());
    }

    private static int getRst() {
        int rst = 0;
        switch(P) {
            case 1:
                rst += C;
                for(int i=0; i<C-3; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]==arr[i+2] && arr[i+2]==arr[i+3]) rst++;
                }
                break;
            case 2:
                for(int i=0; i<C-1; i++) {
                    if(arr[i]==arr[i+1]) rst++;
                }
                break;
            case 3:
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]+1==arr[i+2]) rst++;
                }
                for(int i=0; i<C-1; i++) {
                    if(arr[i]==arr[i+1]+1) rst++;
                }
                break;
            case 4:
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1]+1 && arr[i+1]==arr[i+2]) rst++;
                }
                for(int i=0; i<C-1; i++) {
                    if(arr[i]+1==arr[i+1]) rst++;
                }
                break;
            case 5:
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]==arr[i+2]) rst++;
                }
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1]+1 && arr[i+1]+1==arr[i+2]) rst++;
                }
                for(int i=0; i<C-1; i++) {
                    if(arr[i]==arr[i+1]+1) rst++;
                    if(arr[i]+1==arr[i+1]) rst++;
                }
                break;
            case 6:
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]==arr[i+2]) rst++;
                }
                for(int i=0; i<C-2; i++) {
                    if(arr[i]+1==arr[i+1] && arr[i+1]==arr[i+2]) rst++;
                }
                for(int i=0; i<C-1; i++) {
                    if(arr[i]==arr[i+1]) rst++;
                    if(arr[i]==arr[i+1]+2) rst++; // WA1)
                }
                break;
            case 7:
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]==arr[i+2]) rst++;
                }
                for(int i=0; i<C-2; i++) {
                    if(arr[i]==arr[i+1] && arr[i+1]==arr[i+2]+1) rst++;
                }
                for(int i=0; i<C-1; i++) {
                    if(arr[i]==arr[i+1]) rst++;
                    if(arr[i]+2==arr[i+1]) rst++; // WA1)
                }
                break;
        }
        return rst;
    }
}

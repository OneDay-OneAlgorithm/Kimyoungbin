import java.io.*;
import java.util.*;

public class BOJ3273
{
    static int n, x;
    static int[] arr;
    static int l, r;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
        }
        x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        l = 0; r = n-1;
        while(l<r) {
            if(arr[l]+arr[r]==x) {
                l++;
                r--;
                rst++;
            } else if(arr[l]+arr[r]<x) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(rst);
    }
}

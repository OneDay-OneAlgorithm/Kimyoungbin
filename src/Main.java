import java.io.*;
import java.util.*;

public class Main
{
    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        System.out.println(N%7==2 || N%7==0 ? "CY" : "SK");
    }
}


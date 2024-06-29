package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BOJ4358 {
    static int N = 0;
    static String tree;
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while((tree = br.readLine())!=null) {
            if(map.containsKey(tree)) {
                int count = map.get(tree);
                map.put(tree, count+1);
            } else {
                map.put(tree, 1);
            }
            N++;
        }
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);

        for(String tree: keySet) {
            System.out.printf("%s %.4f\n", tree, (double)map.get(tree)/N*100);
        }
    }
}
package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

// 방안의 사람들이 모두 밖에서 목격되었다면?
public class BOJ30427 {
    static int N, M;
    static TreeSet<String> set1 = new TreeSet<>();
    static TreeSet<String> set2 = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        N = Integer.parseInt(br.readLine());
        set1.add("swi");
        for(int i=0; i<N; i++) {
            set1.add(br.readLine());
        }
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            set2.add(br.readLine());
        }

        // 1. dongho가 집에 있었으면 dongho가 범인
        if(set1.contains("dongho")) {
            System.out.println("dongho");
        } else if(!목격담이없는사람이단한명이라면그사람의이름은무엇인가().equals("기본값")) {
            System.out.println(목격담이없는사람이단한명이라면그사람의이름은무엇인가());
        } else if(set1.contains("bumin") && !set2.contains("bumin")) {
            System.out.println("bumin");
        } else if(set1.contains("cake") && !set2.contains("cake")) {
            System.out.println("cake");
        } else if(set1.contains("lawyer") && !set2.contains("lawyer")) {
            System.out.println("lawyer");
        } else {
            for (String s : set1) {
                if(s.equals("swi")) {
                    continue;
                }
                if(!set2.contains(s)) {
                    System.out.println(s);
                    return;
                }
            }
        }
    }

    private static String 목격담이없는사람이단한명이라면그사람의이름은무엇인가(){
        int count = 0; // 집안에 있는 사람 중 목격된 사람
        String rest = "기본값";
        for (String s : set1) {
            if(set2.contains(s)) {
                count++;
            } else {
                rest = s;
            }
        }
        if(set1.size()-count==1) return rest;
        else return "기본값";
    }
}
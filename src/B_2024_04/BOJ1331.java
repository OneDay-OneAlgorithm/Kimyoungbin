package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1331 {
    static int pastx = -1;
    static int pasty = -1;
    static int startx, starty;
    static boolean[][] visited = new boolean[6][6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<36; i++) {
            char[] input = br.readLine().toCharArray();
            int x = input[0]-'A';
            int y = input[1]-'1';
            if(!isValid(x, y)) {
                System.out.println("Invalid");
                return;
            }
        }
        // 시작점으로 돌아올 수 있는지 확인
        int xDiff = Math.abs(pastx-startx);
        int yDiff = Math.abs(pasty-starty);

        if((xDiff==1 && yDiff==2) || (xDiff==2 && yDiff==1)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }

    private static boolean isValid(int x, int y) {
//        System.out.print("pastx="+pastx+", x="+x+" | ");
//        System.out.println("pasty="+pasty+", y="+y);

        // 방문한점 또 방문하면 안됨
        if(visited[y][x]) {
            return false;
        }
        visited[y][x] = true;

        // 시작점
        if(pastx==-1 && pasty==-1) {
            pastx = x;
            pasty = y;
            startx = x;
            starty = y;
            return true;
        }

        int xDiff = Math.abs(pastx-x);
        int yDiff = Math.abs(pasty-y);

        if((xDiff==1 && yDiff==2) || (xDiff==2 && yDiff==1)) {
            pastx = x;
            pasty = y;
            return true;
        }

        return false;
    }
}
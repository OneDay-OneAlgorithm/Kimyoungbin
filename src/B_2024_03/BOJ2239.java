package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// n=9이므로 시간복잡도 지수승만 아니면될것같긴 한데, 0을 찾을때마다 1~9를 대입하면서 유망하지 않으면 빠져나오게 해도 되려나?
// 사전식으로 앞서는것이므로 오름차순으로 대입
//1-TL) promising() 파라미터 없는건 TL인 이유? -> 일단 시간여유가 있는 문제가 아닌것같다. promising()써도 아래 테케에서 TL나는걸로 보임
//001090000
//800000700
//000001002
//000560000
//000003200
//000070008
//400800000
//007000004
//000020070
//2-WR) 사전순으로 출력되지 않음 -> isPromising(int y, int x)함수 수정 - y/3에서 y/3*3으로 수정
//정리 - sudoku()와 isPromising()을 썼을때 TL이 나고, sudoku(int y, int x)와 isPromising(int y, int x)를 쓰면 시간초과나지 않음
//시간복잡도는 sudoku()를 쓰면 IDE로도 결과가 나오지 않고, sudoku(int y, int x)와 isPromising()을 쓰면 위에 적어놓은 테케에서 TL이 나는것으로 예상
//sudoku()와 isPromising()을 쓰면 백트래킹*9*9, sudoku(int y, int x)와 isPromising()을 쓰면 백트래킹*9*81, 둘다 인자있는걸로 쓰면 백트래킹*9*9정도로 예상
//백트래킹의 경우 최대 9^81이라 사실상 가늠이 잘 되지는 않음. 다만 백트래킹 풀때 함수마다 전체 검사하기보다는 좌표로 검사하면서 들어가는게 더 빠르다.
public class BOJ2239 {
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        for(int i=0; i<9; i++) {
            String[] line_i = br.readLine().split("");
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(line_i[j]);
            }
        }

        sudoku(0, 0);
    }

    private static void sudoku(int y, int x) {
        if(y==9) {
            printRst();
            System.exit(0);
        }

        if(board[y][x] == 0) {
            for(int i=1; i<=9; i++) {
                board[y][x] = i;
                if(isPromising(y, x)) {
                    if(x==8) {
                        sudoku(y+1, 0);
                    } else {
                        sudoku(y, x+1);
                    }
                }
                board[y][x] = 0;
            }
        } else {
            if(x==8) {
                sudoku(y+1, 0);
            } else {
                sudoku(y, x+1);
            }
        }
    }

    // 특정 좌표에 대해서만 유효한지 검사
    private static boolean isPromising(int y, int x) {
        boolean[] check = new boolean[10];
        for(int i=0; i<9; i++) {
            if(board[y][i]!=0) {
                if(check[board[y][i]]) {
                    return false;
                } else {
                    check[board[y][i]] = true;
                }
            }
        }
        check = new boolean[10];
        for(int i=0; i<9; i++) {
            if(board[i][x]!=0) {
                if(check[board[i][x]]) {
                    return false;
                } else {
                    check[board[i][x]] = true;
                }
            }
        }
        check = new boolean[10];
        for(int i=y/3*3; i<y/3*3+3; i++) { // y/3에서 y/3*3으로 수정
            for(int j=x/3*3; j<x/3*3+3; j++) { // x/3에서 x/3*3으로 수정
                if(board[i][j]!=0) {
                    if(check[board[i][j]]) {
                        return false;
                    } else {
                        check[board[i][j]] = true;
                    }
                }
            }
        }
        return true;
    }

    // 현재까지 스도쿠 배열이 유효한지 검사
    private static boolean isPromising() {
        // 행 검사
        for(int i=0; i<9; i++) {
            boolean[] check = new boolean[10];
            for(int j=0; j<9; j++) {
                if(board[i][j]!=0) {
                    if(check[board[i][j]]) {
                        return false;
                    } else {
                        check[board[i][j]] = true;
                    }
                }
            }
        }

        // 열 검사
        for(int i=0; i<9; i++) {
            boolean[] check = new boolean[10];
            for(int j=0; j<9; j++) {
                if(board[j][i]!=0) {
                    if(check[board[j][i]]) {
                        return false;
                    } else {
                        check[board[j][i]] = true;
                    }
                }
            }
        }

        // 3x3 검사
        for(int i=0; i<9; i+=3) {
            for(int j=0; j<9; j+=3) {
                boolean[] check = new boolean[10];
                for(int k=i; k<i+3; k++) {
                    for(int l=j; l<j+3; l++) {
                        if(board[k][l]!=0) {
                            if(check[board[k][l]]) {
                                return false;
                            } else {
                                check[board[k][l]] = true;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private static void printRst() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }


    private static void sudoku() {
        boolean exit = true;
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j]==0) exit = false;
            }
        }
        if(exit) {
            printRst();
            System.exit(0);
        }

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j]==0) {
                    for(int k=1; k<=9; k++) { // 1~9 대입 백트래킹
                        board[i][j] = k;
                        if(isPromising()) sudoku();
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
}
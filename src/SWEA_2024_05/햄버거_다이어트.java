package SWEA_2024_05;/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
// 이번 재료를 포함하거나, 포함하지 않거나
class 햄버거_다이어트
{
    static int N, L;
    static Food[] arr;
    static int[][] dp; // dp[i][j]: 0~i번 재료를 사용하여 j칼로리 이하의 조합에서 가장 높은 점수
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
        int T;
        T=Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] line_1 = br.readLine().split(" ");
            N = Integer.parseInt(line_1[0]); // 재료 수
            L = Integer.parseInt(line_1[1]); // 제한 칼로리
            arr = new Food[N];
            dp = new int[N][L+1];

            for(int i=0; i<N; i++) {
                String[] line_N = br.readLine().split(" ");
                int score = Integer.parseInt(line_N[0]);
                int cal = Integer.parseInt(line_N[1]);
                arr[i] = new Food(score, cal);
            }

            // dp 초기화 (첫번째 음식)
            for(int i=0; i<=L; i++) {
                if(arr[0].cal<=i) {
                    dp[0][i] = arr[0].score;
                }
            }
            for(int i=1; i<N; i++) {
                Food cur = arr[i];
                for(int j=0; j<=L; j++) {
                    if(j-cur.cal>=0) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cur.cal]+cur.score);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            System.out.println("#"+test_case+" "+dp[N-1][L]);
        }
    }

    // 재료
    static class Food {
        int score; // 점수
        int cal; // 칼로리

        public Food(int score, int cal) {
            this.score = score;
            this.cal = cal;
        }
    }

}
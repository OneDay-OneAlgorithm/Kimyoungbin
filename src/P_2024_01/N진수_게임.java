package P_2024_01;

class N진수_게임 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder convert = new StringBuilder();
        StringBuilder ans = new StringBuilder();

        for(int i=0; convert.length()<=t*m; i++){
            convert.append(Integer.toString(i, n)); // integer i를 n진수 변환
        }

        for(int i=p-1; ans.length()<t; i+=m){
            ans.append(convert.charAt(i));
        }

        return ans.toString().toUpperCase();
    }
}
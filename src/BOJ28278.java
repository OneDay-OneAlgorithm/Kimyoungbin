import java.io.*;
import java.util.*;

public class BOJ28278
{
    static int N;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String[] commands = br.readLine().split(" ");
            int command = Integer.parseInt(commands[0]);
            switch(command) {
                case 1:
                    stack.add(Integer.parseInt(commands[1]));
                    break;
                case 2:
                    if(stack.isEmpty()) {
                        bw.write(-1+"\n");
                        break;
                    }
                    bw.write(stack.pop()+"\n");
                    break;
                case 3:
                    bw.write(stack.size()+"\n");
                    break;
                case 4:
                    int rst = stack.isEmpty()?1:0;
                    bw.write(rst+"\n");
                    break;
                case 5:
                    if(stack.isEmpty()) {
                        bw.write(-1+"\n");
                        break;
                    }
                    bw.write(stack.peek()+"\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
    }
}

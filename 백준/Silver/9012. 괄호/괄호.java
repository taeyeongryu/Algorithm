import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.clear();
            String input = br.readLine();
            boolean flag = true;
            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);
                if(ch=='('){
                    stack.add(ch);
                }else{
                   if(!stack.isEmpty()){
                       stack.pop();
                   }else{
                       bw.append("NO\n");
                       flag = false;
                       break;
                   }
                }
            }
            if(flag){
                if(!stack.isEmpty()){
                    bw.append("NO\n");
                }else{
                    bw.append("YES\n");
                }
            }
        }
        bw.close();
        br.close();

    }
}
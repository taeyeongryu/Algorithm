import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int K = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int total = 0;
        for (int i = 0; i < K; i++) {

            int input = Integer.parseInt(br.readLine());
            if(input==0){
                if(!stack.isEmpty()){
                    int pop = stack.pop();
                    total -= pop;
                }
            }else {
                stack.add(input);
                total += input;
            }
        }
        System.out.println(total);
    }
}
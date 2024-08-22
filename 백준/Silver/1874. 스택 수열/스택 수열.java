import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        boolean flag = true;
        outer : for (int i = 1; i <= N; i++) {
//            System.out.println("stack = " + stack);
            int wanted = arr[index];
            int peek = stack.isEmpty() ? 0 : stack.peek();

            if (peek < wanted) {
                stack.add(i);
                sb.append("+\n");
            }
            while (!stack.isEmpty()) {
                wanted = arr[index];
                peek = stack.peek();
                if(peek==wanted){
                    stack.pop();
                    index++;
                    sb.append("-\n");
                } else if (peek > wanted) {
                    sb = new StringBuilder("NO");
                    break outer;
                }else{
                    break;
                }
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
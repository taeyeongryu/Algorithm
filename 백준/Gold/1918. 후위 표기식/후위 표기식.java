import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    static List<Character> operater = new ArrayList<Character>();
    static {
        operater.add('+');
        operater.add('*');
        operater.add('-');
        operater.add('/');
        operater.add('(');
        operater.add(')');
    }
    static String behind(String str) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 연산자이면
            if (operater.contains(ch)) {
                //열린 괄호라면 그냥 넣는다.
                if (ch == '(') {
                    stack.add(ch);
                }
                //닫힌 괄호라면 열린 괄호가 나올 때 까지 계속 뺀다
                else if (ch == ')') {
                    while (!stack.isEmpty()) {
                        if (stack.peek() == '(') {
                            stack.pop();
                            break;
                        } else {
                            sb.append(stack.pop());
                        }
                    }
                }
                //덧샘 혹은 뺄셈이라면 ? 앞에 있는 연산자중에 우선순위가 높은 연산자가 있으면 빼서 출력한다.
                else if (ch == '+'||ch == '-') {
                    if (stack.isEmpty()) {
                        stack.add(ch);
                    } else {
                        while (!stack.isEmpty()) {
                            if (stack.peek() == '*'||stack.peek() == '/'||stack.peek() == '+'||stack.peek() == '-') {
                                sb.append(stack.pop());
                            }else{
                                break;
                            }
                        }
                        stack.add(ch);
                    }
                } else {
                    while (!stack.isEmpty()&& (stack.peek()=='*'||stack.peek()=='/')){
                        sb.append(stack.pop());
                    }
                    stack.add(ch);
                }
            }
            // 아니면
            else {
                sb.append(ch);
            }
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            Character pop = stack.pop();
            sb.append(pop);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String behind = behind(br.readLine());

        bw.append(behind);
        bw.close();
        br.close();
    }
}
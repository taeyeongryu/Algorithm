import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputStr = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < inputStr.length(); i++) {
            stack.add(inputStr.charAt(i));

            //스택에 검사할 만큼의 문자열이 쌓여있다면
            if (stack.size() >= bomb.length()) {
                boolean isBomb = true;
                //검사 진행
                for (int j = 0; j < bomb.length(); j++) {
                    Character checkChar = stack.get(stack.size() - bomb.length() + j);
                    //문자열이 하나라도 같지 않은게 있다면
                    if (checkChar != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }
                if(isBomb){
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            bw.append("FRULA");
        }else{

            int stackSize = stack.size();
            for (int i = 0; i < stackSize; i++) {
                bw.append(stack.get(i));
            }
        }

        bw.close();
        br.close();

    }
}
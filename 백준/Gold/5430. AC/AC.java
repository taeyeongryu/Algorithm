import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int T;
    static boolean isDir;
    static String operator;
    static Deque<String> deque;
    static StringBuilder sb;

    static boolean oper() {
        for (int i = 0; i < operator.length(); i++) {
            char ch = operator.charAt(i);
            if (ch == 'R') {
                isDir = !isDir;  // 방향만 변경
            } else {
                if (deque.isEmpty()) {  // 덱이 비었을 경우 오류
                    return false;
                }
                // 방향에 따른 삭제
                if (isDir) {
                    deque.pollFirst();
                } else {
                    deque.pollLast();
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            isDir = true;
            deque = new ArrayDeque<>();
            operator = br.readLine();
            int arrLength = Integer.parseInt(br.readLine());
            String inputList = br.readLine();
            if (arrLength > 0) {
                String trimList = inputList.substring(1, inputList.length() - 1);
                String[] split = trimList.split(",");
                for (int i = 0; i < split.length; i++) {
                    deque.add(split[i]);
                }
            }
            boolean flag = oper();
            if (flag) {
                makeString(true);
            } else {
                makeString(false);
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void makeString(boolean success) {
        if (success) {
            sb.append("[");
            // 정방향 출력
            if (isDir) {
                while (!deque.isEmpty()) {
                    sb.append(deque.pollFirst());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
            }
            // 역방향 출력
            else {
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]\n");
        } else {
            sb.append("error\n");
        }
    }
}
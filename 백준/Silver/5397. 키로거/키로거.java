import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String str = br.readLine();
			Stack<Character> lst = new Stack<>();
			Stack<Character> rst = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (ch == '<') {
					if (!lst.isEmpty()) {
						rst.push(lst.pop());
					}
				} else if (ch == '>') {
					if (!rst.isEmpty()) {
						lst.push(rst.pop());
					}
				} else if (ch == '-') {
					if (!lst.isEmpty()) {
						lst.pop();
					}
				}
				// 명령어 아닌 문자일때
				else {
					lst.push(ch);
				}
			}
			for (int i = 0; i < lst.size(); i++) {
				bw.append(lst.get(i));
			}
			int rst_size = rst.size();
			for (int i = 0; i < rst_size; i++) {
				bw.append(rst.pop());
			}
			bw.append("\n");
			bw.flush();
		}
	}
}
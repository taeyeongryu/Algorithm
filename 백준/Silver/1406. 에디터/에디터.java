import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> leftst = new Stack<>();
		Stack<Character> rightst = new Stack<>();
		String str = br.readLine().trim();
		for (int i = 0; i < str.length(); i++) {
			leftst.add(str.charAt(i));
		}
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			if (oper.equals("L")) {
				if (!leftst.isEmpty()) {
					rightst.push(leftst.pop());
				}
			} else if (oper.equals("D")) {
				if (!rightst.isEmpty()) {
					leftst.push(rightst.pop());
				}
			} else if (oper.equals("B")) {
				if (!leftst.isEmpty()) {
					leftst.pop();
				}

			}
			// p일때
			else {
				char tmp = st.nextToken().charAt(0);
			leftst.push(tmp);

			}
		}
		for (int i = 0; i < leftst.size(); i++) {
			bw.append(leftst.get(i));
		}
		int rightstsize = rightst.size();
		for (int i = 0; i < rightstsize; i++) {
			bw.append(rightst.pop());
		}
		bw.flush();
	}

}
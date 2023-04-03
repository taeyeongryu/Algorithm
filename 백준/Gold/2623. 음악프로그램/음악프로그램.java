import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int PD;
	static int[] in;
	static Queue<Integer> queue = new LinkedList<>();
	static ArrayList<Integer>[] adjlist;
	static ArrayList<Integer> resultlist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		PD = Integer.parseInt(st.nextToken());
		in = new int[N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < PD; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] tmp = new int[n];
			for (int j = 0; j < tmp.length; j++) {
				tmp[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < tmp.length - 1; j++) {
				adjlist[tmp[j]].add(tmp[j + 1]);
				in[tmp[j + 1]]++;
			}
		}
		for (int i = 1; i <= N; i++) {
			if (in[i] == 0) {
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int tmp = queue.poll();
			resultlist.add(tmp);
			for (int i = 0; i < adjlist[tmp].size(); i++) {
				in[adjlist[tmp].get(i)]--;
				if (in[adjlist[tmp].get(i)] == 0) {
					queue.offer(adjlist[tmp].get(i));
				}
			}
		}
		if (resultlist.size()==N) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < resultlist.size(); i++) {
				sb.append(resultlist.get(i)+"\n");
			}
			System.out.println(sb.toString());
		}else {
			System.out.println(0);
		}
	}

}
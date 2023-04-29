import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int[] position = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			position[arr[i]] = i;
		}
		// 첫번째에 있는 숫자부터 검사한다.
		int max = 1;
		for (int i = 1; i < N; i++) {
			int cur_num = arr[i];
			int cur_idx = position[cur_num];
			if (cur_num==N) {
				continue;
			}
			int next_num = cur_num + 1;
			int next_idx = position[next_num];
			int count = 1;
			while (cur_idx < next_idx && next_num <= N) {
				count++;
				cur_num = next_num;
				next_num = cur_num + 1;
				if (next_num <= N) {
					cur_idx = position[cur_num];
					next_idx = position[next_num];
				}
				else {
					break;
				}
			}
			if (max < count) {
				max = count;
			}
//			System.out.println("cur_idx : "+i+", count : "+count);
		}
		System.out.println(N-max);
	}
}
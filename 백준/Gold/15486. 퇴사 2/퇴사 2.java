import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		int[] D = new int[N + 1];
		for (int i = 1; i < P.length; i++) {
			st = new StringTokenizer(br.readLine());
			T[i]=Integer.parseInt(st.nextToken());
			P[i]=Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			//먼저 i번째 날까지 번 최고의 돈을 업데이트 해준다.
			D[i]=Math.max(D[i-1], D[i]);
			if (i+T[i]-1>N) {
				continue;
			}
			//오늘 일하면 일끝나는 날에 최고 얼마를 벌 수 있는지 업데이트한다.
			D[i+T[i]-1]=Math.max(D[i+T[i]-1], D[i-1]+P[i]);
		}
		System.out.println(D[N]);
	}

}
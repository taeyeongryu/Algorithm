import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] S, W;
	static int max = 0, cnt = 0;

	static void back(int depth) {
		if (depth==N) {
			max = Math.max(max, cnt);
			return;
		}
		if (S[depth]<=0||cnt==N-1) {
			back(depth+1);
			return;
			}
		
		for (int i = 0; i < N; i++) {
			if (depth==i||S[i]<=0) continue;
			S[depth]-=W[i];
			S[i]-=W[depth];
			if(S[depth]<=0)cnt++;
			if(S[i]<=0)cnt++;
			back(depth+1);
			if(S[depth]<=0)cnt--;
			if(S[i]<=0)cnt--;
			S[depth]+=W[i];
			S[i]+=W[depth];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = new int[10];
		W = new int[10];
		for (int i = 0; i < N; i++) {
			int g = sc.nextInt();
			int w = sc.nextInt();
			S[i] = g;
			W[i] = w;
		}
			back(0);
			System.out.println(max);
	}
}
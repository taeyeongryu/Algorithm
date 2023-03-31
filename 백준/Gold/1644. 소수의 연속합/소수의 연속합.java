import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] primearr = new boolean[4000001];
		Arrays.fill(primearr, true);
		primearr[0] = false;
		primearr[1] = false;
		for (int i = 2; i < primearr.length; i++) {
			if (primearr[i]) {
				for (int j = 2 * i; j < primearr.length; j += i) {
					primearr[j] = false;
				}
			}
		}
		int primecount = 0;
		for (int i = 2; i < primearr.length; i++) {
			if (primearr[i]) {
				primecount++;
			}
		}
		int[] prime = new int[primecount];
		int idx = 0;
		for (int i = 2; i < primearr.length; i++) {
			if (primearr[i]) {
				prime[idx++] = i;
			}
		}
		int count = 0;
		int left = 0;
		int right = 0;
		long result = 2;
		while (left<prime.length&&right<prime.length) {
			if (result==N) {
				count++;
				result-=prime[left++];
			}
			else if (result>N) {
				result-=prime[left++];
			}else {
				right++;
				if (right>=prime.length) {
					break;
				}
				result+=prime[right];
			}
			
		}
		System.out.println(count);
	}

}
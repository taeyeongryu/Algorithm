import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = 0;
		int sum = arr[left];
		int min = Integer.MAX_VALUE;
		while (left <= right) {
			// 마지막 인덱스보다 작을때
//			System.out.println("left : " + left + ", right : " + right);
//			System.out.println(sum);
			if (right < arr.length - 1) {
				if (sum < S) {
					sum += arr[++right];
				} else if (sum == S) {
					min = Math.min(min, right - left + 1);
					sum -= arr[left++];
				} else {
					min = Math.min(min, right - left + 1);
					sum -= arr[left++];
				}
			}
			// 마지막 인덱스에 도착했을때
			else {
				if (sum < S) {
					break;
				} else if (sum == S) {
					min = Math.min(min, right - left + 1);
					break;
				} else {
					min = Math.min(min, right - left + 1);
					sum -= arr[left++];
				}
			}
		}
		System.out.println(min==Integer.MAX_VALUE?0:min);
	}

}
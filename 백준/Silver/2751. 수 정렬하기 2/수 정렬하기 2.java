import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] buff;

	static void __mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;

			__mergeSort(a, left, center);
			__mergeSort(a, center + 1, right);
			for (i = left; i <= center; i++) {
				buff[p++] = a[i];
			}
			while (i <= right && j < p) {
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			}
			while (j < p) {
				a[k++] = buff[j++];
			}
		}
	}

	static void mergeSort(int[] a, int n) {
		buff = new int[n];
		__mergeSort(a, 0, n - 1);
		buff = null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		for (int i = 0; i < a.length; i++) {
			a[i]=Integer.parseInt(br.readLine());
		}
		mergeSort(a, N);
		for (int i = 0; i < a.length; i++) {
			bw.write(a[i]+"\n");
		}
		bw.flush();
		bw.close();
	}

}

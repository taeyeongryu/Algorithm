import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[] L;
	static int N,idx;
	//들어갈 위치 찾아주는 메서드
	//arr[i]값보다 크거나 같은 값의 인덱스를 반환한다.
	static int binary(int i) {
		int start = 0;
		int end = idx;
		int mid = 0;
		while(start<end) {
			mid = (start+end)/2;
			if (L[mid]<arr[i]) {
				start=mid+1;
			}else {
				end = mid;
			}
		}
		return end;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		L = new int[N+1];
		idx=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <=N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <=N; i++) {
			if (L[idx]<arr[i]) {
				L[++idx]=arr[i];
			}
			else {
				int addidx=binary(i);
				L[addidx]=arr[i];
			}
		}
		System.out.println(idx);
	}

}
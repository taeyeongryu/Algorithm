import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		List<Integer> arr = new ArrayList<>();
		int[] check = new int[N];
		int result = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			arr.add(sc.nextInt());
		}
		if (N >= K) {
		} else {
			int count = 0;
			outer: for (int i = 0; i < K; i++) {
//				System.out.println(list);
				// 콘센트에 꼽혀있다면
//				System.out.println(list);
				if (list.contains(arr.get(i))) {
					continue;
				}
				// 아니라면
				else {
					// 빈칸있니?
					if (count < N) {
						list.add(arr.get(i));
						count++;
					}
					// 없다.
					else {
						// 빈칸없고, 하나 빼야하는 상황
						check = new int[N];
						Arrays.fill(check, -1);
						for (int j = i+1; j < arr.size(); j++) {
							if (list.contains(arr.get(j))) {
//								System.out.println(1);
								if (check[list.indexOf(arr.get(j))]==-1) {
									check[list.indexOf(arr.get(j))]=j;
								}
							}
						}
//						System.out.println("check : "+Arrays.toString(check));
						int arr_idx = 0;
						int list_idx = 0;
						for (int j = 0; j < check.length; j++) {
							if (check[j]==-1) {
								list.remove(j);
								list.add(arr.get(i));
								result++;
								continue outer;
							}
							else {
								if (check[j]>arr_idx) {
									arr_idx=check[j];
									list_idx=j;
								}
							}
						}
						list.remove(list_idx);
						list.add(arr.get(i));
						result++;
					}
				}
			}
		}
		System.out.println(result);
	}

}
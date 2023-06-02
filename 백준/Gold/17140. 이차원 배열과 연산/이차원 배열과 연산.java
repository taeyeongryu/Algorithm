import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class CountNum implements Comparable<CountNum> {
		int num;
		int count;

		@Override
		public int compareTo(CountNum o) {
			if (this.count < o.count) {
				return -1;
			} else if (this.count > o.count) {
				return 1;
			} else {
				if (this.num < o.num) {
					return -1;
				} else {
					return 1;
				}
			}
		}

		public CountNum(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

	}

	static int maxR = 3;
	static int maxC = 3;
	static int[][] map = new int[101][101];

	static List<Integer> operation(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				int tmp = map.getOrDefault(arr[i], 0);
				map.put(arr[i], tmp + 1);
			}
		}
		// 갯수는 다 셋다.
		PriorityQueue<CountNum> pq = new PriorityQueue<>();
		for (Integer key : map.keySet()) {
			Integer value = map.get(key);
			pq.offer(new CountNum(key, value));
		}
		List<Integer> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			CountNum tmp = pq.poll();
			list.add(tmp.num);
			list.add(tmp.count);
		}
		return list;
	}

	static void time() {
		int[][] tmp = new int[101][101];
		// 행연산
		if (maxR >= maxC) {
			for (int i = 1; i <= maxR; i++) {
				List<Integer> list = operation(map[i]);
				if (list.size() > 100) {
					for (int j = 0; j < list.size(); j++) {
						if (j == 100) {
							break;
						}
						tmp[i][j + 1] = list.get(j);
					}
				} else {
					for (int j = 0; j < list.size(); j++) {
						tmp[i][j + 1] = list.get(j);
					}
					for (int j = list.size(); j < 100; j++) {
						tmp[i][j+1] = 0;
					}
				}
				maxC = Math.max(maxC, list.size());
			}
			if (maxC >= 100) {
				maxC = 100;
			}
		}
		// 열연산
		else {
			for (int i = 1; i <= maxC; i++) {
				int[] tmparr = new int[101];
				for (int j = 0; j < tmparr.length; j++) {
					tmparr[j] = map[j][i];
				}
				List<Integer> list = operation(tmparr);
				if (list.size() > 100) {
					for (int j = 0; j < list.size(); j++) {
						if (j == 100) {
							break;
						}
						tmp[j + 1][i] = list.get(j);
					}
				} else {
					for (int j = 0; j < list.size(); j++) {
						tmp[j + 1][i] = list.get(j);
					}
					for (int j = list.size(); j < 100; j++) {
						tmp[j+1][i] = 0;
					}
				}
				maxR = Math.max(maxR, list.size());
			}
			if (maxR >= 100) {
				maxR = 100;

			}
		}
		map = tmp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int k = sc.nextInt();
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		int t=0;
		while(t<=100) {
			if(map[r][c]==k) {
				break;
			}
			t++;
			time();
		}
		System.out.println(t>100?-1:t);
	}

}
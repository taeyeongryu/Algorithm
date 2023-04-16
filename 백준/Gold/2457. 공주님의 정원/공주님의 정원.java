import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Flower implements Comparable<Flower> {
		int start;
		int end;

		@Override
		public String toString() {
			return "Flower [start=" + start + ", end=" + end + "]";
		}

		public Flower(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Flower flower) {
			return Integer.compare(start, flower.start);
		}
	}

	public static void main(String[] args) throws IOException {
		int[] monthday = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int sum[] = new int[13];
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + monthday[i];
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] choice = new boolean[N];
		List<Flower> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int month1 = Integer.parseInt(st.nextToken());
			int day1 = Integer.parseInt(st.nextToken());
			int month2 = Integer.parseInt(st.nextToken());
			int day2 = Integer.parseInt(st.nextToken());
			int start = sum[month1 - 1] + day1;
			int end = sum[month2 - 1] + day2 - 1;
			list.add(new Flower(start, end));
		}
		Collections.sort(list);
		//start가 이것 전인것 중에서 끝나는 날이 가장 전인것 고른다.
		//이날부터 선택해야한다.
		int end = 60;
		//선택한 꽃의 수
		int count=0;
		while (true) {
			//이미 선택이 끝났다면
			if (334<end) {
				break;
			}
			boolean flag = false;
			int curend = 0;
			int choiceidx = -1;
			for (int i = 0; i < list.size(); i++) {
				if (!choice[i]&&list.get(i).start<=end) {
					flag=true;
					if (list.get(i).end>curend) {
						curend=list.get(i).end;
						choiceidx = i;
//						System.out.println(curend);
					}
				}
			}
			if (flag) {
				if (list.get(choiceidx).end<end) {
					count=-1;
					break;
				}
//				System.out.println(list.get(choiceidx));
				count++;
				choice[choiceidx]=true;
				end=list.get(choiceidx).end+1;
			}
			else {
				count=-1;
				break;
			}
		}
		System.out.println(count==-1?0:count);
	}
}
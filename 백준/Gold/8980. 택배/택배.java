import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Vilage implements Comparable<Vilage> {
		int start;
		int end;
		int weight;

		public Vilage(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(Vilage o) {
			if (this.end < o.end) {
				return -1;
			} else if (this.end > o.end) {
				return 1;
			} else {
				if (this.start < o.start) {
					return 1;
				} else {
					return -1;
				}
			}
		}

		@Override
		public String toString() {
			return "Vilage [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] vilweight = new int[N+1];
		Arrays.fill(vilweight, C);
		List<Vilage> list = new ArrayList<>();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Vilage(start, end, weight));
		}
		Collections.sort(list);
		
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			Vilage vil = list.get(i);
			int start = vil.start;
			int end = vil.end;
			int weight = vil.weight;
			int min = vilweight[start];
			for (int j = start; j < end; j++) {
				if (min > vilweight[j]) {
					min = vilweight[j];
				}
			}
			int cha = Math.min(min, weight);
			for (int j = start; j <end; j++) {
				vilweight[j]-=cha;
			}
			total+=cha;
		}
		
		System.out.println(total);
	}
}
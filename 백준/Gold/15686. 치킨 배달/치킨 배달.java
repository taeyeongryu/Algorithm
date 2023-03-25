import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static int M;
	// 여기에 각 치킨집을 돌면서 인덱스에 해당하는 집과의 최소거리를 업데이트 한다.

	// 길이는 집의 갯수만큼 설정한다.
	// M개를 정한 배열 여기에 치킨집을 저장한다.
	static int[] choice_chicken;
	static int minchickendis = Integer.MAX_VALUE;

	static List<Node> chickenlist = new ArrayList<>();
	static List<Node> houselist = new ArrayList<>();

	static int dis(Node node1, Node node2) {
		return Math.abs(node1.r - node2.r) + Math.abs(node1.c - node2.c);
	}

	// M개의 치킨집을 고르는 메서드
	static void combi(int depth, int count, int at) {
		if (count == M) {
			// 거리 계산하고 리턴
			find_dis();
			return;
		}
		if (depth == chickenlist.size()) {
			return;
		}
		for (int i = at; i < chickenlist.size(); i++) {
			choice_chicken[depth] = i;
			combi(depth + 1, count + 1, i + 1);
		}
	}

	// 치킨집과 집들의 치킨거리를 재면서 최소값을 경신하는 메서드
	static void find_dis() {
		int[] dis = new int[houselist.size()];
		for (int i = 0; i < dis.length; i++) {
			dis[i]=Integer.MAX_VALUE;
		}
		for (int i = 0; i < choice_chicken.length; i++) {
			Node chicken = chickenlist.get(choice_chicken[i]);
			for (int j = 0; j < houselist.size(); j++) {
				Node house = houselist.get(j);
				int tmp = dis(chicken, house);
				dis[j]=Math.min(dis[j], tmp);
			}
		}
		int total=0;
		for (int i = 0; i < dis.length; i++) {
			total+=dis[i];
		}
		minchickendis=Math.min(total, minchickendis);
	}

	public static void main(String[] args) {
		// chicken_dis의 배열 최대값으로 초기화 해야한다.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		choice_chicken = new int[M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = sc.nextInt();
				if (tmp == 1) {
					houselist.add(new Node(i, j));
				} else if (tmp == 2) {
					chickenlist.add(new Node(i, j));
				}
			}
		}
		combi(0, 0, 0);
		System.out.println(minchickendis);
	}

}
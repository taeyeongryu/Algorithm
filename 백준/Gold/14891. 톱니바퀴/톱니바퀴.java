import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static LinkedList<Integer> list1 = new LinkedList<>();
	static LinkedList<Integer> list2 = new LinkedList<>();
	static LinkedList<Integer> list3 = new LinkedList<>();
	static LinkedList<Integer> list4 = new LinkedList<>();
	static boolean[] check;

	static void rotate(int listnum, int rotatedirection) {
		// 리스트를 먼저 복사하고
		LinkedList<Integer> tmplist = new LinkedList<>();
		for (int i = 0; i < list1.size(); i++) {
			switch (listnum) {
			case 1:
				tmplist.add(list1.get(i));
				break;
			case 2:
				tmplist.add(list2.get(i));
				break;
			case 3:
				tmplist.add(list3.get(i));
				break;
			case 4:
				tmplist.add(list4.get(i));
				break;
			}
		}
		// 원본 회전시킨다.
		// 시계
		if (rotatedirection == 1) {
			switch (listnum) {
			case 1:
				list1.add(0, list1.pollLast());
				break;
			case 2:
				list2.add(0, list2.pollLast());
				break;
			case 3:
				list3.add(0, list3.pollLast());
				break;
			case 4:
				list4.add(0, list4.pollLast());
				break;
			}
		}
		// 반시계
		else {
			switch (listnum) {
			case 1:
				list1.add(list1.pollFirst());
				break;
			case 2:
				list2.add(list2.pollFirst());
				break;
			case 3:
				list3.add(list3.pollFirst());
				break;
			case 4:
				list4.add(list4.pollFirst());
				break;
			}
		}
		// 그리고 비교한다.

		if (listnum == 1) {
			if (!check[2]) {
				boolean tmp = turncheck(tmplist, list2, rotatedirection, 1);
				check[2] = true;
				if (tmp) {
					rotate(2, -rotatedirection);
				}
			}
		} else if (listnum == 4) {
			if (!check[3]) {
				boolean tmp = turncheck(tmplist, list3, rotatedirection, -1);
				check[3] = true;
				if (tmp) {
					rotate(3, -rotatedirection);
				}
			}
		} else if (listnum == 3) {
			if (!check[4]) {
				boolean tmp = turncheck(tmplist, list4, rotatedirection, 1);
				check[4] = true;
				if (tmp) {
					rotate(4, -rotatedirection);
				}
			}
			if (!check[2]) {
				boolean tmp = turncheck(tmplist, list2, rotatedirection, -1);
				check[2] = true;
				if (tmp) {
					rotate(2, -rotatedirection);
				}
			}
		}
		// 2를 회전시키는 것이라면
		else {
			if (!check[1]) {
				boolean tmp = turncheck(tmplist, list1, rotatedirection, -1);
				check[1] = true;
				if (tmp) {
					rotate(1, -rotatedirection);
				}
			}
			if (!check[3]) {
				boolean tmp = turncheck(tmplist, list3, rotatedirection, 1);
				check[3] = true;
				if (tmp) {
					rotate(3, -rotatedirection);
				}
			}
		}
	}

	static boolean turncheck(LinkedList list1, LinkedList list2, int roatatedirection, int direction) {
		boolean flag = false;
		// 기준에서 좌측 방향
		if (direction == -1) {
			// 시계방향
			if (roatatedirection == 1) {
				if (list1.get(6) != list2.get(2)) {
					flag = true;
				}
			}
			// 시계반대방향
			else {
				if (list1.get(6) != list2.get(2)) {
					flag = true;
				}
			}
		}
		// 기준에서 우측방향
		else {
			// 시계방향
			if (roatatedirection == 1) {
				if (list1.get(2) != list2.get(6)) {
					flag = true;
				}
			}
			// 시계반대방향
			else {
				if (list1.get(2) != list2.get(6)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 4; i++) {
			String tmp = sc.next();
			for (int j = 0; j < 8; j++) {
				switch (i) {
				case 1:
					list1.add(tmp.charAt(j) - '0');
					break;
				case 2:
					list2.add(tmp.charAt(j) - '0');
					break;
				case 3:
					list3.add(tmp.charAt(j) - '0');
					break;
				case 4:
					list4.add(tmp.charAt(j) - '0');
					break;

				}
			}
		}
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int num = sc.nextInt();
			int dir = sc.nextInt();
			check = new boolean[5];
			check[num] = true;
			rotate(num, dir);
		}
		int result = 0;
		for (int i = 1; i <= 4; i++) {
			switch (i) {
			case 1:
				if (list1.get(0) == 1) {
					result += 1;
				}
				break;
			case 2:
				if (list2.get(0) == 1) {
					result += 2;
				}
				break;
			case 3:
				if (list3.get(0) == 1) {
					result += 4;
				}
				break;
			case 4:
				if (list4.get(0) == 1) {
					result += 8;
				}
				break;
			}
		}
		System.out.println(result);
	}

}
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            sb.append("#" +tc+" ");
            int N = sc.nextInt();
            for (int i = 0; i < N; i++) {
                int oper = sc.nextInt();
                if (oper == 1) {
                    pq.offer(-sc.nextInt());
                } else {
                    if (pq.isEmpty()) {
                        sb.append("-1 ");
                    } else {
                        int pullnum = -pq.poll();
                        sb.append(pullnum + " ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
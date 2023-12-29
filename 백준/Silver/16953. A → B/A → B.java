import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {
    static class Node{
        long num;
        long count;

        public Node(long num, long count) {
            this.num = num;
            this.count = count;
        }
    }

    static long min;
    static void bfs(long a,long b){
        Queue<Node> q = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        set.add(a);
        q.offer(new Node(a, 1));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            long curNum = cur.num;
            long curCount = cur.count;

            long nextNum1 = curNum * 2;
            long nextNum2 = curNum * 10 + 1;

            if (nextNum1 < b) {
                if(!set.contains(nextNum1)){
                    set.add(nextNum1);
                    q.add(new Node(nextNum1, curCount + 1));
                }
            } else if (nextNum1 == b) {
                min = curCount;
            }
            if (nextNum2 < b) {
                if(!set.contains(nextNum2)){
                    set.add(nextNum2);
                    q.add(new Node(nextNum2, curCount + 1));
                }
            } else if (nextNum2 == b) {
                min = curCount;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());

        bfs(A, B);
        bw.append(min == 0 ? "-1" : min + 1 + "");
        bw.close();
        br.close();

    }
}
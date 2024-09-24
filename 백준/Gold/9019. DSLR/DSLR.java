import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int index;
        String oper;
        public Node(int index, String oper){
            this.index = index;
            this.oper = oper;
        }
    }
    public static int D(int input){
        return (input * 2) % 10000;
    }
    public static int S(int input){
        return input - 1 == -1 ? 9999 : input - 1;
    }
    public static int L(int input){
        int q = input / 1000;
        int r = input % 1000;
        return r * 10 + q;
    }
    public static int R (int input){
        int q = input / 10;
        int r = input % 10;
        return r * 1000 + q;
    }
    static boolean[] visited;
    static Queue<Node> queue;
    static String bfs(int start, int end){
        String result = "";
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curIndex = cur.index;
            String curOper = cur.oper;

            if(curIndex==end){
                result = curOper;
                break;
            }

            int nextD = D(curIndex);
            int nextS = S(curIndex);
            int nextL = L(curIndex);
            int nextR = R(curIndex);
          
            if (!visited[nextD]) {
                visited[nextD] = true;
                queue.offer(new Node(nextD, curOper + "D"));
            }

            if (!visited[nextS]) {
                visited[nextS] = true;
                queue.offer(new Node(nextS, curOper + "S"));
            }

            if (!visited[nextL]) {
                visited[nextL] = true;
                queue.offer(new Node(nextL, curOper + "L"));
            }

            if (!visited[nextR]) {
                visited[nextR] = true;
                queue.offer(new Node(nextR, curOper + "R"));
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            queue = new LinkedList<>();
            visited[start]=true;
            queue.offer(new Node(start, ""));
            String result = bfs(start, end);
            bw.append(result + "\n");
        }
        bw.close();
        br.close();
    }
}
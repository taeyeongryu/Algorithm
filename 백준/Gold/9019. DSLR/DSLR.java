import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int num;
        String operations;

        public Node(int num, String operations) {
            this.num = num;
            this.operations = operations;
        }
    }

    static boolean[] visited;
    static String bfs(int start, int end){
        Queue<Node> q = new LinkedList<>();
        visited[start]=true;
        q.offer(new Node(start, ""));
        String result = "";
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int num = cur.num;
            String operations = cur.operations;

            if(num==end){
                result = operations;
                break;
            }
            int numD = D(num);
            if (!visited[numD]){
                visited[numD]=true;
                String nOperations = operations + "D";
                q.offer(new Node(numD, nOperations));
            }
            int numS = S(num);
            if (!visited[numS]){
                visited[numS]=true;
                String nOperations = operations + "S";
                q.offer(new Node(numS, nOperations));
            }
            int numL = L(num);
            if (!visited[numL]){
                visited[numL]=true;
                String nOperations = operations + "L";
                q.offer(new Node(numL, nOperations));
            }
            int numR = R(num);
            if (!visited[numR]){
                visited[numR]=true;
                String nOperations = operations + "R";
                q.offer(new Node(numR, nOperations));
            }
        }
        return result;
    }
    private static int[] intToArr(int input) {
        int[] arr = new int[4];
        if(input==0){
            return arr;
        }
        if (1 <= input) {
            arr[3] = input % 10;
        }
        if (10 <= input) {
            arr[2] = (input / 10) % 10;}
        if (100 <= input) {
            arr[1] = (input / 100) % 10 ;
        }
        if (1000 <= input) {
            arr[0] = (input / 1000) % 10;
        }
        return arr;
    }
    static int D(int input){
        int num = input * 2;
        return num % 10000;
    }

    static int S(int input){
        int num = input - 1;
        if (num<0) return 9999;
        return num;
    }
    static int L(int input){
        int[] numArr = intToArr(input);
        int[] returnArr = new int[4];
        for (int i = 1; i <=3 ; i++) {
            returnArr[i - 1] = numArr[i];
        }
        returnArr[3] = numArr[0];
        return 1000 * returnArr[0] + 100 * returnArr[1] + 10 * returnArr[2] + returnArr[3];
    }
    static int R(int input){
        int[] numArr = intToArr(input);
        int[] returnArr = new int[4];
        for (int i = 0; i <=2 ; i++) {
            returnArr[i + 1] = numArr[i];
        }
        returnArr[0] = numArr[3];
        return 1000 * returnArr[0] + 100 * returnArr[1] + 10 * returnArr[2] + returnArr[3];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            String result = bfs(start, end);
            bw.append(result + "\n");
        }
        bw.close();
        br.close();
    }
}
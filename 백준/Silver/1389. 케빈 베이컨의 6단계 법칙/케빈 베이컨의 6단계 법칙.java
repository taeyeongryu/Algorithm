import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, M;
    static class Node{
        int index;
        int distance;
        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }
    }
    static List<Integer>[] adjList;
    static int[][] distMap;
    static void bfs(int start){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < adjList[cur.index].size(); i++) {
                int next = adjList[cur.index].get(i);
                if (distMap[start][next] == 0 && start!=next) {
//                    System.out.println("start = " + start);
//                    System.out.println("cur.index = " + cur.index);
                    distMap[start][next] = cur.distance + 1;
                    queue.offer(new Node(next, cur.distance + 1));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        distMap = new int[N + 1][N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            adjList[start].add(end);
            adjList[end].add(start);
        }

        for (int i = 1; i < N + 1; i++) {
            //거리 검사
            bfs(i);

        }
//        for (int i = 1; i <distMap.length ; i++) {
//            for (int j = 1; j <distMap.length ; j++) {
//                System.out.print(distMap[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            int tmp = 0;
            for (int j = 1; j < N + 1; j++) {
                tmp += distMap[i][j];
            }
            if(min>tmp){
                index = i;
                min = tmp;
            }
        }
        bw.append(index+"");
        bw.close();
        br.close();

    }
}
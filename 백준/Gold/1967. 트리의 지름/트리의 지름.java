import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Node {
        int index;
        int dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", dist=" + dist +
                    '}';
        }
    }

    static int N;
    static List<Node>[] adjList;
    static List<Integer> leafNodeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());


        adjList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }


        StringTokenizer st = null;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[parent].add(new Node(child, weight));
            adjList[child].add(new Node(parent, weight));
        }
        findLeafNode();
        for (Integer i : leafNodeList) {
            visited[i]=true;
            dfs(i, 0);
        }
        bw.append(max + "");
        bw.close();
        br.close();
    }

    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    //방문체크는 메서드 시작하기 전에 해야한다.
    //index는 여기서부터 탐색을 시작한다는 의미이다.
    private static void dfs(int index, int length){
        max = Math.max(length, max);
        for (int i = 0; i <adjList[index].size() ; i++) {
            Node nextNode = adjList[index].get(i);
            int nextIndex = nextNode.index;
            int nextDist = nextNode.dist;
            if (!visited[nextIndex]) {
                visited[nextIndex] = true;
                dfs(nextIndex,length+nextDist);
                visited[nextIndex] = false;
            }
        }
    }

    private static void findLeafNode() {
        int rootNode = 1;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[rootNode] = true;
        q.offer(rootNode);

        while (!q.isEmpty()) {
            boolean flag = false;
            int cur = q.poll();

            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i).index;
                if(!visited[next]){
                    flag = true;
                    visited[next]=true;
                    q.offer(next);
                }
            }
            if (!flag){
                leafNodeList.add(cur);
            }
        }
    }
}
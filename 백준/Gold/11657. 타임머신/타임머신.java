import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int index;
        int weight;

        public Node(int index,int weight){
            this.index = index;
            this.weight = weight;
        }
    }
    static long[] dist;
    static int N,M;
    static long MAX = Long.MAX_VALUE;
    static List<Node>[] adjList;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        Arrays.fill(dist,MAX);
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
        }
//        System.out.println("adjList[1].size() = " + adjList[1].size());
        boolean flag = bellanFold(1);
        if (flag) {
            for (int i = 2; i <= N; i++) {
                bw.append(dist[i] == MAX ? "-1\n" : dist[i] + "\n");
            }
        } else {
            bw.append("-1");
        }

        bw.close();
        br.close();
    }
    static boolean bellanFold(int startIndex){
        dist[startIndex]=0;
        //N번 반복
        for (int i = 0; i < N; i++) {
            //N개의 노드의 간선을 모두 확인
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < adjList[j].size(); k++) {
                    Node nextNode = adjList[j].get(k);
                    //업데이트
                    if (dist[j] != Long.MAX_VALUE && dist[nextNode.index] > dist[j] + nextNode.weight) {
                        
                        dist[nextNode.index] = dist[j] + nextNode.weight;
                        if (i == N - 1) {
                            //잘못된것
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
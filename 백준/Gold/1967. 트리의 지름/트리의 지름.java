import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Node{
        int index;
        int weight;
        public Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
        @Override
        public String toString(){
            return "index : " + index + ", weight : " + weight;
        }
    }
    static Node[] parent;
    static List<Node>[] child;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        parent = new Node[N + 1];
        child = new ArrayList[N + 1];
        for (int i = 0; i < child.length; i++) {
            child[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            child[p].add(new Node(c, w));
            parent[c] = new Node(p, w);
        }
        leafNodeList = new ArrayList<>();
        for (int i = 1; i < child.length; i++) {
            if(child[i].size()==0){
                leafNodeList.add(i);
            }
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < leafNodeList.size(); i++) {
            int curLeaf = leafNodeList.get(i);
            visited = new boolean[N + 1];
            visited[curLeaf]=true;
            dfs(curLeaf, 0);
        }
        bw.append(max + "");

        bw.close();
        br.close();
    }

    static List<Integer> leafNodeList;
    static boolean[] visited;
    static int max;
    static void dfs(int start,int dist){

        Node parentNode = parent[start];
        if (parentNode != null && !visited[parentNode.index]) {
            visited[parentNode.index] = true;
            dfs(parentNode.index, dist + parentNode.weight);
        }
        //자식 있으면
        if(child[start].size()!=0){
            for (int i = 0; i < child[start].size(); i++) {
                Node nextChild = child[start].get(i);
                if(!visited[nextChild.index]){
                    visited[nextChild.index]=true;
                    dfs(nextChild.index, dist + nextChild.weight);
                }
                else{
                    max = Math.max(max, dist);
                }
            }
        }else{
            max = Math.max(max, dist);
        }
    }
}
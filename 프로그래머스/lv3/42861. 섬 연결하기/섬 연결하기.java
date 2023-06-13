import java.util.*;
class Solution {
    static class Node implements Comparable<Node>{
        int idx;
        int weight;
        
        Node(int idx, int weight){
            this.idx=idx;
            this.weight=weight;
        }
        
        @Override
        public int compareTo(Node n){
            return this.weight-n.weight;
        }
        @Override
        public String toString(){
            return "idx : "+idx+", weight : "+weight;
        }
    }
    static boolean[] choiced;
    static ArrayList<Node>[] adjlist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int min=0;
    static void prim(){
        pq.offer(new Node(0,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(choiced[cur.idx]){
                continue;
            }
            choiced[cur.idx]=true;
            min+=cur.weight;
            for(int i = 0; i<adjlist[cur.idx].size(); i++){
                Node next = adjlist[cur.idx].get(i);
                if(!choiced[next.idx]){
                    pq.offer(next);
                }
            }
        }
    }
    public int solution(int n, int[][] costs) {
        adjlist = new ArrayList[n];
        choiced = new boolean[n];
        for(int i = 0; i<n; i++){
            adjlist[i]=new ArrayList<>();
        }
        for(int i = 0; i<costs.length; i++){
            int st = costs[i][0];
            int ed = costs[i][1];
            int we = costs[i][2];
            adjlist[st].add(new Node(ed,we));
            adjlist[ed].add(new Node(st,we));
        }
        // System.out.println(Arrays.toString(adjlist));
        prim();
        int answer = min;
        return answer;
    }
}
import java.util.*;
class Solution {
    class Node implements Comparable<Node>{
        int idx;
        int weight;
        Node(int idx, int weight){
            this.idx=idx;
            this.weight=weight;
        }
        @Override
        public int compareTo(Node o){
            return this.weight-o.weight;
        }
    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] dist = new int[n+1];
        ArrayList<Integer>[] adjlist = new ArrayList[n+1];
        for(int i = 0; i<adjlist.length; i++){
            adjlist[i]=new ArrayList<>();
        }
        for(int i = 0; i < roads.length; i++){
            int start = roads[i][0];
            int end = roads[i][1];
            adjlist[start].add(end);
            adjlist[end].add(start);
        }
        int INF = Integer.MAX_VALUE;
        PriorityQueue<Node> pq=  new PriorityQueue<>();
        Arrays.fill(dist,INF);
        dist[destination]=0;
        pq.offer(new Node(destination,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.weight>dist[cur.idx]){
                continue;
            }
            for(int i = 0; i<adjlist[cur.idx].size(); i++){
                int next = adjlist[cur.idx].get(i);
                if(dist[next]>cur.weight+1){
                    dist[next]=cur.weight+1;
                    pq.offer(new Node(next,dist[next]));
                }
            }
        }
        // System.out.println(Arrays.toString(dist));
        int[] result = new int[sources.length];
        for(int i = 0; i<sources.length; i++){
            result[i]=dist[sources[i]]==INF?-1:dist[sources[i]];
        }
        int[] answer = result;
        return answer;
    }
}
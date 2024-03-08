import java.util.*;
class Solution {
    class Node implements Comparable<Node>{
        int index;
        int weight;
        public Node(int index, int weight){
            this.index=index;
            this.weight=weight;
        }
        @Override
        public int compareTo(Node o1){
            return Integer.compare(this.weight, o1.weight);
        }
        @Override
        public String toString(){
            return "index : "+index+", weight : "+weight;
        }
    }
    public void dijkstra(){
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.offer(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curIndex = cur.index;
            int curWeight = cur.weight;
            if(dist[curIndex]<curWeight){
                continue;
            }
            dist[curIndex]=curWeight;
            
            for(int i = 0; i<adjList[curIndex].size(); i++){
                Node nextNode = adjList[curIndex].get(i);
                int nextIndex = nextNode.index;
                int nextWeight = nextNode.weight;
                if(curWeight+nextWeight<=dist[nextIndex]){
                    dist[nextIndex]=curWeight+nextWeight;
                    pq.offer(new Node(nextIndex,dist[nextIndex]));
                }
            }
        }
    }
    List<Node>[] adjList;
    int[] dist;
    public int solution(int N, int[][] road, int K) {
        dist = new int[N+1];
        adjList = new ArrayList[N+1];
        for(int i = 0; i<N+1; i++){
            adjList[i]=new ArrayList<>();
        }
        int[][] weight = new int[N+1][N+1];
        for(int i = 0; i<N+1; i++){
            Arrays.fill(weight[i],Integer.MAX_VALUE);
        }
        for(int i = 0; i<road.length; i++){
            if(weight[road[i][0]][road[i][1]]>road[i][2]){
                weight[road[i][0]][road[i][1]]=road[i][2];
                weight[road[i][1]][road[i][0]]=road[i][2];
            }
        }
        
        
        for(int i = 1; i<N+1; i++){
            for(int j = 1; j<N+1; j++){
                if(weight[i][j]!=Integer.MAX_VALUE){
                    adjList[i].add(new Node(j,weight[i][j]));
                }
            }
        }
        dijkstra();
        
        int answer = 0;        
        for(int i = 1; i<N+1; i++){
            if(dist[i]<=K){
                answer++;
            }
        }
        return answer;
    }
}
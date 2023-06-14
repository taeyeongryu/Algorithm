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
            return Integer.compare(this.weight,n.weight);
        }
        @Override
        public String toString(){
            return "idx : "+idx+", weight : "+weight;
        }
    }
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Node>[] adjlist;
    static int[] price;
    static void dji(int start){
        price[start]=0;
        pq.offer(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.weight>price[cur.idx]){
                continue;
            }
            for(int i = 0; i<adjlist[cur.idx].size(); i++){
                Node next = adjlist[cur.idx].get(i);
                int n_idx = next.idx;
                int n_weight = next.weight;
                
                if(price[n_idx]>price[cur.idx]+n_weight){
                    price[n_idx]=price[cur.idx]+n_weight;
                    pq.offer(new Node(n_idx,price[n_idx]));
                }
            }
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int min = Integer.MAX_VALUE;
        adjlist = new ArrayList[n+1];
        price = new int[n+1];
        Arrays.fill(price,Integer.MAX_VALUE);
        for(int i = 0; i<adjlist.length; i++){
            adjlist[i]=new ArrayList<>();
        }
        for(int i = 0; i<fares.length; i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int weight = fares[i][2];
            adjlist[start].add(new Node(end,weight));
            adjlist[end].add(new Node(start,weight));
        }
        //출발 위치에서 다익스트라 진행
        dji(s);
        int[] startprice = new int[n+1];
        for(int i = 1; i<startprice.length; i++){
            startprice[i]=price[i];
        }
        for(int i = 1; i <= n; i++){
            if(min<startprice[i]||startprice[i]==Integer.MAX_VALUE){
                continue;
            }
            Arrays.fill(price,Integer.MAX_VALUE);
            dji(i);
            if(startprice[i]+price[a]+price[b]<min&&price[a]!=Integer.MAX_VALUE&&price[b]!=Integer.MAX_VALUE){
                min=startprice[i]+price[a]+price[b];
            }
        }
        
        System.out.println(min);
        int answer = min;
        return answer;
    }
}
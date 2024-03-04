import java.util.*;
class Solution {
    List<Integer>[] adjList;
    boolean[] visited;
    int min;
    public void bfs(int n, int removeIndex, int[][] wires){
        Queue<Integer> q = new LinkedList<>();
        int count = 1;
        visited = new boolean[n+1];
        visited[1]=true;
        q.offer(1);
        int v1 = wires[removeIndex][0];
        int v2 = wires[removeIndex][1];
        while(!q.isEmpty()){
            int curNode = q.poll();
            for(int i = 0; i < adjList[curNode].size(); i++){
                int nextNode = adjList[curNode].get(i);
                
                if(visited[nextNode]){
                    continue;
                }
                
                if((curNode==v1&&nextNode==v2) || (curNode==v2&&nextNode==v1)){
                    continue;
                }
                
                visited[nextNode]=true;
                count++;
                q.offer(nextNode);
            }
        }
        
        min = Math.min(Math.abs(n-2*count),min);
    }
    public int solution(int n, int[][] wires) {
        min = Integer.MAX_VALUE;
        
        adjList = new ArrayList[n+1];
        for(int i = 0; i<adjList.length; i++){
            adjList[i]=new ArrayList<>();
        }
        
        
        for(int i = 0; i<wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }
        for(int i = 0; i<wires.length; i++){
            bfs(n,i,wires);
        }
        
        
        int answer = min;
        return answer;
    }
}
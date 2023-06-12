import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] adjlist=new ArrayList[n+1];
        for(int i = 0; i<adjlist.length; i++){
            adjlist[i]=new ArrayList<>();
        }
        for(int i = 0; i<edge.length; i++){
            adjlist[edge[i][0]].add(edge[i][1]);
            adjlist[edge[i][1]].add(edge[i][0]);
        }
        int visited[] = new int[n+1];
        visited[1]=1;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<adjlist[1].size(); i++){
            visited[adjlist[1].get(i)]=2;
            q.offer(adjlist[1].get(i));
        }
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0; i<adjlist[cur].size(); i++){
                int next = adjlist[cur].get(i);
                if(visited[next]>0){
                    continue;
                }
                visited[next]=visited[cur]+1;
                q.offer(next);
            }
        }
        System.out.println(Arrays.toString(visited));
        int max = 0;
        int max_count = 0;
        for(int i = 1; i<visited.length; i++){
            if(visited[i]>max){
                max=visited[i];
                max_count = 1;
            }
            else if(visited[i]==max){
                max_count++;
            }
            else{
                continue;
            }
        }
        int answer = max_count;
        return answer;
    }
}
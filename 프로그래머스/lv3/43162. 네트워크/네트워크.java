import java.util.*;
class Solution {
    static boolean[] visited;
    static int count = 0;
    static Queue<Integer> q = new LinkedList<>();
    static void bfs(int[][] map){
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i = 0; i < map.length ; i++){
                if(map[cur][i]==1){
                    if(!visited[i]){
                        visited[i]=true;
                        q.offer(i);
                    }
                }
            }
        }
    }
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                visited[i]=true;
                q.offer(i);
                bfs(computers);
            }
        }
        int answer = count;
        return answer;
    }
}
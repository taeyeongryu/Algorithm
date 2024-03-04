import java.util.*;

class Solution {
    class Node{
        int r;
        int c;
        
        public Node(int r, int c){
            this.r = r;
            this.c = c;
            
        }
    }
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    boolean[][] visited;
    public void bfs(String[] maps){
        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i<maps.length; i++){
            for(int j = 0; j<maps[i].length(); j++){
                if(maps[i].charAt(j)!='X'){
                    
                    int curNum = maps[i].charAt(j)-'0';
                    
                    if(!visited[i][j]){
                        visited[i][j]=true;
                        q.offer(new Node(i,j));
                        
                        while(!q.isEmpty()){
                            Node curNode = q.poll();
                            int curR = curNode.r;
                            int curC = curNode.c;
                            
                            for(int k = 0; k<4; k++){
                                int nextR = curR+dr[k];
                                int nextC = curC+dc[k];
                                if(nextR<0||nextR>=maps.length||nextC<0||nextC>=maps[0].length()){
                                    continue;
                                }
                                if(!visited[nextR][nextC]&&maps[nextR].charAt(nextC)!='X'){
                                    visited[nextR][nextC]=true;
                                    curNum+=maps[nextR].charAt(nextC)-'0';
                                    q.offer(new Node(nextR,nextC));
                                }
                            }
                        }
                        pq.offer(curNum);
                    }
                }
            }
        }   
    }
    public int[] solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        bfs(maps);
        if(pq.size()==0){
            return new int[]{-1};
        }
        
        int[] answer = new int[pq.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i]=pq.poll();
        }
        return answer;
    }
}
import java.util.*;
class Solution {
    class Node{
        int r;
        int c;
        int count;
        
        public Node(int r, int c, int count){
            this.r = r;
            this.c = c;
            this.count = count;
        
        }
        @Override
        public String toString(){
            return "r : "+r+", c : "+c+", count : "+count;
        }
    }
    char[][] map;
    boolean[][] visited;
    int R;
    int C;
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};
    int bfs(int[] start){
        
        Queue<Node> q = new LinkedList<>();
        
        visited[start[0]][start[1]]=true;
        q.offer(new Node(start[0],start[1],0));
        
        outer : while(!q.isEmpty()){
            Node cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            int curCount = cur.count;
            
            for(int i =0; i<4; i++){
                int nextR = curR+dr[i];
                int nextC = curC+dc[i];
                
                if(0 > nextR || nextR >= R || nextC < 0 || nextC >= C){
                    continue;
                }
                if(map[nextR][nextC]=='X'){
                    continue;
                }
                if(visited[nextR][nextC]){
                    continue;
                }
                
                //방문한적 없는 곳들
                
                //출구 열자
                if(map[nextR][nextC]=='L'){
                    visited = new boolean[R][C];
                    q.clear();
                    visited[nextR][nextC]=true;
                    q.offer(new Node(nextR, nextC, curCount+1));
                    break outer;
                }
                //출구가 열려있다!!
                else{
                    visited[nextR][nextC]=true;
                    q.offer(new Node(nextR, nextC, curCount+1));
                }
            }
        }
        while(!q.isEmpty()){
            Node cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            int curCount = cur.count;
            if(map[curR][curC]=='E'){
                return curCount;
            }
            for(int i =0; i<4; i++){
                int nextR = curR+dr[i];
                int nextC = curC+dc[i];
                if(0 > nextR || nextR >= R || nextC < 0 || nextC >= C){
                    continue;
                }
                if(map[nextR][nextC]=='X'){
                    continue;
                }
                if(visited[nextR][nextC]){
                    continue;
                }
                visited[nextR][nextC]=true;
                q.offer(new Node(nextR, nextC, curCount+1));
            }
        }
        return -1;
    }
    
    
    public int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        map = new char[R][C];
        visited = new boolean[R][C];
        int[] start = new int[2];
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                map[i][j]=maps[i].charAt(j);
                if(map[i][j]=='S'){
                    
                    start[0]=i;
                    start[1]=j;
                    
                }
            }
        }
        
        int answer = bfs(start);
        return answer;
    }
}
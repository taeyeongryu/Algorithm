import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    int N;
    int M;
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};
    boolean[][] visited;
    int[][] map;
    
    class Point{
        int r;
        int c;
        int count;
        Point(int r, int c, int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
    
    boolean isBounded(int r, int c){
        if(0<=r && r<N && 0 <= c && c < M) return true;
        return false;
    }
    void bfs(){
        Queue<Point> q = new LinkedList<>();
        visited[0][0]=true;
        q.offer(new Point(0,0,1));
        while(!q.isEmpty()){
            
            Point cur= q.poll();
            int r = cur.r;
            int c = cur.c;
            // System.out.println("r : " + r + ", c : " + c);
            int count = cur.count;
            if(r==N-1&&c==M-1){
                min = count;
                break;
            }
            for(int i = 0; i < 4; i++){
                int nr = r+dr[i];
                int nc = c+dc[i];
                int nextCount = count+1;
                if(!isBounded(nr,nc)){
                    continue;
                }
                if(visited[nr][nc]){
                    continue;
                }
                if(map[nr][nc]==1){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc,nextCount));    
                } 
            }
        }
    }
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j]=maps[i][j];     
            }
        }
        bfs();
        
        int answer = (min == Integer.MAX_VALUE ? -1 : min);
        return answer;
    }
}
import java.util.*;

class Solution {
    
    class Node{
        int r;
        int c;
        int count;
        public Node(int r, int c,int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }
        @Override
        public String toString(){
            return "r : "+r+", c : "+c+", count : "+count;
        }
    }
    int[] dr={1,-1,0,0};
    int[] dc={0,0,1,-1};
    char[][] map;
    boolean[][] visited;
    public boolean rangeCheck(int r, int c){
        return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
    }
    public int[] nextLocation(int curR, int curC, int dir){
        while(true){
            curR+=dr[dir];
            curC+=dc[dir];
            if(!rangeCheck(curR,curC)){
                break;
            }
            if(map[curR][curC]=='D'){
                break;
            }
        }
        curR-=dr[dir];
        curC-=dc[dir];
        return new int[]{curR,curC};
    }
    public int bfs(int startR, int startC){
        Queue<Node> q = new LinkedList<>();
        visited[startR][startC]=true;
        q.offer(new Node(startR,startC,0));
        while(!q.isEmpty()){
            // System.out.println(q);
            Node curNode = q.poll();
            
            int curR = curNode.r;
            int curC = curNode.c;
            int curCount = curNode.count;
            
            for(int dir = 0; dir < 4; dir++){
                int[] next = nextLocation(curR,curC,dir);
                
                int nextR = next[0];
                int nextC = next[1];
                
                if(visited[nextR][nextC]){
                    continue;
                }
                if(map[nextR][nextC]=='G'){
                    return curCount+1;
                }
                visited[nextR][nextC]=true;
                q.offer(new Node(nextR, nextC, curCount+1));
            }
        }
        return -1;
    }
    public int solution(String[] board) {
        visited=new boolean[board.length][board[0].length()];
        map =new char[board.length][board[0].length()];
        int startR = 0;
        int startC = 0;
        for(int i = 0; i<board.length; i++){
            for(int j =0; j<board[0].length(); j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j]=='R'){
                    startR=i;
                    startC=j;
                }
            }
        }
        int answer = bfs(startR, startC);
        return answer;
    }
}
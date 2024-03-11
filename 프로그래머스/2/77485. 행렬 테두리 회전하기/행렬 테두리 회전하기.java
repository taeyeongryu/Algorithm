import java.util.*;

class Solution {
    //시계반대방향으로
    int[] dr = {1,0,-1,0};
    int[] dc = {0,1,0,-1};
    
    int[][] map;
    PriorityQueue<Integer> pq;
    public int rotate(int r1, int c1, int r2,int c2){
        pq = new PriorityQueue<>();
        int startNum = map[r1][c1];
        int times = 2*(r2-r1+c2-c1);
        int dir = 0;
        int curR = r1;
        int curC = c1;
        
        while(times!=0){
            pq.offer(map[curR][curC]);
            // System.out.println(map[curR][curC]);
            
            //다음 인덱스
            int nextR = curR+dr[dir];
            int nextC = curC+dc[dir];
            
            //범위 체크
            if(!(r1<=nextR && nextR<=r2 && c1<=nextC && nextC<=c2)){
                dir+=1;
                dir%=4;
                nextR = curR+dr[dir];
                nextC = curC+dc[dir];
            }
            if(times==1){
                map[curR][curC]=startNum;
            }else{
                map[curR][curC]=map[nextR][nextC];    
            }
            curR = nextR;
            curC = nextC;
            times--;
        }
        
        return pq.poll();
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows+1][columns+1];
        int number = 1;
        for(int i = 1; i<=rows; i++){
            for(int j = 1; j<=columns; j++){
                map[i][j]=number++;
            }
        }
        int[] answer = new int[queries.length];
        for(int i = 0; i<queries.length; i++){
           answer[i] = rotate(queries[i][0],queries[i][1],queries[i][2],queries[i][3]);
        }
        return answer;
    }
}
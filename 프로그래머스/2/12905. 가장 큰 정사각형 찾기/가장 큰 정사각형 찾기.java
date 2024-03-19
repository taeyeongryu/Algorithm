import java.util.*;

class Solution{

    public int solution(int [][]board){
        int[][] map = new int[board.length][board[0].length];
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[0].length; j++){
                if(i-1<0||j-1<0){
                    map[i][j]=board[i][j];
                }
                else{
                    if(board[i][j]==0){
                        map[i][j]=0;
                    }else{
                        int min = map[i-1][j-1];
                        min= Math.min(min, map[i][j-1]);
                        min= Math.min(min, map[i-1][j]);
                        map[i][j]=min+1;
                    }
                }
            }
        }
        int answer = 0;
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[0].length; j++){
                answer = Math.max(answer, map[i][j]);
                // System.out.print(map[i][j]+" ");
                
            }
            // System.out.println();
        }
        return answer*answer;
    }
}
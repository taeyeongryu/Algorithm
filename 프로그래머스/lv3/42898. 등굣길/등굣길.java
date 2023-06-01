import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        map[0][0]=1;
        for(int i = 0; i < puddles.length; i++){
            int c = puddles[i][0]-1;
            int r = puddles[i][1]-1;
            map[r][c]=-1;
        }
        // System.out.println(Arrays.deepToString(map));
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j]!=-1){
                    if(0<=i-1&&map[i-1][j]!=-1){
                        map[i][j]+=map[i-1][j];
                        map[i][j]%=1000000007;
                    }
                    if(0<=j-1&&map[i][j-1]!=-1){
                        map[i][j]+=map[i][j-1];
                        map[i][j]%=1000000007;
                    }
                }
            }
        }
        
        int answer = map[n-1][m-1];
        return answer;
    }
}
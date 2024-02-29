class Solution {
    int[] dr = {1,0,-1};
    int[] dc = {0,1,-1};
    public int[] solution(int n) {
        int[][] map = new int[n][n];
        int times = n;
        int dir = 0;
        int curR = 0;
        int curC = 0;
        int insertNum=1;
        while(times!=0){
            for(int i = 0; i<times; i++){
                map[curR][curC]=insertNum++;
                if(i!=times-1){
                    curR+=dr[dir];
                    curC+=dc[dir];
                }
            }
            dir++;
            dir%=3;
            curR+=dr[dir];
            curC+=dc[dir];
            times--;
        }
        // for(int i = 0; i<map.length; i++){
        //     for(int j = 0; j<map.length; j++){
        //         System.out.print(map[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum+=i;
        }
        int[] result = new int[sum];
        int insertIndex = 0;
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map.length; j++){
                if(map[i][j]!=0){
                    result[insertIndex++]=map[i][j];
                }
            }
        }
        return result;
    }
}
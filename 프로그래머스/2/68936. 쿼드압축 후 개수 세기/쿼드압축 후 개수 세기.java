class Solution {
    int zeroCount = 0;
    int oneCount = 0;
    int[][] staticArr;
    public boolean checkOneNumber(int r, int c, int length){
        //모두 checkNum과 같은지 확인해야 한다.
        int checkNum = staticArr[r][c];
        for(int i = 0; i<length; i++){
            for(int j = 0; j<length; j++){
                if(staticArr[r+i][c+j]!=checkNum)return false;
            }
        }
        return true;
    }
    public void dfs(int r, int c, int length){
        if(length==1){
            if(staticArr[r][c]==1){
                oneCount++;
            }else{
                zeroCount++;
            }
            return;
        }
        if(checkOneNumber(r,c,length)){
            if(staticArr[r][c]==1){
                oneCount++;
            }else{
                zeroCount++;
            }
            return;
        }else{
            dfs(r,c,length/2);
            dfs(r+length/2,c,length/2);
            dfs(r,c+length/2,length/2);
            dfs(r+length/2,c+length/2,length/2);
        }
    }
    public int[] solution(int[][] arr) {
        staticArr = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                staticArr[i][j]=arr[i][j];
            }
        }
        dfs(0,0,arr.length);
        int[] answer =new int[2];
        answer[0]=zeroCount;
        answer[1]=oneCount;
        return answer;
    }
}
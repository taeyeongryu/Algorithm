class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int rowlength = arr1.length;
        int collength = arr2[0].length;
        int crolength = arr2.length;
        int[][] answer =new int[rowlength][collength];
        
        for(int i = 0; i < answer.length; i++){
            for(int j = 0; j < answer[0].length; j++){
                for(int k = 0; k<crolength; k++){
                    answer[i][j]+=arr1[i][k]*arr2[k][j];
                }
            }
        }
        return answer;
    }
}
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] sumboard = new int[N+1][M+1];
        for(int i = 0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = type==1?-skill[i][5]:skill[i][5];
            sumboard[r1][c1]+=degree;
            sumboard[r1][c2+1]+=-degree;    
            sumboard[r2+1][c1]+=-degree;    
            sumboard[r2+1][c2+1]+=degree;    
        }
        for(int i=0; i<sumboard.length;i++){
            for(int j=1;j<sumboard[0].length;j++){
                sumboard[i][j]+=sumboard[i][j-1];
            }
        }
        for(int j=0; j<sumboard[0].length;j++){
            for(int i=1;i<sumboard.length;i++){
                sumboard[i][j]+=sumboard[i-1][j];
            }
        }
        int answer = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j]+=sumboard[i][j];
                if(board[i][j]>0){
                    answer++;
                }
            }
        }
        return answer;
    }
}
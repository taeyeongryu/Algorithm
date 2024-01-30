import java.util.*;
class Solution {
    
    List<int[]> indexList = new ArrayList<>();
    
    void bomb(){
        //먼저 List초기화를 한다.
        indexList.clear();
        for(int i = 0; i < M-1; i++){
            for(int j = 0; j < N-1; j++){
                if(map[i][j]=='.'){
                    continue;
                }
                //여기서 i,j부터 시작하는 사각형을 검사한다.
                if(map[i][j]==map[i+1][j]&&map[i][j]==map[i][j+1]&&map[i][j]==map[i+1][j+1]){
                    indexList.add(new int[]{i,j});
                    indexList.add(new int[]{i+1,j});
                    indexList.add(new int[]{i,j+1});
                    indexList.add(new int[]{i+1,j+1});
                }
            }
        }
        
        for(int i = 0; i < indexList.size(); i++){
            int[] tmp = indexList.get(i);
            map[tmp[0]][tmp[1]]='.';
        }
        boolean isFall = fall();
        
        if(isFall){
            bomb();
        }
    }
    boolean fall(){
        
        boolean isFall = false;
        for(int j = 0; j < N; j++){
            for(int i = M-1; i >= 0; i--){
                //블록이 있는 곳이 아니면 pass~~
                if(map[i][j]=='.'){continue;}
                //i,j 밑에 빈칸이 있는지 check하는 변수
                
                int downCount = 1;
                boolean flag = false;
                while(true){
                    
                    if(!isRange(i+downCount,j)){
                        break;
                    }
                    if(map[i+downCount][j]=='.'){
                        
                        flag=true;
                        downCount++;
                    }else{
                        break;
                    }
                }
                if(flag){
                    isFall=true;
                    map[i+downCount-1][j]=map[i][j];
                    map[i][j]='.';
                    
                }
            }
        }
        return isFall;
    }
    void print(){
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    boolean isRange(int i, int j){
        return 0<=i && i<M && 0<=j && j<N;
    }
    int bombCheck(){
        int cnt=0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]=='.'){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    char[][] map;
    int N,M;
    
    public int solution(int m, int n, String[] board) {
        N = n;
        M = m;
        map = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        bomb();
        int answer = bombCheck();
        
        return answer;
    }
}
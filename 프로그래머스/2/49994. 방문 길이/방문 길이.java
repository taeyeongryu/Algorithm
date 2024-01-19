class Solution {
    
    //D, U, R, L
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    boolean[][] horizontal = new boolean[12][11];
    boolean[][] vertical = new boolean[11][12];
    
    public int solution(String dirs) {
        int r = 6;
        int c = 6;
        for(int i = 0; i<dirs.length(); i++){
            char operation = dirs.charAt(i);
            int nr = 0;
            int nc = 0;
            switch(operation){
                case 'D':
                    nr = r+dr[0];
                    nc = c+dc[0];
                    break;
                case 'U':
                    nr = r+dr[1];
                    nc = c+dc[1];
                    break;
                case 'R':
                    nr = r+dr[2];
                    nc = c+dc[2];
                    break;
                case 'L':
                    nr = r+dr[3];
                    nc = c+dc[3];
                    break;
            }
            if(!isRange(nr,nc)){
                continue;
            }
            //여기까지 오면 그 다음 곳으로 가는 것이다잉
            checkVisited(r,c,nr,nc);
            //좌표 이동
            r=nr;
            c=nc;
        } 
        return pathCheck();
    }
    
    boolean isRange(int r, int c){
        return 1 <= r && r <= 11 && 1 <= c && c <= 11;
    }
    int pathCheck(){
        int count=0;
        for(int i = 0; i<horizontal.length; i++){
            for(int j = 0; j<horizontal[0].length; j++){
                if(horizontal[i][j]){
                    count++;
                }
            }
        }
        for(int i = 0; i<vertical.length; i++){
            for(int j = 0; j<vertical[0].length; j++){
                if(vertical[i][j]){
                    count++;
                }
            }
        }
        return count;
    }
    void checkVisited(int r, int c, int nr, int nc){
        int chaR = nr-r;
        int chaC = nc-c;
        //세로로 움직인 경우
        if(chaR==0){
            if(chaC>0){
                //오른쪽으로 움직임   
                horizontal[r][c] = true;
            }else{
                //왼쪽으로 움직임
                horizontal[r][nc] = true;;
            }
        }
        //가로로 움직인 경우
        else{
            if(chaR>0){
                //아래로 움직임   
                vertical[r][c] = true;
            }else{
                //위로 움직임
                vertical[nr][c] = true;
            }
        }
    }
}
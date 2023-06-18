import java.util.*;
class Solution {
    int M,N,count;
    int[][] key1;
    int[][] key2;
    int[][] key3;
    int[][] key4;
    int[][] map;
    int[][] rotate(int[][] key){
        int[][] tmp = new int[key.length][key.length];
        for(int i = 0; i < tmp.length; i++){
            for(int j = 0; j<tmp.length; j++){
                tmp[i][j]=key[tmp.length-1-j][i];
            }
        }
        return tmp;
    }
    boolean isPerfect(int type, int r, int c){
        int co = 0;
        int[][] curkey = new int[M][M];
        switch(type){
            case 1:
                curkey=key1;
                break;
            case 2:
                curkey=key2;
                break;
            case 3:
                curkey=key3;
                break;
            case 4:
                curkey=key4;
                break;
        }
        boolean flag = true;
        outer : for(int i = r; i<r+M; i++){
            for(int j = c; j<c+M; j++){
                // System.out.println("i : "+i+", j : "+j);
                if(i<M||j<M||i>=M+N||j>=M+N){
                    continue;
                }
                if(curkey[i-r][j-c]==map[i][j]){
                    flag = false;
                    break outer;
                }
                else if(map[i][j]==0){
                    co++;
                }
            }
        }
        if(co!=count){
            flag=false;
        }
        return flag;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        map =new int[2*M+N][2*M+N];
        for(int i = M; i<M+N; i++){
            for(int j = M; j< M+N; j++){
                map[i][j]=lock[i-M][j-M];
                if(map[i][j]==0){
                    count++;
                }
            }
        }
        // System.out.println(Arrays.deepToString(map));
        // System.out.println(Arrays.deepToString(key2));
        // System.out.println(Arrays.deepToString(key3));
        // System.out.println(Arrays.deepToString(key4));
        key2 = rotate(key);
        key3 = rotate(key2);
        key4 = rotate(key3);
        key1 = rotate(key4);
        boolean flag = false;
        outer : for(int i=1; i<=M+N-1;i++){
            for(int j=1; j<=M+N-1;j++){
                for(int k = 1; k<=4; k++){
                    if(isPerfect(k,i,j)){
                        flag=true;
                        break outer;
                    }
                }
            }
        }
        
        boolean answer = flag;
        return answer;
    }
}
import java.util.*;
class Solution {
    boolean[] check = new boolean[1000001];
    int bfs(int x, int y, int n){
        Queue<int[]> q = new LinkedList<>();
        check[x]=true;
        q.offer(new int[]{0,x});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curTime = cur[0];
            int curNumber = cur[1];
            
            if(curNumber==y){
                return curTime;
            }
            int nextNumber = curNumber+n;
            if(isRange(y,nextNumber)&&!check[nextNumber]){
                check[nextNumber] = true;
                q.offer(new int[]{curTime+1,nextNumber});
            }
            nextNumber = curNumber*2;
            if(isRange(y,nextNumber)&&!check[nextNumber]){
                check[nextNumber] = true;
                q.offer(new int[]{curTime+1,nextNumber});
            }
            nextNumber = curNumber*3;
            if(isRange(y,nextNumber)&&!check[nextNumber]){
                check[nextNumber] = true;
                q.offer(new int[]{curTime+1,nextNumber});
            }      
        }
        return -1;
    }
    boolean isRange(int y, int num){
        return 1<=num&&num<=y;
    }
    public int solution(int x, int y, int n) {
    
        int answer = bfs(x,y,n);;
        return answer;
    }
}
import java.util.*;
class Solution {
    static int[][] map;
    // static ArrayList<Integer>[] winarr; 
    // static ArrayList<Integer>[] losearr;
    static void put(int win, int lose){
        // if(map[win][lose]==1){
        //     return;
        // }
        // System.out.println("win : "+win+", lose : "+lose);
        map[win][lose]=1;
        map[lose][win]=-1;
        for(int i = 1; i<map.length;i++){
            if(map[lose][i]>0&&map[win][i]==0){
                put(win,i);    
            }
        }
        for(int i = 1; i<map.length;i++){
             if(map[win][i]<0&&map[i][lose]==0){
                put(i,lose);    
            }
        }
    }
    public int solution(int n, int[][] results) {
        map = new int[n+1][n+1];
//         winarr = new ArrayList<Integer>[n+1];
//         losearr = new ArrayList<Integer>[n+1];
        
//         for(int i = 0; i<n+1; i++){
//             winarr[i]=new ArrayList<>();
//             losearr[i]=new ArrayList<>();
//         }
        
//         for(int i = 0; i<results.length; i++){
//             int win = results[i][0];
//             int lose = results[i][1];
//             winarr[win].add(lose);
//             losearr[lose].add(win);
//         }
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i<results.length; i++){
            
            int win = results[i][0];
            int lose = results[i][1];
            q.offer(new int[]{win,lose});
            map[win][lose]=1;
            map[lose][win]=-1;
        }
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            put(tmp[0],tmp[1]);
        }
        // for(int i = 1; i<n+1;i++){
        //     for(int j = 1; j<n+1;j++){
        //         System.out.print(map[i][j]);
        //     }    
        //      System.out.println();
        // }
        int answer = 0;
        for(int i = 1; i <= n; i++){
            int cnt = 0; 
            for(int j = 1; j <= n; j++){
                if(map[i][j] != 0) cnt++;
            }
            if(cnt == n-1) answer++;
        }
        return answer;
    }
}
import java.util.*;
class Solution {
    int N = 0;
    int[] seq;
    int[] sum;
    int min = Integer.MAX_VALUE;
    int[] mineralsInt;
    int[][] map = {{1,1,1},
                   {5,1,1},
                   {25,5,1}};
    public void check(){
        int tmp = 0;
        //곡갱이 돌면서
        outer : for(int i = 0; i<seq.length; i++){
            //광물 돈다
            for(int j = 0; j<5; j++){
                //지금 캐야하는 광물 Index
                int mineralIndex = (i)*5+j;
                if(mineralIndex<mineralsInt.length){
                    // System.out.println(seq[i]);
                    // System.out.println(map[seq[i]][mineralsInt[mineralIndex]]);
                    tmp+=map[seq[i]][mineralsInt[mineralIndex]];
                }else{
                    break outer;
                }
            }
        }
        min = Math.min(min,tmp);
    }
    public void dfs(int depth){
        if(depth==N){
            // System.out.println(Arrays.toString(seq));
            check();
            return;
        }
        for(int i = 0; i<sum.length; i++){
            if(sum[i]>0){
                sum[i]--;
                seq[depth]=i;
                dfs(depth+1);
                sum[i]++;
            }
        }
    }
    public int solution(int[] picks, String[] minerals) {
        sum = new int[picks.length];
        mineralsInt = new int[minerals.length];
        for(int i = 0; i<minerals.length; i++){
            String tmp = minerals[i];
            if(tmp.equals("diamond")){
                mineralsInt[i]=0;
            }else if(tmp.equals("iron")){
                mineralsInt[i]=1;
            }else{
                mineralsInt[i]=2;
            }
        }
        for(int i = 0; i<picks.length; i++){
            sum[i]=picks[i];
            N+=picks[i];
        }
        seq = new int[N];
        // System.out.println(Arrays.toString(seq));
        dfs(0);
        // System.out.println(N);
        
        int answer = min;
        return answer;
    }
}
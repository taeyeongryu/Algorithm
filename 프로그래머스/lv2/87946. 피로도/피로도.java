class Solution {
    int stamina = 0;
    int[][] map;
    boolean[] visited;
    int max = 0;
    public void dfs(int depth){
        if(depth==map.length){
            max = depth;
            return;
        }
        for(int i = 0; i<map.length; i++){
            if(!visited[i]){
                //방문가능
                if(stamina>=map[i][0]){
                    visited[i]=true;
                    stamina-=map[i][1];
                    dfs(depth+1);
                    stamina+=map[i][1];
                    visited[i]=false;
                }
            }
        }
        max = Math.max(depth,max);
        
    }
    public int solution(int k, int[][] dungeons) {
        stamina = k;
        map = new int[dungeons.length][2];
        visited = new boolean[map.length];
        for(int i = 0; i<dungeons.length; i++){
            for(int j = 0; j<2; j++){
                map[i][j]=dungeons[i][j];
            }
        }
        dfs(0);
        
        int answer = max;
        return answer;
    }
}
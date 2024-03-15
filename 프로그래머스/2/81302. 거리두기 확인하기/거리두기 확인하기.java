import java.util.*;
class Solution {
    class Node{
        int r;
        int c;
        int count;
        public Node(int r, int c, int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }
        @Override
        public String toString(){
            return r+", "+c+", "+count;
        }
    }
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};
    List<int[]> personList;
    int bfs(String[] place){
        //사람마다 검사한다.
        for(int k = 0; k<personList.size(); k++){
            Queue<Node> q = new LinkedList<>();
            boolean[][] visited = new boolean[5][5];
            
            int[] startPerson = personList.get(k);
            visited[startPerson[0]][startPerson[1]]=true;
            q.offer(new Node(startPerson[0],startPerson[1],0));
            while(!q.isEmpty()){
                // System.out.println(q);
                Node curNode = q.poll();
                int curR = curNode.r;
                int curC = curNode.c;
                int curCount = curNode.count;

                for(int i = 0; i<4; i++){
                    int nextR = curR+dr[i];
                    int nextC = curC+dc[i];
                    if(nextR<0||nextR>=5||nextC<0||nextC>=5){
                        continue;
                    }
                    //맨하탄 거리가 2보다 크면 넘긴다.
                    if(curCount>=2){
                        continue;
                    }
                    //맨하탄 거리가 2이하일 때만 생각한다.
                    //사람일 때
                    if(visited[nextR][nextC]){
                        continue;
                    }
                    if(place[nextR].charAt(nextC)=='P'){
                        return 0;
                    }
                    //빈공간일 때
                    else if(place[nextR].charAt(nextC)=='O'){
                        visited[nextR][nextC]=true;
                        q.offer(new Node(nextR,nextC,curCount+1));
                    }
                }
            }
         }
        return 1;
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i = 0; i<places.length; i++){
            personList = new ArrayList<>();
            for(int j = 0; j<5; j++){
                for(int k = 0; k<5; k++){
                    if(places[i][j].charAt(k)=='P'){
                        personList.add(new int[]{j,k});
                    }    
                }
            }
            answer[i]=bfs(places[i]);
        }
        return answer;
    }
}
                                              
                                              
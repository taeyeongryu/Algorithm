import java.util.*;
class Solution {
    static ArrayList<String> list = new ArrayList<>();
    static boolean[] choice;
    static void dfs(int depth, String[][] tickets,String route, String cur){
        if(depth==tickets.length){
            list.add(route);
            return;
        }
        for(int i = 0; i<tickets.length; i++){
            if(!choice[i]&&tickets[i][0].equals(cur)){
                choice[i]=true;
                dfs(depth+1,tickets,route+" "+tickets[i][1],tickets[i][1]);
                choice[i]=false;;
            }
        }
    }
    public String[] solution(String[][] tickets) {
        choice = new boolean[tickets.length];
        dfs(0,tickets,"ICN","ICN");
        Collections.sort(list);
        String result = list.get(0);
        
        
        String[] answer = result.split(" ");
        return answer;
    }
}
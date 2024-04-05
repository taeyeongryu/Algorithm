import java.util.*;
class Solution {
    int[] choice;
    List<int[]> list;
    String[][] table;
    int result = 0;
    boolean minCheck(){
        for(int i = 0; i<list.size(); i++){
            int[] tmp = list.get(i);
            int count=0;
            
            Set<Integer> set = new HashSet<>();
            for(int a : choice){
                set.add(a);
            }
            
            for(int j = 0; j<tmp.length; j++){
                if(set.contains(tmp[j])){
                    count++;
                }
            }
            if(count==tmp.length){
                return false;
            }
        }
        return true;
    }
    
    boolean uniqCheck(){
        Set<String> set = new HashSet<>();
        for(int i = 0; i<table.length; i++){
            StringBuilder sb = new StringBuilder();    
            for(int j = 0;  j < choice.length; j++){
                sb.append(table[i][choice[j]]+" ");
            }
            set.add(sb.toString());
        }
        // System.out.println(set);
        return set.size()==table.length?true:false;
    }
    
    void combi(int depth, int k, int at){
        if(depth==k){
            if(minCheck()&&uniqCheck()){
                int[] tmp = new int[choice.length];
                    for(int i = 0; i<tmp.length; i++){
                        tmp[i]=choice[i];
                    }
                list.add(tmp);
                result++;
            }
            return;    
        }
        for(int i = at; i < table[0].length; i++){
            choice[depth]=i;
            combi(depth+1, k, i+1);
        }
        
    }
    public int solution(String[][] relation) {
        table = relation;
        list = new ArrayList<>();
        for(int i = 1; i<=relation.length; i++){
            choice = new int[i];
            combi(0,i,0);
            
        }
        
        
        // // System.out.println(list);
        // subset(0);
        int answer = result;
        return answer;
    }
}
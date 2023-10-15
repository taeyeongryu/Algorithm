import java.util.*;
class Solution {
    char[] charArr = {'A','E','I','O','U'};
    char[] resultArr = new char[5];
    List<String> list = new ArrayList<>();
    int N;
    String staticWord;
    
    
    void dfs(int depth){
        String str = new String(resultArr);
        list.add(str.substring(0,depth));
        if(depth == 5){
            return;   
        }
        for(int i = 0; i < 5; i++){
            resultArr[depth]=charArr[i];
            dfs(depth+1);
        }
    }
    
    public int solution(String word) {
        staticWord = word;
        N = word.length();
        
        dfs(0);
        int idx=list.indexOf(word);
        return idx;
    }
}
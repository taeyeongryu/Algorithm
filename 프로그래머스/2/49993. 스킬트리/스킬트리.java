import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 1; i <= skill.length(); i++){
            map.put(skill.charAt(i-1),i);
        }
        for(int i = 0; i < skill_trees.length; i++){
            boolean flag = true;
            int curIndex = 1;
            for(int j = 0; j < skill_trees[i].length(); j++){
                char curChar = skill_trees[i].charAt(j);
                //스킬트리가 있는 스킬이라면
                if(map.containsKey(curChar)){
                    if(map.get(curChar)==curIndex){
                        curIndex++;
                    }
                    //순서를 지키지 않으면
                    else{
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                answer++;
            }
        }
        return answer;
    }
}
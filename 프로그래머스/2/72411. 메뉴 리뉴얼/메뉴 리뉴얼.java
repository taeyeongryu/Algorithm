import java.util.*;
class Solution {
    Map<String,Integer> map;
    public void dfs(int length, int index, String order, String newCourse){
        if(newCourse.length()==length){
            map.put(newCourse, map.getOrDefault(newCourse,0)+1);
            return;
        }
        if(index==order.length()){
            return;
        }
        dfs(length, index+1, order, newCourse+order.charAt(index));
        dfs(length, index+1, order, newCourse);
    }
    
    public String[] solution(String[] orders, int[] course) {
        List<String> strList = new ArrayList<>();
        for(int i = 0; i<orders.length; i++){
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr);
        }
        for(int i = 0; i < course.length; i++){
            map = new HashMap<>();
            for(int j = 0; j < orders.length; j++){
                dfs(course[i],0,orders[j],"");
            }
            int max = Integer.MIN_VALUE;
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                max = Math.max(max,entry.getValue());
            }
            if(max<2){
                 continue;   
            }
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                if(entry.getValue()==max){
                        strList.add(entry.getKey());
                }
            }
        }
        Collections.sort(strList);
        String[] answer = new String[strList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i]=strList.get(i);
        }
        
        return answer;
    }
}
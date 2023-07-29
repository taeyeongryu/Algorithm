import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0){
            answer=cities.length*5;
        }else{
            HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0; i<cities.length; i++){
            String str = cities[i].toLowerCase();
            Set<String> set = map.keySet();
            //캐시에 있으면
            if(set.contains(str)){
                //시간 올려주기
                answer+=1;
                //참조 최신화
                map.put(str,i+1);
            }
               //캐시에 없으면
               else{
                   //캐시에 빈자리 있으면
                if(set.size()<cacheSize){
                    map.put(str,i+1);
                    answer+=5;
                }
                   //캐시에 빈자리 없으면
                   else{
                    String tmp = "";
                    int recently = Integer.MAX_VALUE;
                    for(String s:map.keySet()){
                        if(recently>map.get(s)){
                            tmp=s;
                            recently=map.get(s);
                        }
                    }
                      map.remove(tmp);
                      map.put(str,i+1);
                      answer+=5; 
                   }
            }}
        }
        
            
        
        return answer;
    }
}
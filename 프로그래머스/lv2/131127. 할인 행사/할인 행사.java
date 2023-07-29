import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String,Integer> wantmap = new HashMap<>();
        HashMap<String,Integer> realmap = new HashMap<>();
        int total = 0;
        int count = 0;
        for(int i=0; i<want.length; i++){
            total+=number[i];
            for(int j = 1; j<=number[i]; j++){
                wantmap.put(want[i],wantmap.getOrDefault(want[i],0)+1);
            }
        }
        
        for(int i = 0; i<total; i++){
            realmap.put(discount[i],realmap.getOrDefault(discount[i],0)+1);
        }
        if(realmap.equals(wantmap)){
            count++;
        }
        for(int i = total; i <discount.length; i++){
            realmap.put(discount[i],realmap.getOrDefault(discount[i],0)+1);
            if(realmap.get(discount[i-total])==1){
                realmap.remove(discount[i-total]);
            }
            else{
                realmap.put(discount[i-total],realmap.get(discount[i-total])-1);    
            }
            if(realmap.equals(wantmap)){
            count++;
        }
        }
        
        int answer = count;
        return answer;
    }
}
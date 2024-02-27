import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        boolean isZero = true;
        String[] strArr = new String[numbers.length];
        for(int i = 0; i<numbers.length; i++){
            
            if(numbers[i]!=0){
                isZero=false;
            }
            strArr[i]=numbers[i]+"";
        }
        if(isZero){
            return "0";
        }
        Arrays.sort(strArr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                int num1 = Integer.parseInt(s1+s2);
                int num2 = Integer.parseInt(s2+s1);
                return -(num1-num2);
            }
        });
        String answer = "";
        for(int i = 0; i<strArr.length; i++){
            answer+=strArr[i];
        }
        return answer;
    }
}
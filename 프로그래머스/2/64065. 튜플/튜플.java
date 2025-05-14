import java.util.*;
class Solution {
    public int[] solution(String s) {
        List<int[]> list = new ArrayList<>();
        int openIndex = 0;
        int closeIndex = 0;
        boolean isOpen = false;
        for(int i = 1; i<s.length()-1; i++){
            char ch = s.charAt(i);
            if(ch =='{'){
                // isOpen=true;
                openIndex=i;
            }else if(ch == '}'){
                closeIndex=i;
                list.add(split(s,openIndex,closeIndex));    
            }else{
                continue;
            }
        }
        Collections.sort(list, (o1,o2)->o1.length-o2.length);
        int[] answer = new int[list.size()];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < list.size(); i++){
            int[] arr=list.get(i);
            for(int j = 0; j<arr.length; j++){
                if(set.contains(arr[j])){
                    continue;
                }else{
                    set.add(arr[j]);
                    answer[i]=arr[j];
                    break;
                }
            }
            // System.out.println(Arrays.toString(arr));
        }
        
        return answer;
    }
    public int[] split(String s, int openIndex, int closeIndex){
        String str = s.substring(openIndex+1,closeIndex);
        String[] splitStringArr = str.split(",");
        int[] intArr = new int[splitStringArr.length];
        // System.out.println(Arrays.toString(splitStringArr));
        for(int i = 0; i<intArr.length; i++){
            intArr[i] = Integer.parseInt(splitStringArr[i]);
        }
        return intArr;
    }
    
}
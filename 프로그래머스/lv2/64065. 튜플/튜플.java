import java.util.*;
class Solution {
    public int[] solution(String s) {
        HashMap<Integer,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<s.length()-1; i++){
            char ch = s.charAt(i);
            if(ch=='{'){
                continue;
            }else if(ch == ','){
                 String str = sb.toString();
                int a = Integer.parseInt(str);
                map.put(a,map.getOrDefault(a,0)+1);
                sb.delete(0,sb.length());
            
            }
            else if(ch=='}'){
                String str = sb.toString();
                int a = Integer.parseInt(str);
                map.put(a,map.getOrDefault(a,0)+1);
                sb.delete(0,sb.length());
                i+=2;
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        List<int[]> list = new LinkedList<>();
        
        
        for(Integer key : map.keySet()){
            list.add(new int[]{key,map.get(key)});
        }
        Collections.sort(list,new Comparator<int[]>(){
                @Override
                public int compare(int[] a, int[] b){
                    return -(a[1]-b[1]);
                }
            });
        int[] answer = new int[list.size()];
        for(int i =0; i<list.size(); i++){
            answer[i]=list.get(i)[0];
        }
        // System.out.println(list);
        
        return answer;
    }
}
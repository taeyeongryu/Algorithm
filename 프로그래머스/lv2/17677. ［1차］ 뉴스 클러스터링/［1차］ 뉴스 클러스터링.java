import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        Set<String> set = new HashSet<>();
        List<String> aList = new LinkedList<>();
        List<String> bList = new LinkedList<>();
        for(int i = 0; i<str1.length()-1; i++){
            
            if(!isAlpha(str1.charAt(i))){
                continue;
            }else if(!isAlpha(str1.charAt(i+1))){
                i++;
                continue;
            }else{
                String sub = str1.substring(i,i+2).toLowerCase();
                aList.add(sub);
            }
        }
         for(int i = 0; i<str2.length()-1; i++){
            if(!isAlpha(str2.charAt(i))){
                continue;
            }else if(!isAlpha(str2.charAt(i+1))){
                i++;
                continue;
            }else{
                String sub = str2.substring(i,i+2).toLowerCase();
                bList.add(sub);
            }
        }
        // System.out.println(aList);
        // System.out.println(bList);
        Collections.sort(aList);
        Collections.sort(bList);
        int inter = 0;
        int sum = 0;
        for(int i = 0; i<aList.size(); i++){
            int a = aList.lastIndexOf(aList.get(i));
            int asize = a-i+1;
            int bsize = 0;
            if(bList.contains(aList.get(i))){
                bsize = bList.lastIndexOf(aList.get(i))-bList.indexOf(aList.get(i))+1;
            }
            inter+=Math.min(asize,bsize);
            set.add(aList.get(i));
            if(a!=-1){
                i=a;
            }
        }
        for(int i = 0; i<bList.size(); i++){
            set.add(bList.get(i));
        }
        for(String s : set){
            int a = 0;
            int b = 0;
            if(aList.contains(s)){
                a = aList.lastIndexOf(s)-aList.indexOf(s)+1;
            }
            if(bList.contains(s)){
                b = bList.lastIndexOf(s)-bList.indexOf(s)+1;
            }
            sum+=Math.max(a,b);
        }
        // System.out.println(inter);    
        // System.out.println(sum);
        double answer = 0;
        if(inter==0&&sum==0){
            answer=1;
        }else
            answer = ((double)inter/sum);
        answer*=65536;
        
        
        return (int)answer;
    }
    public boolean isAlpha(char a){
        if('a'<=a && a<='z'||'A'<=a && a<='Z'){
            return true;
        }
        else{
            return false;
        }
            
    }
}
class Solution {
    public int solution(String s) {
        int answer = s.length();
        StringBuilder result = new StringBuilder();
        String flag = "";
        String compare = "";
        
        for(int len = 1; len <= s.length()/2; len++){
            int count = 1;
            flag = s.substring(0,len);
            for(int i = len; i<s.length(); i+=len){
                compare = s.substring(i,Math.min(i+len,s.length()));
                if(flag.equals(compare)){
                    count++;
                }else{
                    if(count!=1){
                        result.append(count+"");
                        result.append(flag);
                        
                    }else{
                        result.append(flag);
                        
                    }
                    count=1;
                    flag = compare;
                }
            }
            if(count!=1){
                result.append(count+"");
                result.append(flag);
                                
            }else{
                result.append(flag);        
            }
            answer = Math.min(answer,result.length());
            // System.out.println(result.toString());
            result=new StringBuilder();
        }
        
        return answer;
    }
}
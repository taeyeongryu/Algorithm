class Solution {
    public String solution(String s) {
        String answer = "";
        for(int i = 0; i<s.length(); i++){
            //맨 앞이거나, 바로앞 문자가 공백이면
            if(i==0||s.charAt(i-1)==' '){
                //자기가 공백이 아니면
               if(s.charAt(i)!=' '){
                   //숫자면
                   if('0'<=s.charAt(i) && s.charAt(i)<='9'){
                       answer+=s.charAt(i);
                   }
                   //아니면
                   else if('a'<=s.charAt(i) && s.charAt(i)<='z'){
                       answer+=(char)(s.charAt(i)-32);
                   }
                   else{
                        answer+=s.charAt(i);
                   }
               }
                //자기가 공백이면
                else{
                    answer+=' ';
                }
            }
            else{
                 //자기가 공백이 아니면
               if(s.charAt(i)!=' '){
                   //숫자면
                   if('0'<=s.charAt(i) && s.charAt(i)<='9'){
                       answer+=s.charAt(i);
                   }
                   //아니면
                   else if('a'<=s.charAt(i) && s.charAt(i)<='z'){
                       answer+=s.charAt(i);
                   }
                   else{
                        answer+=(char)(s.charAt(i)+32);
                   }
               }
                //자기가 공백이면
                else{
                    answer+=' ';
                }
            }
        }
//         System.out.println((int)'A');
//         System.out.println((int)'a');
        
//         System.out.println(answer);
        return answer;
    }
}
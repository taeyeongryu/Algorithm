import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int sequence = 1;
        
       outer : for(int i =0;;i++){
            int number = i;
            //문자열로 n진수로 바꾼 숫자
            String changedNumber = "";
            if(number==0){changedNumber="0";}
           else{
            while(number!=0){
                int tmp = number%n;
                //10이상이면
                if(tmp>=10){
                    switch(tmp){
                        case 10:
                            changedNumber = "A"+changedNumber;
                            break;
                        case 11:
                            changedNumber = "B"+changedNumber;
                            break;
                        case 12:
                            changedNumber = "C"+changedNumber;
                            break;
                        case 13:
                            changedNumber = "D"+changedNumber;
                            break;
                        case 14:
                            changedNumber = "E"+changedNumber;
                            break;
                        case 15:
                            changedNumber = "F"+changedNumber;
                            break;
                    }   
                }
                else{
                    changedNumber=tmp+changedNumber;    
                }
                number/=n;
            }      
           }
            for(int j = 0; j<changedNumber.length(); j++){
                if(sequence==m+1){
                    sequence = 1;
                }
                //검사 시작
                if(sequence==p){
                    sb.append(changedNumber.charAt(j));
                    count++;
                    if(count==t){
                        break outer;
                    }
                }
                //카운트 올려주기
                sequence++;
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
}
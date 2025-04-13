class Solution {
    boolean solution(String s) {
        int count = 0;
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i)=='('){
                count++;
            }else{
                if(count>0){
                    count--;
                }else{
                    return false;
                }
            }
        }
        if(count>0){
            return false;
        }
        boolean answer = true;
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        // System.out.println("Hello Java");

        return answer;
    }
}
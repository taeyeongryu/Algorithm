class Solution {
   
    public int solution(int n) {
       int num = Integer.bitCount(n);
        int answer = 0;
        for(int i= n+1 ;; i++){
            if(num==Integer.bitCount(i)){
                answer=i;
                break;
            }
        }
        return answer;
    }
}
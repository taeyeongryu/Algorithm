class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for(int a = 0; a<=d/k; a++){
            
           long tmp = (long) Math.sqrt((((double)d*d)/((double)k*k)-(double)a*a));
            // System.out.println("a : "+a+", b : "+tmp);
            answer+=tmp+1;
        }
        
        return answer;
    }
}
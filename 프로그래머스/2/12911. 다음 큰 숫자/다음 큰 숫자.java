class Solution {
    public int solution(int n) {
        int bitCountN = Integer.bitCount(n);
        int answer = 0;
        while(true){
            n++;
            if(bitCountN==Integer.bitCount(n)){
                answer=n;
                break;
            }
        }
        return answer;
    }
}
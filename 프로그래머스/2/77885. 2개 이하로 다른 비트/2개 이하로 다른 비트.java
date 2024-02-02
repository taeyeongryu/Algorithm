class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i = 0; i<numbers.length; i++){
            long num = numbers[i];
            //짝수
            
            if(num%2==0){
                answer[i]=num+1;
            }
            //홀수
            else{
                String result = "";
                String tmp = Long.toBinaryString(num);
                int lastIndex = tmp.lastIndexOf("0");
                //만약 전체가 다 1이면
                if(lastIndex==-1){
                    result = "10"+tmp.substring(1);
                }
                //만약 전체가 다 1이 아니면
                else{
                    result = tmp.substring(0,lastIndex)+"10"+tmp.substring(lastIndex+2);
                }
                answer[i]=Long.parseLong(result,2);
            }
        }
        return answer;
    }
}
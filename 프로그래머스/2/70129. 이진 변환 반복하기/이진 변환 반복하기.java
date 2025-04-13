class Solution {
    public int[] solution(String s) {
        int zeroCount = 0;
        int count=0;
        while(!s.equals("1")){
            count++;
            int num = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i)=='0'){
                    zeroCount++;
                }else{
                    num++;
                }
            }
            s = Integer.toBinaryString(num);
        }
        // System.out.println(zeroCount);
        // System.out.println(count);
        int[] answer = new int[2];
        answer[0]=count;
        answer[1]=zeroCount;
        return answer;
    }
}
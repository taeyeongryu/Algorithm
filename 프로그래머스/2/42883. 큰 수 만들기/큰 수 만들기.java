class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        for(int i = 1; i <= number.length()-k; i++){
            //check해야되는 마지막 인덱스
            int lastIndex = k+i-1;
            int max = 0;
            int maxIndex = startIndex;
            for(int j = startIndex; j<=lastIndex; j++){
                int curNum = number.charAt(j)-'0';
                if(max<curNum){
                    max=curNum;
                    maxIndex=j;
                }
            }
            startIndex = maxIndex+1;
            sb.append(max+"");
        }
        return sb.toString();
    }
}
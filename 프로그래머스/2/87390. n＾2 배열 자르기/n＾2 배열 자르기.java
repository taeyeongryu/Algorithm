class Solution {
    public int[] solution(int n, long left, long right) {
  
        int[] answer = new int[(int)(right-left+1)];
        for(int i = 0; i < right - left + 1; i++){
            int leftNum = (int)((i+left)/n);
            int rightNum = (int)((i+left)%n);
            
            answer[i]= (leftNum>rightNum?leftNum:rightNum) + 1;
        }
        return answer;
    }
}
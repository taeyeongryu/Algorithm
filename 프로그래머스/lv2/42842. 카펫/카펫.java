class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown+yellow;
        int[] answer = {};
        //가로 길이
        for(int i = 3; i<=brown; i++){
            int tmp = total%i;
            if(tmp!=0){continue;}
            int height = total/i;
            if(i*2+(height-2)*2==brown){
                answer = new int[]{height,i};
                break;
            }
        }
        
        return answer;
    }
}
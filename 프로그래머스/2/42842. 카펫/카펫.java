class Solution {
    public int[] solution(int brown, int yellow) {
        int rowLength = yellow;
        
        for(; rowLength >= 1; rowLength--){
            if(yellow % rowLength != 0){
                continue;
            }
            int colLength = yellow / rowLength;
            if((rowLength+colLength)*2+4 == brown){
                break;
            }
        }
        int[] answer = { rowLength+2 , yellow/rowLength + 2};
        return answer;
    }
}
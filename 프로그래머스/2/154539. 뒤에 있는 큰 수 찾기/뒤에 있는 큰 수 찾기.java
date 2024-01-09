import java.util.*;
class Solution {
    static void findBig(int[] numbers,int[] answer,int[] bigIndex,int startIndex, int targetIndex) {
        //더이상 뒤에 큰 수가 없으면
        if (numbers[startIndex] < numbers[targetIndex]) {
            answer[startIndex] = numbers[targetIndex];
            bigIndex[startIndex] = targetIndex;
            return;
        } else {
            //더이상 뒤에 큰수가 없다면
            if(bigIndex[targetIndex]==-1){
                return;
            }
            //아니라 뒤에 더 큰수가 있다면
            else{
                findBig(numbers, answer, bigIndex, startIndex, bigIndex[targetIndex]);
            }
        }
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int[] bigIndex = new int[numbers.length];
        Arrays.fill(answer, -1);
        Arrays.fill(bigIndex, -1);
        for (int i = numbers.length - 2; i >= 0; i--) {
            findBig(numbers, answer, bigIndex, i, i + 1);
        }
        return answer;
    }
}
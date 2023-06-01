import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        //포인터란 비교해야 되는 값을 의미한다.
        int ap = A.length-1;
        int bp = B.length-1;
        int count = 0;
        for(int i = ap; i >= 0; i--){
            if(A[i]<B[bp]){
                count++;
                bp--;
            }
        }
        int answer = count;
        return answer;
    }
}
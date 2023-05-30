import java.util.*;
import java.io.*;
class Solution
{
    public int solution(int []A, int []B)
    {   Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        for(int i = 0;i<A.length;i++){
            answer+=A[i]*B[A.length-i-1];      
        }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        return answer;
    }
}
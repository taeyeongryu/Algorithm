import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int count = 0;
        int right = 1;
        for(int i = 0; i < stations.length; i++){
          
            int lt = stations[i]-w;
            int rt = stations[i]+w;
            if(lt<=right){
                right=rt + 1;
                continue;
            }else{
                count+=((lt-right)/(2*w+1));
                if(((lt-right)%(2*w+1))>0){
                    count+=1;
                }
                right=rt+1;
                }
        }
        if(right<=n){
            count+=(n-right+1)/(2*w+1);
            if(((n-right+1)%(2*w+1))>0){
                    count+=1;
                }
        }
        
        
        int answer = count;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}
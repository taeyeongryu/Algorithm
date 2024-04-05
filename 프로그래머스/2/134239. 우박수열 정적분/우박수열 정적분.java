import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> list = new ArrayList<>();
        
        list.add(k);
        while(k!=1){
            //홀수
            if(k%2==1){
                k=k*3+1;
            }
            //짝수
            else{
                k=k/2;
            }
            list.add(k);
        }
        int n = list.size()-1;
        double[] totalIntegral = new double[n+1];
        for(int i = 1; i < n+1; i++){
            
            int left = list.get(i-1);
            int right = list.get(i);
            
            int min = Math.min(left,right);
            int max = Math.max(left,right);
            
            totalIntegral[i]=((double)max-min)/2+min+totalIntegral[i-1];
        }
        double[] answer = new double[ranges.length];
        // System.out.println(Arrays.toString(totalIntegral));
        for(int i = 0; i<ranges.length; i++){
            int a = ranges[i][0];
            int b = n+ranges[i][1];
            // System.out.println("a : "+a);
            // System.out.println("b : "+b);
            if( a>n || b<a || b>n ){
                answer[i] = -1.0;
            }else{
                answer[i] = totalIntegral[b]-totalIntegral[a];
            }
            // System.out.println("answer[i] : "+answer[i]);
        }
        
        // System.out.println(n);
        
        
        return answer;
    }
}
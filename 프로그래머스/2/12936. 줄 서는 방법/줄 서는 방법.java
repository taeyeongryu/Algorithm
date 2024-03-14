import java.util.*;
class Solution {
    List<Integer> numberList = new ArrayList<>();
    int[] result;
    long[] pactorial;
    //index는 현재 찾으려는 위치
    public void find(int n, long k, int index){
        // System.out.println(k);
        
        if(index==n){
            //하나 남은 숫자를 결과에 넣는다.
            result[index] = numberList.remove(0);
            return;
        }
        long remain = k%pactorial[n-index];
        int div = (int)(k/pactorial[n-index]);
        
        if(remain==0){
            
            result[index]=numberList.get(div-1);
            numberList.remove(div-1);
            find(n,pactorial[n-index],index+1);
            
        }else{
            
            result[index]=numberList.get(div);
            numberList.remove(div);
            find(n,remain,index+1);
            
        }
        
    }
    public int[] solution(int n, long k) {
        pactorial = new long[n+1];
        result = new int[n+1];
        for(int i = 1; i <= n; i++){
            numberList.add(i);
        }
        pactorial[1]=1;
        for(int i = 2; i <= n; i++){
            pactorial[i]=i*pactorial[i-1];
        }
        find(n,k,1);
        int[] answer = new int[n];
        for(int i = 0; i<answer.length; i++){
            answer[i]=result[i+1];
        }
        
        return answer;
    }
}
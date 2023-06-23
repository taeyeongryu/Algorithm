import java.util.*;
class Solution {
    public int solution(int[] a) {
        int[] leftmin = new int[a.length];
        int[] rightmin = new int[a.length];
        int left = a[0];
        int right = a[a.length-1];
        for(int i = 1; i<a.length-1; i++){
            leftmin[i]=left;
            left = Math.min(left,a[i]);
        }
        for(int i = a.length-2; i>=1; i--){
            rightmin[i]=right;
            right=Math.min(right,a[i]);
        }
        // System.out.println(Arrays.toString(leftmin));
        // System.out.println(Arrays.toString(rightmin));
        int answer = 0;
        if(a.length==1){
            answer=1;
        }else{
            answer=2;
            for(int i = 1; i<a.length-1; i++){
                if(!(a[i]>leftmin[i]&&a[i]>rightmin[i])){
                    answer++;
                }
            }
        }
        return answer;
    }
}
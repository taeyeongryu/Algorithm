import java.util.*;
class Solution {
   
    public int findGcd(int a, int b){
        if(b==0)return a;
        return findGcd(b,a%b);
    }
    public int solution(int[] arrayA, int[] arrayB) {
        int[] gcdA = new int[arrayA.length];
        int[] gcdB = new int[arrayB.length];
        
        gcdA[0] = arrayA[0];
        gcdB[0] = arrayB[0];
       
       
        for(int i = 1; i < arrayA.length; i++){
            gcdA[i]=findGcd(gcdA[i-1],arrayA[i]);
            gcdB[i]=findGcd(gcdB[i-1],arrayB[i]);
        }
        
        // System.out.println(Arrays.toString(gcdA));
        // System.out.println(Arrays.toString(gcdB));
        int result = 0;
        for(int i = 0; i<arrayA.length; i++){
            if(arrayA[i]%gcdB[arrayA.length-1]==0){
                break;
            }
            if(i==arrayA.length-1){
                result = gcdB[arrayA.length-1];
            }
        }
        for(int i = 0; i<arrayB.length; i++){
            if(arrayB[i]%gcdA[arrayA.length-1]==0){
                break;
            }
            if(i==arrayA.length-1){
                result =Math.max(result,gcdA[arrayA.length-1]);
            }
        }
        // if(gcdA[arrayA.length-1]%gcdB[arrayA.length-1]!=0){
        //     result = Math.max(result,gcdB[arrayA.length-1]);
        // }
        // if(gcdB[arrayA.length-1]%gcdA[arrayB.length-1]!=0){
        //     result = Math.max(result,gcdA[arrayA.length-1]);
        // }   
        return result;
    }
}
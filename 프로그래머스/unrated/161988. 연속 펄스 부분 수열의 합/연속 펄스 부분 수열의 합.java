import java.util.*;
class Solution {
    int[] seq1;
    int[] seq2;
    long[][] result1;
    long[][] result2;
    long max = Long.MIN_VALUE;
    public long solution(int[] sequence) {
        seq1 = new int[sequence.length];
        seq2 = new int[sequence.length];
        for(int i = 0; i<sequence.length; i++){
            if(i%2==0){
                seq1[i]=sequence[i];
                seq2[i]=-sequence[i];
            }else{
                seq1[i]=-sequence[i];
                seq2[i]=sequence[i];
            }
        }
        //더한거, 안더한거
        result1 = new long[2][sequence.length];
        result2 = new long[2][sequence.length];
        result1[0][0]=seq1[0];
        result2[0][0]=seq2[0];
        max = Math.max(max,result1[0][0]);
        max = Math.max(max,result1[1][0]);
        max = Math.max(max,result2[0][0]);
        max = Math.max(max,result2[1][0]);
        for(int i = 1; i<sequence.length; i++){
            result1[0][i]=Math.max(result1[0][i-1]+seq1[i],seq1[i]);
            result1[1][i]=Math.max(result1[0][i-1],result1[1][i-1]);
            result2[0][i]=Math.max(result2[0][i-1]+seq2[i],seq2[i]);
            result2[1][i]=Math.max(result2[0][i-1],result2[1][i-1]);
            max = Math.max(max,result1[0][i]);
            max = Math.max(max,result1[1][i]);
            max = Math.max(max,result2[0][i]);
            max = Math.max(max,result2[1][i]);
        }
        // System.out.println(Arrays.deepToString(result1));
        // System.out.println(Arrays.deepToString(result2));
        long answer = max;
        return answer;
    }
}
import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[col-1],o2[col-1])!=0?Integer.compare(o1[col-1],o2[col-1]): -Integer.compare(o1[0],o2[0]);
            }
        });
        long[] sArr = new long[data.length+1];
        for(int i = 0; i <data.length; i++){
            for(int j = 0; j<data[i].length; j++){
                sArr[i+1] =sArr[i+1]+ (data[i][j]%(i+1));
            }
        }
        // System.out.println(Arrays.deepToString(data));
        // System.out.println(Arrays.toString(sArr));
        // System.out.println(Integer.parseInt());
        int answer = 0;
        for(int i=row_begin; i<=row_end; i++){
            answer^=sArr[i];
        }
        
        return answer;
    }
}
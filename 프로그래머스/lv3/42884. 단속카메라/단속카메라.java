import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1]<o2[1]){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        
        int cam_location = Integer.MIN_VALUE;
        int answer = 0;
        // System.out.println(Arrays.deepToString(routes));
        for(int i = 0; i < routes.length; i++){
            int start = routes[i][0];
            int end = routes[i][1];
            if(start<=cam_location&&cam_location<=end){
                continue;
            }
            else{
                cam_location=end;
                answer++;
            }
        }
        return answer;
    }
}
class Solution {
    public int solution(int n) {
        int lt = 1;
        int rt = 1;
        int sum = 1;
        int answer = 0;
        while(lt <= rt){
            // System.out.println(sum);
            if(sum == n){
                answer++;
                rt++;
                sum+=rt;
            }else if(sum < n){
                rt++;
                sum+=rt;
            }else{
                sum-=lt;
                lt++;
            }
        }
        return answer;
    }
}
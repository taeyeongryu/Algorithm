class Solution {
    public int solution(int n) {
        int count = 0;
        int total = 1;
        int left = 1;
        int right = 1;
        while(left<=right){
            if(total<n){
                right++;
                total+=right;    
            }
            else if(total==n){
                count++;
                right++;
                total+=right; 
            }else{
                total-=left;
                left++;
            }
        }
        return count;
    }
}
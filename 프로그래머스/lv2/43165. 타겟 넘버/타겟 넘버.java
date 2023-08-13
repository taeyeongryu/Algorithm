class Solution {
    int count = 0;
    int[] numArr;
    void dfs(int depth,int cur,int target){
        if(depth==numArr.length){
            if(cur==target){
                count++;
            }
            return;
        }
        dfs(depth+1,cur+numArr[depth],target);
        dfs(depth+1,cur-numArr[depth],target);
    }
    
    public int solution(int[] numbers, int target) {
        numArr = new int[numbers.length];
        for(int i = 0; i<numbers.length; i++){
            numArr[i]=numbers[i];
        }
        dfs(0,0,target);
        int answer = count;
        return answer;
    }
}
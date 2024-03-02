class Solution {
    public int[] solution(int[] sequence, int k) {
        int lp = 0;
        int rp = 0;
        int sum = sequence[0];
        int[] answer = new int[2];
        int dist = sequence.length;
        while(rp < sequence.length && lp <= rp){
            if(sum>k){
                sum-=sequence[lp++];
            }else if(sum<k){
                if(rp==sequence.length-1){
                    break;
                }
                sum+=sequence[++rp];
            }
            //같다면
            else{
                //거리가 더 짧으면
                if(dist>rp-lp){
                    answer[0]=lp;
                    answer[1]=rp;
                    dist = rp-lp;
                }
                //거리가 더 길면
                else{
                    sum-=sequence[lp++];
                }        
            }
        }
        return answer;
    }
}
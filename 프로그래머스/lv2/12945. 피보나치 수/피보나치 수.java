class Solution {
    int[] fibo;
    public int solution(int n) {
        fibo = new int[n+1];
        fibo[1]=1;
        fibo[2]=1;
        int answer = F(n);
        return answer;
    }
    public int F(int n){
        if(fibo[n]>0){
            return fibo[n];
        }
        else{
            return fibo[n]=(F(n-1)+F(n-2))%1234567;
        }
    }
}
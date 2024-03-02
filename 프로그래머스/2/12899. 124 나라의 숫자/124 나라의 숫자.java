class Solution {
    public String solution(int n) {
        String[] arr = {"4", "1", "2"};
        String answer = "";
        while(n!=0){
            int div = n%3;
            n/=3;
            answer = arr[div] + answer;
            if(div==0){
                n--;
            }
        }
        return answer;
    }
}
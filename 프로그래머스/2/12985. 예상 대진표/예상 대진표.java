class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = calRound(a,b,0);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        // System.out.println("Hello Java");

        return answer;
    }
    public int calRound(int a, int b, int count){
        int A = Math.min(a,b);
        int B = Math.max(a,b);
        if(A+1==B && A/2!=B/2){
            return count+1;
        }
     
        if(A%2==1){
            A = A/2+1;
        }else{
            A = A/2;
        }
        if(B%2==1){
            B = B/2+1;
        }else{
            B = B/2;
        }
        return calRound(A,B,count+1);
    }
}
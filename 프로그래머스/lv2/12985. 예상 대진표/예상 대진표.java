class Solution
{
    public int solution(int n, int a, int b)
    {
        int x = Math.min(a,b);
        int y = Math.max(a,b);
        int count=1;
        //정답이 아니면
        while(!(x%2==1&&y%2==0&&x+1==y)){
            count++;
            x=(x+1)/2;
            y=(y+1)/2;
        }
        int answer = count;
        

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}
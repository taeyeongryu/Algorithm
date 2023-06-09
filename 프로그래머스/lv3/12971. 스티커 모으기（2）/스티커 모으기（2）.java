class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        if(len==1)return sticker[0];
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        
        //첫번째 스티커 뗄때
        dp1[0]=sticker[0];
        dp1[1]=sticker[0];
        for(int i = 2; i<len-1; i++){
            dp1[i]=Math.max(dp1[i-1],dp1[i-2]+sticker[i]);
        }
        //첫번째 스티커 안뗄때
        dp2[1]=sticker[1];
        for(int i = 2; i<len; i++){
            dp2[i]=Math.max(dp2[i-1],dp2[i-2]+sticker[i]);
        }
        answer = Math.max(dp1[len-2],dp2[len-1]);
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}
class Solution {
    public int gcd(int a, int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    public long solution(int w, int h) {
        
        int gcdNum = gcd(w,h);
        int startW = w/gcdNum;
        int startH = h/gcdNum;
        double tan = (double)startH/(double)startW;
        
        long total = 0;
        for(int i = 1; i<=startW; i++){
            //i가 하나적은거랑 높이를 비교해야한다.
            int left = (int) Math.floor((i-1)*tan);
            int right = (int) Math.ceil(i*tan);
           
            total+=(right-left);
            
        }
        long totalSqare = (long)w*(long)h;
        long answer = totalSqare-total*gcdNum;
        return answer;
    }
}
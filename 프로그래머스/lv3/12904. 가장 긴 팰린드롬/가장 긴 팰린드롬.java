import java.util.*;
class Solution{
    int[][] check;
    //이 문자열에서 start부터 end까지가 펠린드롬인지 찾는것
    boolean isPalindrome(String str,int start,int end){
        if(check[start][end]==1){
            return true;
        }
        else{
            if(check[start][end]==-1){
                return false;
            }
            //검사를 해봐야하는 것이여
            else{
                //여기는 check배열을 업데이트 해야되는 것이여
                //같으면 그 아랫것들 해봐야지
                if(str.charAt(start)==str.charAt(end)){
                    if(start+1==end){
                        check[start][end]=1;
                        return true;
                    }
                    else{
                        if(isPalindrome(str,start+1,end-1)){
                            check[start][end]=1;
                            return true;
                        }
                        else{
                            check[start][end]=-1;
                            return false;
                        }
                    }
                }
                else{
                    check[start][end]=-1;
                    return false;
                }
            }
        }
    }
    public int solution(String s){
        int answer = 0;
        check = new int[s.length()][s.length()];
        for(int i = 0; i<s.length() ;i++){
            check[i][i]=1;
        }
        outer : for(int i = s.length(); i>=1; i--){
            //i는 펠린드롬의 길이를 의미한다.
            for(int j = 0; j+i-1<s.length() ;j++){
                if(isPalindrome(s,j,j+i-1)){
                    answer = i;
                    break outer;
                }
            }
        }
        // System.out.println(Arrays.deepToString(check));
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        // System.out.println("Hello Java");
        return answer;
    }
}
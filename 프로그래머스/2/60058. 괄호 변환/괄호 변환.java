class Solution {
    public boolean checkRight(String str){
        if(str.equals("")){
            return true;
        }
        int num=0;
        for(int i = 0; i<str.length(); i++){
            char tmp = str.charAt(i);
            if(tmp=='('){
                num++;
            }else{
                if(num==0){
                    return false;
                }
                num--;
            }
        }       
        return num == 0 ? true : false;
    }
    public boolean checkBalance(String str){
        int left = 0;
        int right = 0;
        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i)=='('){
                left++;
            }else{
                right++;
            }
        }
        return left==right;
    }
    public String allRight(String str){
        String result = "";
        if(checkRight(str)){
            return str;
        }
        for(int i = 2; i<=str.length(); i+=2){
            String u = str.substring(0,i);
            String v = str.substring(i,str.length());
            if(!checkBalance(u)){
                continue;
            }
            if(checkRight(u)){
                return u+allRight(v);
            }
            result = "("+allRight(v)+")"+changeDir(u);
            break;
        }
        return result;
    }
    public String changeDir(String str){
        if(str.length()<=2)return"";
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<str.length()-1; i++){
            char tmp = str.charAt(i);
            if(tmp=='('){
                sb.append(')');
            }else{
                sb.append('(');
            }
        }
        return sb.toString();
    }
    public String solution(String p) {
        String answer = allRight(p);
        return answer;
    }
}
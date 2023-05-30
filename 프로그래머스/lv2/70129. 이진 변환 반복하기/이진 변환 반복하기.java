class Solution {
    static int count = 0;
    static String toOne(String str){
        String result="";
        for(int i = 0; i < str.length();i++){
            if(str.charAt(i)=='0'){
                    count++;
            }
            else{
                result+=str.charAt(i);
            }
        }
        return result;
    }
    static String toTen(String str){
        int length = str.length();
        String result="";
        while(length > 0){
            result= (length%2)+result;
            length/=2;
        }
        return result;
    }
    public int[] solution(String s) {
        System.out.println("s : "+s);
        int time = 0;
        while(!s.equals("1")){
            time++;
            String a = toOne(s);
            System.out.println("a : "+a);
            s = toTen(a);
            System.out.println("s : "+s);
        }
        int[] answer = {time, count };
        return answer;
    }
}
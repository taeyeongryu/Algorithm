import java.util.*;
class Solution {
    public String solution(String s) {
        // System.out.println((int)'a');
        // System.out.println((int)'A');
        // System.out.println(Arrays.toString(words));
        // String answer = "";
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch =  s.charAt(i);
            if(ch==' '){
                sb.append(' ');
                isFirst=true;
                continue;
            }else{
                if(isFirst){
                    if('0'<=ch && ch<='9'){
                        sb.append(ch);
                    }else if('a'<=ch && ch<='z'){
                        sb.append((char)(ch-32));
                    }else{
                        sb.append(ch);
                    }
                    isFirst=false;
                }
                else{
                if('A'<=ch && ch<='Z'){
                        sb.append((char)(ch+32));
                    }else{
                        sb.append(ch);
                    }
                }
            }
        }
        // List<String> list = new ArrayList<>();
        // for(String word : words){
        //     StringBuilder sb = new StringBuilder();
        //     for(int i = 0; i < word.length(); i++){
        //         char ch = word.charAt(i);
        //         if(i==0){
        //             if('0'<=ch && ch<='9'){
        //                 sb.append(ch);
        //             }else if('a'<=ch && ch<='z'){
        //                 sb.append((char)(ch-32));
        //             }else{
        //                 sb.append(ch);
        //             }
        //         }else{
        //             if('A'<=ch && ch<='Z'){
        //                 sb.append((char)(ch+32));
        //             }else{
        //                 sb.append(ch);
        //             }
        //         }
        //     }
        //     list.add(sb.toString());
        // }
        // for(int i = 0; i < list.size(); i++){
        //     answer+=list.get(i);
        //     if(i!=list.size()-1){
        //         answer+=' ';
        //     }
        // }
        return sb.toString();
    }
}
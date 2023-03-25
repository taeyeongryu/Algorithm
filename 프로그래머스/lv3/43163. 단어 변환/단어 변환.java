import java.util.*;
class Solution {
     static class Node{
        String str;
        int num;
        Node(String str, int num){
            this.str = str;
            this.num = num;
        }
    }
    boolean[] visited;
   
    boolean go(String str1, String str2){
        boolean flag = true;
        int cnt=0;
        for(int i=0 ; i<str1.length(); i++){
            if(str1.charAt(i)!=str2.charAt(i)){
                cnt++;
            }
        }
        if(cnt>1){
            flag=false;
                 }
        return flag;
    }
    Queue<Node> queue = new LinkedList<>();
  
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        for(int i = 0; i < words.length; i++){
            if(go(begin, words[i])){
                visited[i]=true;
                queue.offer(new Node(words[i],1));
            }
        }
        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            
            
            if(tmp.str.equals(target)){
            
                answer=tmp.num;
                break;
            }
         for(int i = 0; i < words.length; i++){
            if(go(tmp.str,words[i])){
                if(!visited[i]){
                visited[i]=true;
                queue.offer(new Node(words[i],tmp.num+1));    
                }
            }
        }
        } 
        return answer;
    }
}
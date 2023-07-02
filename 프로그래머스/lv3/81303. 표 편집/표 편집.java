import java.util.*;
class Solution {
    class Node{
        
        int pre;
        int cur;
        int nxt;
        
        Node(int pre, int cur, int nxt){
            this.pre=pre;
            this.cur=cur;
            this.nxt=nxt;
        }
        
    }
    
    
    public String solution(int n, int k, String[] cmd) {   
        int[] pre = new int[n];
        int[] next = new int[n];
        
        for(int i = 0; i < n; i++){
            pre[i]=i-1;
            next[i]=i+1;
        }
        next[n-1]=-1;
        Stack<Node> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        
        for(int i = 0; i<cmd.length; i++){
            String str = cmd[i];
            char oper = str.charAt(0);
            if(oper=='U'){
                int num =Integer.parseInt(str.substring(2));
                while(num-->0){
                    k=pre[k];
                }
            }else if(oper=='D'){
                int num =Integer.parseInt(str.substring(2));
                while(num-->0){
                    k=next[k];
                }
            }
            else if(oper=='C'){
                stack.push(new Node(pre[k],k,next[k]));
                //첫값 아니면
                if(pre[k]!=-1){
                    next[pre[k]]=next[k];
                }
                //끝값 아니면
                if(next[k]!=-1){
                    pre[next[k]]=pre[k];
                }
                sb.setCharAt(k,'X');
                if(next[k] != -1) k = next[k];
                else k = pre[k]; //마지막 행인 경우에 바로 윗 행 선택
            }
            else{
                Node node = stack.pop();
                if(node.pre!=-1){
                    next[node.pre]=node.cur;
                }
                if(node.nxt!=-1){
                    pre[node.nxt]=node.cur;
                }
                sb.setCharAt(node.cur,'O');
            }
        }
        return sb.toString();
    }
}
import java.util.*;
class Solution {
    class Node implements Comparable<Node>{
        int x;
        int y;
        int idx;
        Node left;
        Node right;
        Node(int x, int y, int idx, Node left, Node right){
            this.x=x;
            this.y=y;
            this.idx=idx;
            this.left=left;
            this.right=right;
        }
        @Override
        public String toString(){
            return "idx : "+idx+", left : "+(left!=null?left.idx:"null")+", right : "+(right!=null?right.idx:"null");
        }
        @Override
        public int compareTo(Node o){
            if(this.y>o.y){
                return -1;
            }else if(this.y<o.y){
                return 1;
            }else{
                return this.x-o.x;
            }
        }
    }
    
   
 
    void insert(Node parent, Node child){
        //왼쪽 자식
        if(parent.x>child.x){
            if(parent.left==null)
                parent.left=child;
            else
                insert(parent.left,child);
        }else{
            if(parent.right==null)
                parent.right=child;
            else
                insert(parent.right,child);
        }
    }
    List<Integer> prelist;
    List<Integer> afterlist;
    void preorder(Node node){
        prelist.add(node.idx);
        if(node.left!=null){
            preorder(node.left);
        }
        if(node.right!=null){
            preorder(node.right);
        }
    }
    void afterorder(Node node){
        if(node.left!=null){
            afterorder(node.left);
        }
        if(node.right!=null){
            afterorder(node.right);
        }
        afterlist.add(node.idx);
    }
    public int[][] solution(int[][] nodeinfo) {
        List<Node> list = new ArrayList<>();
        prelist = new ArrayList<>();
        afterlist = new ArrayList<>();
        for(int i = 0; i<nodeinfo.length; i++){
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null));
        }
        Collections.sort(list);
        Node root = list.get(0);
        for(int i = 1; i<list.size(); i++){
            insert(root,list.get(i));
        }
        preorder(root);
        afterorder(root);
        int[][] answer = new int[2][nodeinfo.length];
        for(int i = 0; i<answer.length; i++){
            for(int j = 0; j<nodeinfo.length; j++){
                if(i==0){
                    answer[i][j]=prelist.get(j);
                }
                else{
                    answer[i][j]=afterlist.get(j);
                }
            }
        }
        
        return answer;
    }
}
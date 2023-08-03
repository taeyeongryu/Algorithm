import java.util.*;

class Solution {
    
    class Node implements Comparable<Node>{
        int index;
        int priorities;
        Node(int index, int priorities){
            this.index=index;
            this.priorities=priorities;
        }
        @Override
        public int compareTo(Node node){
            return -this.priorities+node.priorities;
        }
    }
    
    Queue<Node>   q = new LinkedList<>();
    PriorityQueue<Node> pq = new PriorityQueue<>();
    
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        for(int i = 0; i<priorities.length; i++){
            q.offer(new Node(i,priorities[i]));
            pq.offer(new Node(i,priorities[i]));
        }
        int count=0;
        while(true){
            //빼서 버린다.
            if(q.peek().priorities>=pq.peek().priorities){
                count++;
                Node cur=q.poll();
                //만약 찾는 것이면
                if(cur.index==location){
                    pq.poll();
                    break;
                }
                //만약 찾는게 아니면
                else{
                    pq.poll();
                }
            }
            //다시 넣는다.
            else{
                q.offer(q.poll());
            }
        }
        return answer=count;
    }
}
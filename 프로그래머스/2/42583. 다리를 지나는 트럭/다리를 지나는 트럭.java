import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int passedTruck = 0;
        int nextIndex = 0;
        int weightOnBridge = 0;
        int numOnBridge = 0;
        int time=0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<bridge_length; i++){
            q.offer(0);
        }
        while(true){
            
            time++;
            //트럭 빼기
            if(!q.isEmpty()){
                int poll = q.poll();
                //트럭이면
                if(poll!=0){
                    weightOnBridge-=poll;
                    numOnBridge-=poll;
                    passedTruck++;
                }
            }
            if(passedTruck==truck_weights.length){
                break;
            }
            //트럭 넣기
            //넣을 수 있음
            if(nextIndex<truck_weights.length){
                int nextTruckWeight = truck_weights[nextIndex];
                if(weight>=weightOnBridge+nextTruckWeight){
                q.offer(nextTruckWeight);
                weightOnBridge+=nextTruckWeight;
                numOnBridge++;
                nextIndex++;
            }
                //넣을 수 없음
                else{
                    q.offer(0);
                }    
            }
        }
        return time;
    }
}
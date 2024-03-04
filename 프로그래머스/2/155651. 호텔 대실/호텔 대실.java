import java.util.*;
class Solution {
    public int strToInt(String time){
        String[] strArr=time.split(":");
        return Integer.parseInt(strArr[0])*60+Integer.parseInt(strArr[1]);
    }
    public int solution(String[][] book_time) {
        int[][] bookTimeInt = new int[book_time.length][2];
        for(int i = 0; i<book_time.length; i++){
            for(int j = 0; j<2; j++){
                bookTimeInt[i][j]=strToInt(book_time[i][j]);
            }
        }
        int count=0;
        Arrays.sort(bookTimeInt, (o1,o2)->o1[0]-o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<book_time.length; i++){
            if(!pq.isEmpty()){
                int endedTime = pq.peek();
                if(endedTime<=bookTimeInt[i][0]){
                    pq.poll();
                    pq.offer(bookTimeInt[i][1]+10);
                }
                else{
                    pq.offer(bookTimeInt[i][1]+10);
                    count++;
                }
            }else{
                pq.offer(bookTimeInt[i][1]+10);
                count++;
            }
        }
        int answer = count;
        return answer;
    }
}
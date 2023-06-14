import java.util.*;
class Solution {
    static class Task implements Comparable<Task>{
        int start;
        int weight;
        Task(int start,int weight){
            this.start=start;
            this.weight=weight;
        }
        @Override
        public int compareTo(Task t2){
            return Integer.compare(this.weight,t2.weight);
        }
        @Override
        public String toString(){
            return "start : "+start+", weight : "+weight;
        }
    }
    static PriorityQueue<Task> pq = new PriorityQueue<>();
    //a는 마지막 넣은 인덱스를 의미
    static int time,N,jobidx,count,result;
    
    public int solution(int[][] jobs) {
        time=0;
        //이것을 넣어야할 차례
        jobidx=0;
        //작업을 처리한 갯수
        count=0;
        N = jobs.length;
        Arrays.sort(jobs,new Comparator<int[]>(){
            @Override
            public int compare(int[] t1, int[] t2){
                return Integer.compare(t1[0],t2[0]);
            }
        });
       while(count<N){
               while(jobidx<N&&jobs[jobidx][0]<=time){
                   pq.offer(new Task(jobs[jobidx][0],jobs[jobidx][1]));
                   jobidx++;
           }
           if(pq.isEmpty()){
               time++;
           }
           else{
               Task cur= pq.poll();
               time=time+cur.weight;
               result+=time-cur.start;
               count++;
           }
       }
        int answer=result/N;
        return answer;
    }
}
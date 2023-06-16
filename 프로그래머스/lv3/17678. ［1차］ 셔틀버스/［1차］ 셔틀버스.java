import java.util.*;

class Solution {
    ArrayList<Integer>[] list;
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        int[] timetableint = new int[timetable.length];
        for(int i = 0; i< timetable.length; i++){
            String tmp = timetable[i];
            int tmp2 = Integer.parseInt(tmp.substring(0,2))*60+Integer.parseInt(tmp.substring(3,5));
            timetableint[i]=tmp2;
        }
        list = new ArrayList[n];
        for(int i = 0; i<n; i++){
            list[i] = new ArrayList<>();
        }
        //버스 갯수
        
        for(int i = 0; i<timetableint.length; i++){
            for(int j = 0; j<n; j++){
                int time = 9*60+ t*j;
                //차에 탈수있을때
                if(timetableint[i]<=time){
                    //차에 탄다.
                    if(list[j].size()<m){
                        list[j].add(timetableint[i]);
                        break;
                    }
                    //차가 꽉찼으면 다음차 기다린다.
                    else{
                        continue;
                    }
                }
            }
        }
        int result = 0;
        //막차가 꽉찼으면 마지막사람보다 1분 빨라야함
        if(list[n-1].size()==m){
            result=list[n-1].get(m-1)-1;
        }
        //막차가 꽉안찼으면
        //막차시간에 도착해도 된다.
        else{
            result= 9*60+ t*(n-1);
        }
        // System.out.println(Arrays.toString(timetableint));
        // System.out.println(Arrays.toString(list));
        String answer = ""+(result/60>9?result/60:"0"+result/60)+":"+(result%60>9?result%60:"0"+result%60);
        return answer;
    }
}
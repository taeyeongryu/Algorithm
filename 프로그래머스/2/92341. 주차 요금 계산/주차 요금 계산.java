import java.util.*;

class Solution {
    class NumberFees implements Comparable<NumberFees>{
        String number;
        int fees;
        
        NumberFees(String number, int fees){
            this.number = number;
            this.fees = fees;
        }
        @Override
        public int compareTo(NumberFees numberFees){
            return this.number.compareTo(numberFees.number);
        }
        
    }
    int basicTime;
    int basicFees;
    int unitTime;
    int unitFees;
    //번호, 시간(입력 받은 것 그대로)
    Map<String,String> recordMap = new HashMap<>();
    //번호, 금액
    Map<String,Integer> resultMap = new HashMap<>();
    Map<String,Integer> totalTimeMap = new HashMap<>();
    
    int calcurateTime(String inTime, String outTime){
        int inTimeInt = timeChangeToInt(inTime);
        int outTimeInt = timeChangeToInt(outTime);
        return outTimeInt-inTimeInt;
    }
    
    int calcurateFees(int minutes){
        //기본요금만 낼 때
        if(minutes<=basicTime){
            return basicFees;
        }else{
            //추가 요금 낼 때
            return basicFees+((minutes-basicTime)/unitTime)*unitFees+((minutes-basicTime)%unitTime==0?0:unitFees);
        }
    }
    
    int timeChangeToInt(String strTime){
        
        int result = 0;
        result+=600*(strTime.charAt(0)-'0');
        result+=60*(strTime.charAt(1)-'0');
        result+=10*(strTime.charAt(3)-'0');
        result+=(strTime.charAt(4)-'0');
        return result;
    }
    public int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFees = fees[1];
        unitTime = fees[2];
        unitFees = fees[3];
        for(String tmp : records){
            String time = tmp.substring(0,5);
            String number = tmp.substring(6,10);
            String inAndOut = tmp.substring(11,tmp.length());
           
            if(inAndOut.equals("IN")){
                recordMap.put(number,time);
            }else{
                
                String inTime=recordMap.get(number);
                
                recordMap.remove(number);
                
                //시간 차이 구한다.
                int disTime = calcurateTime(inTime,time);
                
                
                //시간 맵에 더한다.
                if(totalTimeMap.containsKey(number)){
                    totalTimeMap.put(number,totalTimeMap.get(number)+disTime);
                }else{
                    totalTimeMap.put(number,disTime);
                }
            }
        }
        for(String keys : recordMap.keySet()){
            String inTime = recordMap.get(keys);
            int disTime = calcurateTime(inTime,"23:59");
            // System.out.println(disTime);
                //시간 맵에 더한다.
            if(totalTimeMap.containsKey(keys)){
                totalTimeMap.put(keys,totalTimeMap.get(keys)+disTime);
            }else{
                totalTimeMap.put(keys,disTime);
            }
        }
        
        List<NumberFees> list = new ArrayList<>();
        for(String number : totalTimeMap.keySet()){
            int disTime = totalTimeMap.get(number);
            int feess = calcurateFees(disTime);
            list.add(new NumberFees(number,feess));
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i = 0; i<list.size(); i++){
            answer[i] = list.get(i).fees;
        }
        
        return answer;
    }
}
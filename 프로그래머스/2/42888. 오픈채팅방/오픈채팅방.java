import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<String[]> strList = new ArrayList<>();
        StringTokenizer st = null;
        for(int i = 0; i < record.length; i++){
            st = new StringTokenizer(record[i]);
            String oper = st.nextToken();
            
            String userId = "";
            String nickname = "";
            if(oper.equals("Enter")){
                userId = st.nextToken();
                nickname = st.nextToken();
                //map에 넣고
                map.put(userId,nickname);
                strList.add(new String[]{oper,userId});
            }else if(oper.equals("Leave")){
                userId = st.nextToken();
                strList.add(new String[]{oper,userId});
            }else{
                userId = st.nextToken();
                nickname = st.nextToken();
                //map바꾼다.
                map.put(userId,nickname);
            }
        }
        
        String[] answer = new String[strList.size()];
        for(int i = 0; i < strList.size(); i++){
            String oper = strList.get(i)[0];
            String userId = strList.get(i)[1];
            String nickname = map.get(userId);
            if(oper.equals("Enter")){
                answer[i] = nickname+"님이 들어왔습니다.";
            }else{
                answer[i] = nickname+"님이 나갔습니다.";
            }
        }
        return answer;
    }
}
import java.util.*;
class Solution {
    boolean[] isPrime = new boolean[10000000];
    boolean[] visited;
    Set<Integer> set = new HashSet<>();
    
    public void dfs(int index, String num, String numbers){
        //끝까지 검사 끝
        if(index==numbers.length()){
            if(num.equals("")){
                return;
            }
            else{
                int result = Integer.parseInt(num);
                set.add(result);
                return;
            }
        }
        for(int i = 0; i < numbers.length(); i++){
            if(!visited[i]){
                visited[i]=true;
                dfs(index+1,num+numbers.charAt(i),numbers);
                dfs(index+1,num,numbers);
                visited[i]=false;
            }
        }
    }
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        Arrays.fill(isPrime,true);
        isPrime[0]=false;
        isPrime[1]=false;
        for(int i = 2; i<isPrime.length; i++){
            if(!isPrime[i]){
                continue;
            }
            for(int j = 2*i; j < isPrime.length; j+=i){
                isPrime[j]=false;
            }
        }
        dfs(0,"",numbers);
        int answer = 0;
        for(Integer num : set){
            if(isPrime[num]){
                answer++;
            }
        }    
        return answer;
    }
}
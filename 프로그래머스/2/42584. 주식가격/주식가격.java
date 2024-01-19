import java.util.*;
class Solution {
    class Price{
        int time;
        int price;
        Price(int time, int price){
            this.time = time;
            this.price = price;
        }
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Price> stack = new Stack<>();
        for(int i = 0; i<prices.length; i++){
            
            int curPrice = prices[i];
            
            if(stack.isEmpty()){
                stack.add(new Price(i,curPrice));
                continue;
            }
            while(!stack.isEmpty()){
                Price peekPrice = stack.peek();
                if(peekPrice.price<=curPrice){
                    break;
                }else{
                    Price popPrice = stack.pop();
                    answer[popPrice.time]=i-popPrice.time;
                }
            }
            stack.add(new Price(i,curPrice));   
        }
        while(!stack.isEmpty()){
            Price popPrice = stack.pop();
            answer[popPrice.time] = prices.length-popPrice.time-1;
        }
        return answer;
    }
}
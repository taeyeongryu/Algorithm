import java.util.*;
class Solution {
    char[] oper = {'*','+','-'};
    boolean[] check = new boolean[3];
    int[] seq = new int[3];
    public void permu(int depth){
        if(depth==3){
            // System.out.println(Arrays.toString(seq));
            // System.out.println(calculate());
            max = Math.max(max,calculate());
            return;
        }
        for(int i = 0; i<3; i++){
            if(check[i]){
                continue;
            }
            check[i]=true;
            seq[depth]=i;
            permu(depth+1);
            check[i]=false;
        }
    }
    public Long calculate(){
        setting();
        for(int i = 0; i<3; i++){
            for(int j = 0; j<operList.size(); j++){
                if(operList.get(j)==oper[seq[i]]){
                    long left = numberList.get(j);
                    long right = numberList.get(j+1);
                    long tmp = 0;
                    //곱
                    if(seq[i]==0){
                        tmp = left*right;
                    }
                    //덧
                    else if(seq[i]==1){
                        tmp = left+right;
                    }
                    //마
                    else{
                        tmp = left-right;
                    }
                    numberList.remove(j+1);
                    numberList.remove(j);
                    numberList.add(j,tmp);
                    operList.remove(j);
                    j--;
                }
            }
        }
        return Math.abs(numberList.get(0));
    }
    List<Long> numberList;
    List<Character> operList;
    String staticString;
    Long max = Long.MIN_VALUE;
    public void setting(){
        numberList = new ArrayList<>();
        operList = new ArrayList<>();
        int leftIndex = 0;
        for(int i = 0; i<staticString.length(); i++){
            if(staticString.charAt(i)=='*'||staticString.charAt(i)=='+'||staticString.charAt(i)=='-'){
                numberList.add(Long.parseLong(staticString.substring(leftIndex,i)));
                operList.add(staticString.charAt(i));
                leftIndex=i+1;
            }
            if(i==staticString.length()-1){
                numberList.add(Long.parseLong(staticString.substring(leftIndex,staticString.length())));
            }
        }
    }
    public long solution(String expression) {
        staticString = expression;
        permu(0);
        
        return max;
    }
}
import java.util.*;
class Solution {
    // List<int[]>[][][] = new LinkedList[16][3][2];
    public List<int[]> find(int n, int start, int end){
        List<int[]> list=new LinkedList<>();
        if(n==1){
            list.add(new int[]{start,end});
            return list;
        }
        int another = 0;
        for(int i = 1; i<=3; i++){
            if(i!=start&&i!=end){
                another=i;
            }
        }
        list.addAll(find(n-1,start,another));
        list.add(new int[]{start,end});
        list.addAll(find(n-1,another,end));
        return list;
    }
    public int[][] solution(int n) {
        List<int[]> list = find(n,1,3);
        int[][] answer = new int[list.size()][2];
        for(int i =0; i< list.size(); i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
}
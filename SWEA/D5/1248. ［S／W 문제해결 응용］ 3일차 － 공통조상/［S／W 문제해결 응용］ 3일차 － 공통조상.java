import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    static int V,E,num1,num2;
    static int[][] arr;
    static Set<Integer> set1 = new HashSet<>();
    static Set<Integer> set2 = new HashSet<>();
    static int findParent(int num1, int num2){
//        int tmp1 = num1;
//        int tmp2 = num2;
        if(set2.contains(num1)){
            return num1;
        }else{
            set1.add(num1);
        }
        if(set1.contains(num2)){
            return num2;
        }else{
            set2.add(num2);
        }
        return findParent(arr[num1][0], arr[num2][0]);
    }
    static int countChild(int num){
        int count = 1;
        if(arr[num][1]!=0){
            count+=countChild(arr[num][1]);
        }
        if(arr[num][2]!=0){
            count+=countChild(arr[num][2]);
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <=T; tc++) {
            set1.clear();
            set2.clear();

            V = sc.nextInt();
            E = sc.nextInt();
            num1 = sc.nextInt();
            num2 = sc.nextInt();
            arr = new int[V + 1][3];
            for (int i = 0; i <E ; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();
                arr[child][0]=parent;
                //왼쪽자식 비었으면 넣는다.
                if(arr[parent][1]==0){
                    arr[parent][1] = child;
                }
                //왼쪽 자식 있으면 오른쪽에 넣는다.
                else{
                    arr[parent][2] = child;
                }
            }
        int result1 = findParent(num1,num2);
        int result2 = countChild(result1);
        System.out.printf("#%d %d %d%n",tc,result1,result2);
        }
    }
}
import java.util.Scanner;

public class Solution {
    static int[][] childindex;
    static char[] tree;
    static StringBuilder sb;
    static void inOrder(int start){
        if(childindex[start][0]!=0){
            inOrder(childindex[start][0]);
        }
        sb.append(tree[start]);
        if (childindex[start][1]!=0){
            inOrder(childindex[start][1]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <=10 ; tc++) {
            int N = sc.nextInt();
            tree = new char[N + 1];
            childindex = new int[N + 1][2];
            for (int i = 1; i <=N; i++) {
                int parent = sc.nextInt();
                char cha = sc.next().charAt(0);
                if(i*2<=N){
                    int left = sc.nextInt();
                    childindex[parent][0] = left;
                }
                if (i*2+1<=N){
                    int right = sc.nextInt();
                    childindex[parent][1] = right;
                }
                tree[parent] = cha;
            }

            sb = new StringBuilder();
            inOrder(1);
            System.out.printf("#%d %s%n",tc,sb.toString());
        }

    }
}
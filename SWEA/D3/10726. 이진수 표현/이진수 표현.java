import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int compareN = (1<<N)-1;
            if((M&compareN)==compareN){
                System.out.printf("#%d %s%n",tc,"ON");
            }else {
                System.out.printf("#%d %s%n",tc,"OFF");
            }
        }
    }
}
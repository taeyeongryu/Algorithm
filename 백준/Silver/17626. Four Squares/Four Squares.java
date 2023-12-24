import java.util.Scanner;

public class Main {

    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        dp = new int[N+1];
        dp[1]=1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = 4;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}
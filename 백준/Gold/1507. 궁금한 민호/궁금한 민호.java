import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map,newmap;
    static boolean[][] check;
    public static void main(String[] args) {
        final int MAX = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N+1][N+1];
        newmap = new int[N+1][N+1];
        check = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N ; j++) {
                map[i][j] = sc.nextInt();
                newmap[i][j]=map[i][j];
            }
        }
        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i <N+1 ; i++) {
                for (int j = 1; j <N+1 ; j++) {
                    if(i==j||i==k||j==k){
                        continue;
                    }
                    if(map[i][j]>map[i][k]+map[k][j]){
                        System.out.println(-1);
                        return;
                    }
                    if(map[i][j]==map[i][k]+map[k][j]){
                        newmap[i][j] = MAX;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <=N ; i++) {
            for (int j = 1; j <=N ; j++) {
                if(i!=j&&newmap[i][j]!=MAX&&!check[i][j]){
                    answer += newmap[i][j];
                    check[i][j]=check[j][i]=true;
                }
            }
        }
        System.out.println(answer);
    }
}
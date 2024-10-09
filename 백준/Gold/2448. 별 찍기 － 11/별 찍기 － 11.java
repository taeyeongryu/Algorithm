import java.util.Scanner;

public class Main {

    static char[][] starMap;
    //꼭짓점 별의 위치가 r,c에 들어간다.


    static int N;
    static int K;
    static void appendStar(int r, int c, int height, int wide){
        if(height==3){
            starMap[r][c]='*';
            starMap[r + 1][c - 1] = '*';
            starMap[r + 1][c + 1] = '*';
            starMap[r + 2][c] = '*';
            starMap[r + 2][c + 1] = '*';
            starMap[r + 2][c + 2] = '*';
            starMap[r + 2][c - 1] = '*';
            starMap[r + 2][c - 2] = '*';
            return;
        }
        appendStar(r , c, height / 2, wide / 2);
        appendStar(r + height / 2, c - (wide / 4 + 1), height / 2, wide / 2);
        appendStar(r + height / 2, c + (wide / 4 + 1), height / 2, wide / 2);
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < 10; i++) {
            if(Math.pow(2,i)*3==N){
                K = i;
                break;
            }
        }
        starMap = new char[N][2*N-1];
        appendStar(0, (2 * N - 1) / 2, N, 2 * N - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <starMap.length ; i++) {
            for (int j = 0; j < starMap[0].length; j++) {
                sb.append(starMap[i][j] == '*' ? starMap[i][j] : ' ');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
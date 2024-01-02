import java.util.Scanner;

public class Main {
    //기본이 3이 들어가야 한다.
//    static void printThree(int space){
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i <space; i++) {
//            sb.append(" ");
//        }
//        sb.append("*\n");
//        for (int i = 0; i <space-1; i++) {
//            sb.append(" ");
//        }
//        sb.append("* *\n");
//        for (int i = 0; i <space-2; i++) {
//            sb.append(" ");
//        }
//        sb.append("*****");
//        System.out.print(sb.toString());
//    }
    static char[][] starMap;
    //꼭짓점 별의 위치가 r,c에 들어간다.
    static void print(int r, int c, int N){
        if(N==3){
            starMap[r][c] = '*';
            starMap[r + 1][c - 1] = '*';
            starMap[r + 1][c + 1] = '*';
            starMap[r + 2][c - 1] = '*';
            starMap[r + 2][c + 1] = '*';
            starMap[r + 2][c] = '*';
            starMap[r + 2][c - 2] = '*';
            starMap[r + 2][c + 2] = '*';
            return;
        }
        else{
            print(r,c,N/2);
            print(r+N/2,c-(N/2),N/2);
            print(r+N/2,c+(N/2),N/2);
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        starMap = new char[N][2*N - 1];
        for (int i = 0; i <starMap.length ; i++) {
            for (int j = 0; j < starMap[0].length; j++) {
                starMap[i][j] = ' ';
            }
        }
        print(0,N-1,N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <starMap.length ; i++) {
            for (int j = 0; j < starMap[0].length; j++) {
                sb.append(starMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
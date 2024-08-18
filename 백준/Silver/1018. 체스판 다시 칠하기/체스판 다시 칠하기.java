import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static char[][] board;
    //시작위치
    static int countChange(int x, int y){

        //시작위치를 보고 8,8을 넘어가는지 아닌지 chech 해야함
        if (x + 8 > N || y + 8 > M) {
            return -1;
        }
        else{
            //첫번째가 백일 때
            int countA = 0;
            for (int i = x; i < x + 8; i++) {
                for (int j = y; j < y + 8; j++) {
                    int div = (i + j) % 2;
                    //백이어야 함
                    if(div==0){
                        if(board[i][j]=='B'){
                            countA++;
                        }
                    }
                    //흑이어야함
                    else{
                        if(board[i][j]=='W'){
                            countA++;
                        }
                    }
                }
            }
            int countB = 0;
            //첫번째가 흑일때
            for (int i = x; i < x + 8; i++) {
                for (int j = y; j < y + 8; j++) {
                    int div = (i + j) % 2;
                    //흑이어야 함
                    if(div==0){
                        if(board[i][j]=='W'){
                            countB++;
                        }
                    }
                    //백이어야함
                    else{
                        if(board[i][j]=='B'){
                            countB++;
                        }
                    }
                }
            }
            return Integer.min(countA, countB);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }
//        System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int count = countChange(i, j);
                if(count==-1){
                    continue;
                }else{
                    min = Math.min(min, count);
                }
            }
        }
        System.out.println(min);
    }
}
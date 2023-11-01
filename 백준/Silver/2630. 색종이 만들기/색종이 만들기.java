import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int white;
    static int black;
    static int sameColor(int r, int c, int dist){
        int firstColor = map[r][c];
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
                if(map[r+i][c+j]!=firstColor){
                    //색이 다르면
                    return 2;
                }
            }
        }
        //흰색은 0 검은색은 1
        return firstColor == 0 ? 0 : 1;
    }
    static void check(int r, int c ,int dist){

        int color = sameColor(r, c, dist);
        if(color !=2){
            //흰색이면
            if (color==0){
                white++;
            }
            //검은색이면
            else{
                black++;
            }
            return;
        }

        int halfDist = dist / 2;
        check(r, c, halfDist);
        check(r + halfDist, c + halfDist, halfDist);
        check(r + halfDist, c, halfDist);
        check(r, c + halfDist, halfDist);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        check(0,0,N);
        bw.append(white + "\n");
        bw.append(black + "");
        bw.close();
        br.close();

    }
}
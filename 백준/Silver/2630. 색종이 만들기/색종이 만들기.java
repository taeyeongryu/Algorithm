import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int oneCount;
    static int zeroCount;
    static  int check (int r, int c, int length){
        int first = map[r][c];
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                if (first != map[i][j]) {
                    return -1;
                }
            }
        }
        return first;
    }
    static void dfs(int r, int c, int length){
        if(length==1){
            if (map[r][c] == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
            return;
        }
        //도구 같은 색이면
        int color = check(r, c, length);
        if(color!=-1){
            if(color==0){
                zeroCount++;
            }else{
                oneCount++;
            }
        }else{
            dfs(r, c, length / 2);
            dfs(r + length / 2, c, length / 2);
            dfs(r, c + length / 2, length / 2);
            dfs(r + length / 2, c + length / 2, length / 2);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, N);
        bw.append(zeroCount + "\n");
        bw.append(oneCount + "");
        bw.close();

    }
}
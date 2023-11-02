import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,R,C;
//    static int[][] map;

    static int result;

    static boolean check(int r, int c, int n){

        if (r <= R && R < r+n && c <= C && C < c+n) {
            return true;
        }
        return false;
    }
    static  void search(int r, int c, int n){
//            System.out.println("r : " + r + ", c : " + c);
            if(n==1){
                return;
            }else{
                int dist = n / 2;
                if (check(r, c, dist)) {
                    search(r, c, dist);
                } else if (check(r, c + dist, dist)) {
                    result += dist * dist;
                    search(r, c + dist, dist);
                } else if (check(r + dist, c, dist)) {
                    result += dist * dist * 2;
                    search(r + dist, c, dist);

                } else {
                    result += dist * dist * 3;
                    search(r + dist, c + dist, dist);
                }
            }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int pow = (int) Math.pow(2, N);
        search(0, 0, pow);
        System.out.println(result);
//        System.out.println(map[R][C]);
    }
}
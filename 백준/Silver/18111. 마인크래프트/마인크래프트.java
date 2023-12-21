import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, B,min,max,minTime=Integer.MAX_VALUE,minHeight;
    static int[][] map;

    static void goToHeight(int height) {
        int time=0;
        int block = 0;
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //블록을 쌓는 경우
                if (map[i][j] < height) {
                    //블록이 존재하면
                    time += (height - map[i][j]);

                    block += (height - map[i][j]);
                }
                //블록을 제거하는 경우
                else if (map[i][j] > height) {
                    time += (map[i][j] - height) * 2;
                    block -= (map[i][j] - height);
                }
            }
        }
        if(block>B){
            flag = false;
        }
        if (flag) {
            if(minTime >= time){
                minTime = time;
                minHeight = height;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        if(min!=max){
            for (int height = min; height <=max ; height++) {
                goToHeight(height);
            }
        }else{
            minTime = 0;
            minHeight = max;
        }
        bw.append(minTime + " " + minHeight);
        bw.close();
        br.close();
    }
}
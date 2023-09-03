import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] map;
    static int ans, N;
    static final int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}, dc = {0, 0, -1, 1, 1, -1, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j <N ; j++) {
                    char charAt = str.charAt(j);
                    //만약 빈공간이라면
                    if(charAt=='.'){
                        map[i][j] = -1;
                    }else {
                        map[i][j]=-2;
                    }
                }
            }
            solve();
            System.out.printf("#%d %d%n",tc,ans);
        }
    }
    private static void solve(){
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                if (map[i][j]!=-1) continue;
                if (isZero(i, j)) {
                    click(i, j);
                    ans++;
                }
            }
        }
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                if(map[i][j]==-1) ans++;
            }
        }
    }
    //zero인지 check후에 이 메서드 실행
    private static void click(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        map[r][c] = 0;
        while (!q.isEmpty()) {

            int[] cur = q.poll();
//            map[cur[0]][cur[1]] = 0;
            for (int i = 0; i < 8; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] != -1) {
                    continue;
                }
                if (isZero(nr, nc)) {
                    q.add(new int[]{nr, nc});
                }
                map[nr][nc] = 0;
            }
        }
    }
    private static boolean isZero(int r, int c){
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }
            if(map[nr][nc]==-2){
                return false;
            }
        }
        return true;
    }
}
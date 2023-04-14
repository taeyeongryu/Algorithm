import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int width, height, M, ans;
    private static int[][] map;
    private static boolean isFinish = false;
 
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 연결된 가로선의 갯수
        height = Integer.parseInt(st.nextToken());
 
        map = new int[height + 1][width + 1];
        for (int y = 0; y < M; y++) {
            st = new StringTokenizer(br.readLine());
 
            // a 높이에서 b번과 b+1번 세로선을 연결한다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;     // 1 : 우측으로 이동.
            map[a][b + 1] = 2; // 2 : 좌측으로 이동.
        }
 
        // 추가할 가로선의 갯수를 미리 정해놔야 탐색 종료 조건으로 걸 수 있다.
        // 아래 반복문에서 i는 추가할 가로선의 수.
        for (int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 1, 0);
            if (isFinish) break;
        }
 
        System.out.println((isFinish ? ans : -1));
        br.close();
    }
 
    // addHorizontalLineNumber : 추가한 가로선의 갯수 (3개가 넘어가면 더이상의 탐색이 무의미.)
    private static void dfs(int x, int y, int addHorizontalLineNumber) {
        if (isFinish) return;
        if (ans == addHorizontalLineNumber) {
            if (check()) isFinish = true;
            return;
        }
 
        for (int i = y; i <= height; i++) {
            for (int j = x; j < width; j++) {
                // 가로선 두 개가 연속으로 놓여질 수 없기 때문에 가로선을 추가하기 전에 연결된 가로선이 있는지 확인한다.
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    // 가로선을 추가한다.
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
 
                    dfs(1, 1, addHorizontalLineNumber + 1);
 
                    // 추가했던 가로선을 다시 제거한다. (백트래킹)
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }
 
    // i번으로 출발해서 i번으로 도착하는지 확인한다.
    private static boolean check() {
        for (int i = 1; i <= width; i++) {
            int nx = i;
            int ny = 1;
 
            while (ny <= height) {
                if (map[ny][nx] == 1) nx++; // 우측으로 이동
                else if (map[ny][nx] == 2) nx--; // 좌측으로 이동
                ny++; // y축+1칸 이동한다. (아래로 이동)
            }
 
            if (nx != i) return false; // i번으로 출발해서 i번으로 도착하지 않는게 하나라도 있다면 리턴.
        }
 
        return true;
    }
}
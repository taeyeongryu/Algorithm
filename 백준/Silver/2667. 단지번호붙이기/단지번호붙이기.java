import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] visited;
    static boolean[][] map;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static List<Integer> list = new ArrayList<>();
    static void bfs(int r, int c){
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        visited[r][c]=true;
        count++;
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC]) {
                    visited[nextR][nextC]=true;
                    count++;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }
        list.add(count);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map =new boolean[N][N];
        visited =new boolean[N][N];
        for (int i = 0; i<N; i++){
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);
                if(ch=='1'){
                    map[i][j]=true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]&&map[i][j]){
                    bfs(i, j);
                }
            }
        }
        Collections.sort(list);
        bw.append(list.size() + "\n");
        for (Integer i : list) {
            bw.append(i + "\n");
        }
        bw.close();
        br.close();
    }
}
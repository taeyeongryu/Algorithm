import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> q = new LinkedList<>();
    static List<Node> list = new ArrayList<>();
    static boolean bfs(){
        boolean flag = false;
        list.clear();
        int count = 0;
        int total = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            list.add(cur);
            int r = cur.r;
            int c = cur.c;
            count++;
            total+=map[r][c];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N)continue;
                if(visited[nr][nc]) continue;
                int cha = Math.abs(map[nr][nc] - map[r][c]);
                if(!(L<=cha&&cha<=R))continue;

                visited[nr][nc]=true;
                flag=true;
                q.add(new Node(nr, nc));
            }
        }
        int mean = total/count;
        for (Node node : list) {
            map[node.r][node.c]=mean;
        }
        return flag;
    }
    static boolean check(){
        int first = map[0][0];
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                if(first!=map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i <N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int day = 0;
        while(true){
            boolean flag = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <N ; j++) {
                    if(!visited[i][j]){
                        visited[i][j] = true;
                        q.add(new Node(i, j));
                        if(bfs())flag=true;
                    }
                }
            }
            //인구 이동이 일어나면 day++하고 다음
            if(flag){
                day++;
            }else{
                break;
            }
        }
        System.out.println(day);
    }
}
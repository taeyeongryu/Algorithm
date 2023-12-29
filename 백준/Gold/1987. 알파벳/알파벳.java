import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int r;
        int c;
        int cnt;
        boolean[] visited;

        public Node(int r, int c, int cnt, boolean[] visited) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", cnt=" + cnt +
                    ", visited=" + Arrays.toString(visited) +
                    '}';
        }
    }
    static int R, C,result;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static char[][] map;

//    static void bfs(){
//        Queue<Node> q = new LinkedList<>();
//        boolean[] visited = new boolean[26];
//        visited[map[0][0] - 'A'] = true;
//        q.offer(new Node(0, 0, 1, visited));
//        while (!q.isEmpty()) {
//            Node cur = q.poll();
//            int curR = cur.r;
//            int curC = cur.c;
//            int curCnt = cur.cnt;
//            boolean[] curVisited = cur.visited;
//            Arrays.toString(curVisited);
//            result = curCnt;
//
//            for (int i = 0; i < 4; i++) {
//                int nr = curR + dr[i];
//                int nc = curC + dc[i];
//                if(nr<0||nc<0||nr>=R||nc>=C){
//                    continue;
//                }
//                if(curVisited[map[nr][nc]-'A']){
//                    continue;
//                }
//
//                //기존 방문 배열 복사해서 새로운 배열 만든다.
//                boolean[] newVisited = new boolean[26];
//                for (int i1 = 0; i1 < newVisited.length; i1++) {
//                    newVisited[i1] = curVisited[i1];
//                }
//
//                newVisited[map[nr][nc] - 'A'] = true;
//                q.add(new Node(nr, nc, curCnt + 1, newVisited));
//            }
//        }
//    }
    //이 메서드의 의미는 r,c에서 검사를 끝내고 거기로 보낸다는 의미이다.
    //visited,cnt에는 이미 r,c의 결과가 반영되어 있다.
    static void dfs(int r, int c, int cnt, boolean[] visited){
        result = Math.max(cnt, result);
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            //범위 벗어나면 더 이상 앞으로 가지 못한다.
            if(nr<0||nc<0||nr>=R||nc>=C){
                continue;
            }
            //이미 방문한적이 있던 알파벳이라면 가지 못한다.
            if(visited[map[nr][nc]-'A']){
                continue;
            }
            visited[map[nr][nc] - 'A'] = true;
            dfs(nr, nc, cnt + 1, visited);
            visited[map[nr][nc] - 'A'] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        boolean[] visited = new boolean[26];
        visited[map[0][0] - 'A']=true;
        dfs(0, 0, 1, visited);

        bw.append(result + "");
        bw.close();
        br.close();

    }
}
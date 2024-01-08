import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int index;
        int time;
        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    static boolean[] visited;
    static int[] time;
    static int N, K, cnt;
    static boolean flag;
    static void bfs(){
        //최소 거리가 찾아졌는지 확인하는 변수
        flag = false;
        Queue<Node> q = new LinkedList<>();
        //방문여부
        visited[N] = true;
        //이동횟수
        time[N] = 0;
        q.offer(new Node(N, 0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            int curIndex = cur.index;
            int curTime = cur.time;
            if (flag && curTime > time[K]) {
                continue;
            }
            if (curIndex == K) {
                flag = true;
                if (curTime <=time[K]) {
                    cnt++;
                }
                continue;
            }
            int nextIndex1 = curIndex + 1;
            if (isRange(nextIndex1)&&!isVisited(curIndex, nextIndex1)) {
                visited[nextIndex1]=true;
                time[nextIndex1] = curTime + 1;
                q.offer(new Node(nextIndex1, curTime + 1));
            }

            int nextIndex2 = curIndex - 1;
            if (isRange(nextIndex2)&&!isVisited(curIndex, nextIndex2) ) {
                visited[nextIndex2]=true;
                time[nextIndex2] = curTime + 1;
                q.offer(new Node(nextIndex2, curTime + 1));
            }

            int nextIndex3 = curIndex * 2;
            if (isRange(nextIndex3)&&!isVisited(curIndex, nextIndex3)) {
                visited[nextIndex3]=true;
                time[nextIndex3] = curTime + 1;
                q.offer(new Node(nextIndex3, curTime + 1));
            }
        }
    }

    private static boolean isRange(int nextIndex) {
        return 0 <= nextIndex && nextIndex <= 200000;
    }

    private static boolean isVisited(int curIndex,int nextIndex) {
        if(!visited[nextIndex]){
            return false;
        } else if (time[nextIndex] >= time[curIndex] + 1) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200001];
        time = new int[200001];

        bfs();

        bw.append(time[K] + "\n");
        bw.append(cnt + "");
        bw.close();
        br.close();

    }
}
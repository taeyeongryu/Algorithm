import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static final int LOG = 21;
    static int[] d;
    static int[][] parent;
    static boolean[] check,visited;
    static int N;
    static List<Integer>[] list;
    static void bfs2(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int cur = q.poll();
            d[cur] = d[parent[cur][0]] + 1;

            for (int i = 0; i <list[cur].size() ; i++) {
                int next = list[cur].get(i);
                    q.add(next);
            }
        }
    }
    static void setParent(){
        bfs2();
        for (int i = 1; i <LOG ; i++) {
            for (int j = 1; j < N+1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
    static int lca(int a, int b){
        int count = 0;
        if(d[a]>d[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = LOG-1; i >=0 ; i--) {
            if (d[b] - d[a] >= (1 << i)) {
                b = parent[b][i];
                count += (1 << i);

            }
        }
        if (a == b) {
            return count;
        }
        for (int i = LOG-1; i >=0 ; i--) {
            if (parent[a][i]!=parent[b][i]) {
                count += (2 * (1 << i));
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        count+=2;
        return count;
    }
    static long bfs(){
        long count = 0;
        Queue <Integer> q = new LinkedList<>();
        visited[1]=true;
        q.add(1);
        int prev = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            count += lca(prev, cur);
            Collections.sort(list[cur]);
            for (int i = 0; i <list[cur].size() ; i++) {
                int next = list[cur].get(i);
                if (!visited[next]) {
                    visited[next]=true;
                    q.add(next);
                }
            }
            prev = cur;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            list = new List[N + 1];
            for (int i = 0; i <list.length ; i++) {
                list[i] = new ArrayList<>();
            }
            parent = new int[N + 1][LOG];
            d = new int[N + 1];
            check = new boolean[N + 1];
            visited = new boolean[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 2; i <=N ; i++) {
                int parentnum = Integer.parseInt(st.nextToken());
                parent[i][0] = parentnum;
                list[parentnum].add(i);
            }
            setParent();
            long result = bfs();
            System.out.printf("#%d %d%n", tc, result);
        }
    }
}
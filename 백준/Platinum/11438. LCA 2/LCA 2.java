import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int LOG = 21;
    static int N,M;
    static int[][] parent;
    static int[] d;
    static boolean[] c;
    static List<Integer>[] list;
    static void dfs(int index, int depth){
        c[index] = true;
        d[index] = depth;

        for (int i = 0; i <list[index].size() ; i++) {
            int next = list[index].get(i);
            if(c[next]){continue;}
            parent[next][0] = index;
            dfs(next, depth + 1);
        }
    }
    static void setParent(){
        dfs(1, 0);
        for (int i = 1; i <LOG ; i++) {
            for (int j = 1; j < N+1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
    static int lca(int a, int b){
        if(d[a]>d[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = LOG-1; i >=0 ; i--) {
            if (d[b] - d[a] >= (1 << i)) {
                b = parent[b][i];
            }
        }
        if(a==b){
            return a;
        }
        for (int i = LOG-1; i >=0 ; i--) {
            if (parent[a][i]!=parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1][LOG];
        d = new int[N + 1];
        c = new boolean[N + 1];
        list = new List[N + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            list[parent].add(child);
            list[child].add(parent);
        }
        setParent();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = lca(a, b);
            bw.write(lca+"\n");
        }
        bw.close();
        br.close();
        
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static boolean[] check;
    static int[] d,parent;
    static List<Integer>[] list;
    static void dfs(int index, int depth){
        if(check[index]){return;}
        check[index]=true;
        d[index] = depth;

        for (int i = 0; i <list[index].size() ; i++) {
            int next = list[index].get(i);
            if(!check[next]){parent[next] = index;}
            dfs(next, depth + 1);
        }
    }
    static int lca(int a, int b){
        while(d[a]!=d[b]){
            if (d[a]>d[b]) {
                a=parent[a];
            }
            else {
                b=parent[b];
            }
        }
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 0; i <list.length ; i++) {
            list[i] = new ArrayList<>();
        }
        check = new boolean[N + 1];
        d = new int[N + 1];
        parent = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            list[parent].add(child);
            list[child].add(parent);
        }
        M = Integer.parseInt(br.readLine());


        dfs(1,0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = lca(a, b);
            bw.write(result+"\n");
        }
        bw.close();
        br.close();
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static char[][] tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        tree = new char[N][2];
        StringTokenizer st;
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char start = st.nextToken().charAt(0);
            for (int j = 0; j < 2; j++) {
                char ch = st.nextToken().charAt(0);
                tree[start-'A'][j] = ch;
            }
        }
//        System.out.println("Arrays.deepToString(tree) = " + Arrays.deepToString(tree));
        pre(0);
        sb.append("\n");
        mid(0);
        sb.append("\n");
        post(0);

        System.out.println(sb.toString());
    }
    static StringBuilder sb;
    static void pre(int index){
        sb.append((char) (index + 'A'));
        if(tree[index][0]!='.'){
            pre(tree[index][0]-'A');
        }
        if(tree[index][1]!='.'){
            pre(tree[index][1]-'A');
        }
    }
    static void mid(int index){
        if(tree[index][0]!='.'){
            mid(tree[index][0]-'A');
        }
        sb.append((char) (index + 'A'));

        if(tree[index][1]!='.'){
            mid(tree[index][1]-'A');
        }
    }
    static void post(int index){
        if(tree[index][0]!='.'){
            post(tree[index][0]-'A');
        }
        if(tree[index][1]!='.'){
            post(tree[index][1]-'A');
        }
        sb.append((char) (index + 'A'));
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < T; i++) {
            HashMap<String, Set<String>> map = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                String cloth = st.nextToken();
                String category = st.nextToken();
                Set<String> orDefault = map.getOrDefault(category, new HashSet<>());
                orDefault.add(cloth);
                map.put(category, orDefault);
            }
            int result = 1;
            for (String s : map.keySet()) {
                Set<String> strings = map.get(s);
                result *= (strings.size()+1);
            }

            bw.append((result - 1) + "\n");
        }
        bw.close();
        br.close();
    }
}
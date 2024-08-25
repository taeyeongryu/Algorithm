import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t <T ; t++) {
            int N = Integer.parseInt(br.readLine());
            if(N==0){
                bw.append(0+"\n");
                continue;
            }
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String cloth = st.nextToken();
                String category = st.nextToken();
                if(!map.containsKey(category)){
                    map.put(category, new ArrayList<>());
                }
                List<String> list = map.get(category);
                list.add(cloth);

            }

            int result = 1;
            Set<String> keySet = map.keySet();
            for (String category : keySet) {
                result = result * (map.get(category).size() + 1);
            }
            bw.append(result - 1 + "\n");
        }
        bw.close();
        br.close();

    }
}
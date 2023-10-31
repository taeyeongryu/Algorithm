import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> first = new HashSet<>();
        List<String> second = new ArrayList<>();
        for (int i = 0; i < N; i++){
            first.add(br.readLine());
        }
        for (int i = 0; i < M; i++){
            String tmp = br.readLine();
            if(first.contains(tmp)){
                second.add(tmp);
            }
        }
        Collections.sort(second);
        bw.append(second.size() + "\n");
        for (String s : second) {
            bw.append(s + "\n");
        }
        bw.close();
        br.close();
    }
}
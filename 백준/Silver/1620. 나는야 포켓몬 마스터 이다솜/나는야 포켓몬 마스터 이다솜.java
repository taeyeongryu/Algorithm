import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> strToInt = new HashMap<>();
        Map<Integer, String> intToStr = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            strToInt.put(str, i);
            intToStr.put(i, str);
        }
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            char c = str.charAt(0);
            //숫자다.
            if ('0' <= c && c <= '9') {
                bw.append(intToStr.get(Integer.parseInt(str)) + "\n");
            }
            //문자열이다.
            else {
                bw.append(strToInt.get(str) + "\n");
            }
        }
        bw.close();
        br.close();
    }
}
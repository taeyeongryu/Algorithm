import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int tc = 1; tc <= T; tc++) {
            int result = 0;
            HashSet<String> set = new HashSet();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < a; i++) {
                set.add(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < b; i++) {
                if (set.contains(st.nextToken())) result++;
            }
            System.out.printf("#%d %d%n",tc,result);
        }

    }
}
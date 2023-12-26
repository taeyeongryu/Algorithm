import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> resultList = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int result = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            String[] mbtiArr = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(N>=33){
                resultList.add(0);
                continue;
            }
            for (int i1 = 0; i1 < mbtiArr.length; i1++) {
                mbtiArr[i1] = st.nextToken();
            }

            for (int j = 0; j <N ; j++) {
                for (int k = j+1; k <N ; k++) {
                    for (int l = k + 1; l < N; l++) {
                        int sum = 0;
                        sum += calculate(mbtiArr[j], mbtiArr[k]);
                        sum += calculate(mbtiArr[k], mbtiArr[l]);
                        sum += calculate(mbtiArr[l], mbtiArr[j]);
                        result = Math.min(result, sum);
                    }
                }
            }
            resultList.add(result);
        }
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
        br.close();
    }

    private static int calculate(String mbti1, String mbti2) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}
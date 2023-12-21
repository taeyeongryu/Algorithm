import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,M,minButton=Integer.MAX_VALUE;
    static boolean[] numberArr;
    static int[] goToNumber;

    static void permutation(int depth){
        if(depth== goToNumber.length){
            //값 계산한다.
            //최종 버튼을 몇번 눌러야 되는지
            int resultNum = calculate();
            minButton=Math.min(minButton, resultNum);
            return;
        }
        for (int i = 0; i < numberArr.length; i++) {
            if(numberArr[i]){
                goToNumber[depth] = i;
                permutation(depth + 1);
            }
        }
    }

    private static int calculate() {
        int number = 0;
        int result = 0;
        for (int i = 0; i < goToNumber.length; i++) {
            number = number * 10 + goToNumber[i];
        }
        String str = number + "";
        result += str.length();
        return result + Math.abs(N - number);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        numberArr = new boolean[10];
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = true;
        }
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if(M!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                numberArr[tmp] = false;
            }
        }
        for (int i = 1; i <=7 ; i++) {
            goToNumber = new int[i];
            permutation(0);
        }
        minButton = Math.min(minButton, Math.abs(N - 100));
        bw.append("" + minButton);
        bw.close();
        br.close();

    }
}
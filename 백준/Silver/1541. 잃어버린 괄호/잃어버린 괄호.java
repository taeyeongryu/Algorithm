import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String[] split = input.split("-");
        int result = 0;
        for (int i = 0; i < split.length; i++) {
            int sum = sumString(split[i]);
            if(i==0){
                result +=sum;
            }else{
                result -= sum;
            }

        }
        System.out.println(result);
    }

    private static int sumString(String input) {
        int result = 0;
        String[] split = input.split("\\+");
        for (String s : split) {
            result += Integer.parseInt(s);
        }
        return result;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        int num = 1;
        while(true){
            String strNum = ""+num;
            if(strNum.indexOf("666") != -1){
                count++;
            }
            if(count==N){
                System.out.println(num);
                break;
            }
            num++;
        }

    }
}
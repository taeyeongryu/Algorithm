import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static boolean isPe(String number){
        boolean flag = true;
        int left = 0;
        int right = number.length()-1;
        while(left<right){
            if(number.charAt(left)==number.charAt(right)){
                left++;
                right--;
            }else{
                flag=false;
                break;
            }
        }
        return flag;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String number = br.readLine();
            if(Integer.parseInt(number) == 0){
                break;
            }
            bw.append(isPe(number) ? "yes" : "no");
            bw.append("\n");
        }
        bw.close();
        br.close();

    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[8];
        for(int i = 0; i < 8; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean asc = true;
        boolean desc = true;
        //ascending
        for (int i = 0; i < 8; i++) {
            if(arr[i]==i+1){
                continue;
            }else{
                asc = false;
                break;
            }
        }
        //descending
        for (int i = 0; i < 8; i++) {
            if (arr[i] == 8 - i) {
                continue;
            } else {
                desc = false;
                break;
            }
        }
        System.out.println(asc ? "ascending" : desc ? "descending" : "mixed");
    }
}
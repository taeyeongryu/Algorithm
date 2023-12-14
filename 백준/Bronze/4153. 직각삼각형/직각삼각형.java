import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> list = new ArrayList<>();

        StringTokenizer st = null;
        while(true){
            list.clear();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==0||b==0||c==0){
                break;
            }
            list.add(a);
            list.add(b);
            list.add(c);
            Collections.sort(list);
            long sqarA = ((long)list.get(0)) * list.get(0);
            long sqarB = ((long)list.get(1)) * list.get(1);
            long sqarC = ((long)list.get(2)) * list.get(2);
            if(sqarA+sqarB==sqarC){
                bw.append("right\n");
            }else{
                bw.append("wrong\n");
            }
        }
        bw.close();
        br.close();
    }
}
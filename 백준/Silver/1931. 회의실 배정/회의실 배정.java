import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        Collections.sort(list,new Comparator<int[]>(){
            @Override
            public int compare(int[] arr1, int[] arr2){
                return Integer.compare(arr1[1], arr2[1]) == 0 ? Integer.compare(arr1[0], arr2[0]) : Integer.compare(arr1[1], arr2[1]);
            }
        });
//        for (int[] ints : list) {
//            System.out.println(Arrays.toString(ints));
//        }
        int endTime = 0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            if(endTime<=cur[0]){
                endTime=cur[1];
                count++;
            }
        }
        bw.append(count + "");
        bw.close();
        br.close();

    }
}
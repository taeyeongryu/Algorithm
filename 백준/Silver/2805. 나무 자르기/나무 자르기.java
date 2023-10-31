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

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        int[] treeArr = new int[N];

        st = new StringTokenizer(br.readLine());
        long minTree = 0;
        long maxTree = 0;
        for(int i = 0; i < treeArr.length; i++){
            treeArr[i] = Integer.parseInt(st.nextToken());
            if (maxTree <= treeArr[i]) {
                maxTree = treeArr[i];
            }
        }
        long result = 0;
        long midTree = 0;


        while(minTree<=maxTree){
            midTree = (minTree + maxTree) / 2;
            long total = 0;
            for (int i = 0; i<treeArr.length; i++){
                //칼날의 높이가 나무 높이보다 낮을 때만 나무가 잘린다.
                if (treeArr[i] > midTree) {
                    total += (treeArr[i] - midTree);
                }
            }
            //조건을 만족
            //칼날을 올려야 함
            if(total>=M){
                result = Long.max(result, midTree);
                minTree = midTree + 1;
            }
            //조건을 만족하지 않음
            //칼날을 내려야 함
            else{
                maxTree=midTree-1;
            }
        }
        bw.append(result+"");
        bw.close();
        br.close();
    }
}
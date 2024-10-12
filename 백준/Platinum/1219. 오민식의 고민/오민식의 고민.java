import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long[] earnArr;
    static int[] incomeArr;
    static class Edge{
        int start;
        int end;
        int weight;
        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    static int N,M,startIndex, endIndex;
    static List<Edge> edgeList;
    static boolean flag = true;
    static void bellmanfold(int startIndex){
        earnArr[startIndex] = incomeArr[startIndex];

        for (int i = 0; i < N + 50; i++) {
            for (int j = 0; j < M; j++) {
                Edge curEdge = edgeList.get(j);

                int curStart = curEdge.start;
                int curEnd = curEdge.end;
                int curWeight = curEdge.weight;

                if (earnArr[curStart] == Long.MIN_VALUE) {
                    continue;
                }
                if(earnArr[curStart]==Long.MAX_VALUE){
                    earnArr[curEnd] = Long.MAX_VALUE;
                    continue;
                }
                if (earnArr[curEnd] < incomeArr[curEnd] - curWeight + earnArr[curStart]) {
                    if (i >= N - 1) {
                        earnArr[curEnd] = Long.MAX_VALUE;
                    } else {
                        earnArr[curEnd] = incomeArr[curEnd] - curWeight + earnArr[curStart];
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        startIndex = Integer.parseInt(st.nextToken());
        endIndex = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        earnArr = new long[N];
        incomeArr = new int[N];
        Arrays.fill(earnArr,Long.MIN_VALUE);


        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(s, e, w));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int income = Integer.parseInt(st.nextToken());
            incomeArr[i] = income;
        }
        bellmanfold(startIndex);
//        System.out.println("Arrays.toString(earnArr) = " + Arrays.toString(earnArr));
        if(earnArr[endIndex]==Long.MIN_VALUE){
            bw.append("gg");
        }
        else{
            if (earnArr[endIndex]!=Long.MAX_VALUE) {
                bw.append(earnArr[endIndex] + "");
            } else {
                bw.append("Gee");
            }
        }
        bw.close();
        br.close();
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[] choiced;
    static List<Node> chicken;
    static List<Node> house;
    static int[][] dist;
    static int min;
    static void dfs(int depth, int at){
        if(depth==M){
            //특정 메소드 실행시킨다.
            min = Math.min(scan(), min);
            return;
        }
        for (int i = at; i < chicken.size(); i++) {
            choiced[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
    static int scan(){
        int result = 0;
        for (int i = 0; i < house.size(); i++) {
            int tmp = Integer.MAX_VALUE;
            for (int j = 0; j < choiced.length; j++) {
                tmp = Math.min(dist[i][choiced[j]], tmp);
            }
            result += tmp;
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        min = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        choiced = new int[M];
        house = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 0) {
                    continue;
                }else if(input ==1){
                    house.add(new Node(i, j));
                }else{
                    chicken.add(new Node(i, j));
                }
            }
        }

        dist = new int[house.size()][chicken.size()];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                Node nowHouse = house.get(i);
                Node nowChicken = chicken.get(j);
                dist[i][j] = Math.abs(nowHouse.r - nowChicken.r) + Math.abs(nowHouse.c - nowChicken.c);
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }
}
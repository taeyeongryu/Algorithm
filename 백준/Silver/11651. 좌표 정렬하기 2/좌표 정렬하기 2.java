import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point>{
        int x;
        int y;
        private Point(){
        }
        private Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point p){
            if (Integer.compare(this.y, p.y) != 0) {
                return Integer.compare(this.y, p.y);
            }else{
                return Integer.compare(this.x, p.x);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Point(x, y));
        }
        Collections.sort(list);
        for (Point point : list) {
            bw.append("" + point.x + " " + point.y + "\n");
        }
        bw.close();
        br.close();
    }
}
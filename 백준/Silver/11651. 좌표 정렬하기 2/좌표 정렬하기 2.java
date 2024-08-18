import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "x : " + x + ", y : " + y;
        }

    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list,new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                return Integer.compare(p1.y, p2.y) == 0 ? Integer.compare(p1.x, p2.x) : Integer.compare(p1.y, p2.y);
            }
        });
        for (Point point : list) {
            bw.append(point.x + " " + point.y + "\n");
        }
        bw.close();
        br.close();
    }
}
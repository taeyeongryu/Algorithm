import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <=10 ; tc++) {
        list.clear();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            list.add(input);
        }
        int opernum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < opernum; i++) {
            char oper = st.nextToken().charAt(0);
            switch (oper){
                case 'I':
                    int location = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    for (int i1 = 0; i1 < num; i1++) {
                        list.add(location+i1,Integer.parseInt(st.nextToken()));
                    }
                    break;
                case 'D':
                    int start = Integer.parseInt(st.nextToken());
                    int num2 = Integer.parseInt(st.nextToken());
                    for (int i1 = 0; i1 < num2; i1++) {
                        list.remove(start);
                    }
                    break;
                case 'A':
                    int num3 = Integer.parseInt(st.nextToken());
                    for (int i1 = 0; i1 < num3; i1++) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                    break;
            }
        }
        System.out.printf("#%d ",tc);
            for (int i = 0; i < 10; i++) {
                System.out.printf("%d ",list.get(i));
            }
            System.out.println();
        }
    }
}
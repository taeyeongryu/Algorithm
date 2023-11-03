import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    static void add(int num) {
        set.add(num);
    }
    static void remove(int num){
        set.remove(num);
    }
    static void check(int num){
        if(set.contains(num)){
            sb.append("1\n");
        }else{
            sb.append("0\n");
        }
    }
    static void toggle(int num){
        if(set.contains(num)){
            set.remove(num);
        }else{
            set.add(num);
        }
    }
    static void all(){
        set.clear();
        for (int i = 1; i <=20 ; i++) {
            set.add(i);
        }
    }
    static void empty(){
        set.clear();
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if ("add".equals(oper) ) {
                int number = Integer.parseInt(st.nextToken());
                add(number);
            } else if ("remove".equals(oper) ) {
                int number = Integer.parseInt(st.nextToken());
                remove(number);
            } else if ("check".equals(oper) ) {
                int number = Integer.parseInt(st.nextToken());
                check(number);
            } else if ("toggle".equals(oper) ) {
                int number = Integer.parseInt(st.nextToken());
                toggle(number);
            } else if ("all".equals(oper)) {
                all();
            } else{
                empty();
            }
        }
        System.out.println(sb.toString());
    }
}
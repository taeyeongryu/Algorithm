import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Person implements Comparable<Person> {
        int age;
        String name;

        private Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public int compareTo(Person person) {
            return Integer.compare(this.age, person.age);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>();
        StringTokenizer st = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Person(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        Collections.sort(list);

        for(Person person : list){
            bw.append("" + person.age + " " + person.name + "\n");
        }
        bw.close();
        br.close();

    }
}
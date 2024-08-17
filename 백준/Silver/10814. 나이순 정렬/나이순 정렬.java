import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Person{
        int age;
        String name;
        public Person(int age, String name){
            this.age = age;
            this.name = name;
        }
        @Override
        public String toString(){
            return "age : " + age + ", name : " + name;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<Person> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Person(age, name));
        }
        Collections.sort(list, new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2){
                return Integer.compare(p1.age, p2.age);
            }
        });
        for(Person p : list){
            bw.append(p.age + " " + p.name + "\n");
        }
        bw.close();
        br.close();
    }
}
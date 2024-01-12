import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Jewl{
        int weight;
        int price;

        public Jewl(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Jewl{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }
    static int N, K;
    static PriorityQueue<Jewl> pq = new PriorityQueue<>(new Comparator<Jewl>(){
        @Override
        public int compare(Jewl o1, Jewl o2) {
            return -Integer.compare(o1.price, o2.price);
        }
    });
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        List<Integer> bagList = new ArrayList<>();
        List<Jewl> jewlList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            jewlList.add(new Jewl(w, p));
        }
        for (int i = 0; i < K; i++) {
            bagList.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(jewlList, new Comparator<Jewl>() {
            @Override
            public int compare(Jewl o1, Jewl o2) {
                    return Integer.compare(o1.weight, o2.weight);
            }
        });
        Collections.sort(bagList);

        long totalValue = 0;
        int jewlIndex = 0;
        for (int i = 0; i < bagList.size(); i++) {
            int curBackWeight = bagList.get(i);
            //지금 내 가방에 들어갈 수 있는 모든 보석 다 pq에 넣는다.
            for (;jewlIndex < N; jewlIndex++) {
                Jewl curJewl = jewlList.get(jewlIndex);

                int curJewlWeight = curJewl.weight;
                if (curJewlWeight<=curBackWeight) {
                    pq.add(curJewl);
                }else{
                    break;
                }
            }
            if(!pq.isEmpty()){
                Jewl curJewl = pq.poll();
                totalValue += curJewl.price;
            }
        }
        bw.append(totalValue + "");
        bw.close();
        br.close();

    }
}
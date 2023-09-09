import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int result = 0;
            PriorityQueue<Integer> leftheap = new PriorityQueue<>();
            PriorityQueue<Integer> rightheap = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int mid = Integer.parseInt(st.nextToken());
            for (int i = 0; i <N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                if  (a <= mid) {
                    leftheap.offer(-a);
                    int b = Integer.parseInt(st.nextToken());
                    if (mid <= b) {
                        rightheap.offer(b);
                    } else {
                        int peek = -leftheap.peek();
                        if(peek<=b){
                        rightheap.offer(mid);
                        mid=b;
                        }else{
                            rightheap.offer(mid);
                            mid = peek;
                            leftheap.poll();
                            leftheap.offer(-b);
                        }
                    }

                } else {
                    rightheap.offer(a);
                    int b = Integer.parseInt(st.nextToken());
                    if (b <= mid) {
                        leftheap.offer(-b);
                    } else {
                        int peek = rightheap.peek();
                        if(peek>=b){
                            leftheap.offer(-mid);
                            mid=b;
                        }else{
                            leftheap.offer(-mid);
                            mid = peek;
                            rightheap.poll();
                            rightheap.offer(b);
                        }
                    }

                }
                result += mid;
                result %= 20171109;

            }
            bw.write("#" + tc + " " + result + "\n");
        }
        br.close();
        bw.close();
    }
}
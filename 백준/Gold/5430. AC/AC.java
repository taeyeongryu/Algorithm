import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    static boolean dir;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
//        List<Integer> list = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Collections.reverse(list);
//        System.out.println(list.toString());

        int T = Integer.parseInt(br.readLine());
        outer:for (int i = 0; i < T; i++) {
            String operation = br.readLine();
            int arrLength = Integer.parseInt(br.readLine());
            String strString = br.readLine();
            dir = true;
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            if(arrLength>0){
                String[] split = strString.substring(1, strString.length() - 1).split(",");
                for (String s : split) {
                    deque.add(Integer.parseInt(s));
                }
            }
//            System.out.println(Arrays.toString(split));

//            System.out.println(intList.toString());
            for (int j = 0; j < operation.length(); j++) {
                char c = operation.charAt(j);
                if(c=='R'){
                    dir=!dir;
                }
                else{
                    //Drop해야 되는데 없으면
                    if(deque.isEmpty()){
                        sb.append("error\n");
                        continue outer;
                    }
                    //정방향이면
                    if(dir){
                        deque.removeFirst();
                    }
                    //역방향이면
                    else{
                        deque.removeLast();
                    }
                }
//                System.out.println(intList);
            }
            makePrintString(deque,dir);
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void makePrintString(ArrayDeque<Integer> deque, boolean isRight) {

        sb.append('[');	// 여는 대괄호 먼저 StringBuilder에 저장

        if(deque.size() > 0) {	//만약 출력 할 원소가 한 개 이상일 경우

            if(isRight) {	// 정방향일경우

                sb.append(deque.pollFirst());	// 먼저 첫 번째 원소를 넘겨준다.

                // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 하나씩 뽑아 넘긴다.
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollFirst());
                }
            }
            else {	// 역방향일경우
                sb.append(deque.pollLast());	// 먼저 뒤에서부터 첫 번째 원소를 넘겨준다.

                // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 뒤에서부터 하나씩 뽑아 넘긴다.
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollLast());
                }
            }
        }

        sb.append(']').append('\n');	// 닫는 대괄호 및 개행으로 마무리
    }
}
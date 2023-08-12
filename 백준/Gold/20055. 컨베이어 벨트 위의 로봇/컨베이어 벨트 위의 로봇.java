import java.util.Scanner;

public class Main {
    static boolean[] robot;
    static int[][] map;
    static int N,K;

    static void turnRobot(){
        for (int i = robot.length-1; i > 0 ; i --) {
            robot[i] = robot[i - 1];
        }
        robot[0]=false;
    }
    static void turnMap(){
        int tmp1 = map[map.length - 1][0];
        int tmp2 = map[map.length - 1][1];
        for (int i = map.length - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
            map[i][1] = map[i - 1][1];
        }
        map[0][0] = tmp1;
        map[0][1] = tmp2;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        map = new int[2 * N][2];
        robot = new boolean[N];
        for (int i = 0; i <N*2 ; i++) {
            map[i][0] = sc.nextInt();
        }
        int cnt = 0;
        int time = 1;
        while (true) {
            turnRobot();
            turnMap();
            for (int i = robot.length-1; i >= 0 ; i--) {
                if (robot[i]){
                    int nxt = i + 1;
                    if(nxt==N){
                        robot[i]=false;
                        continue;
                    }
                    if (nxt < N && !robot[nxt]) {
                        if(map[nxt][0]>0){
                            robot[nxt] = true;
                            robot[i] = false;
                            map[nxt][0]--;

                            if(map[nxt][0]==0){
                                cnt++;
                            }
                        }
                    }
                }
            }
            if (!robot[0] && map[0][0] > 0) {
                map[0][0]--;
                robot[0] = true;
                if (map[0][0]==0){
                    cnt++;
                }
            }
            if(cnt>=K){
                break;
            }
            time++;
        }
        System.out.println(time);
    }
}
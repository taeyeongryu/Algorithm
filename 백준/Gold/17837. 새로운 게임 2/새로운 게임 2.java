import sun.font.FontRunIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int idx;
        int r;
        int c;
        int dir;
        public Node(int idx, int r, int c, int dir) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }
    static int N,K,count;
    static boolean flag;
    static int[][] chess;
    static List<Integer>[][] map;
    static Node[] arr;
    //하, 우, 상, 좌
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chess = new int[N + 1][N + 1];
        map = new List[N + 1][N + 1];
        arr = new Node[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                chess[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 1; i <=K ; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            switch (dir){
                case 2:
                    dir=3;
                    break;
                case 3:
                    dir=2;
                    break;
                    case 4:
                        dir=0;
                        break;
            }
            arr[i] = new Node(i, r, c, dir);
            map[r][c].add(i);
        }
        outer : for (int i = 1; i <=1000 ; i++) {
            count++;
            for (int j = 1; j <=K ;j++) {
                move(j);
                if(flag){break outer;}
            }
        }
        if(!flag){

        System.out.println(-1);
        }
    }
    static void move(int idx){
        Node cur = arr[idx];
        //이동해야 하는 곳
        int nr = cur.r+dr[cur.dir];
        int nc = cur.c+dc[cur.dir];
        //블루 호출
        if(1>nr||N<nr||1>nc||nc>N){
            moveblue(idx);
        }
        else{
            //화이트
            if(chess[nr][nc]==0){
                movewhite(idx, nr, nc);
            }
            //빨간색
            else if(chess[nr][nc]==1){
                movered(idx, nr, nc);
            }
            //파란색
            else{
                moveblue(idx);
            }
        }
    }
    //다음 위치까지 생각해야 한다.
    static void movewhite(int idx,int nr, int nc){

        Node cur = arr[idx];
        int r = cur.r;
        int c = cur.c;
        List<Integer> integerList = map[r][c];
        int index = integerList.indexOf(idx);

        int size = integerList.size();
        //index부터 끝까지 다음위치로 옮긴다.
        for (int i = index; i <size ; i++) {
            map[nr][nc].add(integerList.get(index));
            arr[integerList.get(index)].r = nr;
            arr[integerList.get(index)].c = nc;
            integerList.remove(index);
            if(map[nr][nc].size()>=4){
                System.out.println(count);
                flag=true;
                return;
            }

        }

    }
   static void moveblue(int idx){

        Node cur = arr[idx];
        int ndir = (cur.dir + 2) % 4;
        cur.dir=ndir;
        int nr = cur.r + dr[ndir];
        int nc = cur.c + dc[ndir];
       if ((1 > nr || N < nr || 1 > nc || nc > N) || chess[nr][nc] == 2) {
           return;
       }
       //그냥 직진
       else if (chess[nr][nc] == 0) {

           movewhite(idx, nr, nc);
       }
       //빨간색
       else if (chess[nr][nc] == 1) {
           movered(idx, nr, nc);
       }
        //파란색
        //암것도 안함

    }
    static void movered(int idx,int nr, int nc){

        Node cur = arr[idx];
        int r = cur.r;
        int c = cur.c;

        List<Integer> integerList = map[r][c];

        int index = integerList.indexOf(idx);

        int size = integerList.size();
        //index부터 끝까지 다음위치로 옮긴다.
        for (int i = size-1; i >= index  ; i--) {
            map[nr][nc].add(integerList.get(i));
            arr[integerList.get(i)].r = nr;
            arr[integerList.get(i)].c = nc;
            integerList.remove(i);
            if(map[nr][nc].size()>=4){
                System.out.println(count);
                flag=true;
                return;
            }
        }

    }

}

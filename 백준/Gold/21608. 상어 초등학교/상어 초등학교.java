import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    //순서배열
    static int[] seq;
    //학생 배열
    //자리 맵
    static int[][] map;
    //좋아하는 사람 배열
    static List<Integer>[] likeList;
    static void memberset(int cur){
        boolean flag = false;
        int r = 1;
        int c = 1;
        int total = 0;
        int remain = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1 ; j++) {
                if(map[i][j]!=0){
                    continue;
                }

                int count = 0;
                int curremain=0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(nr < 1||nr > N||nc < 1||nc > N){
                        continue;
                    }

                    //친구 찾으면 update

                    if(likeList[cur].indexOf(map[nr][nc])!=-1){
                        count++;
                    }

                    if(map[nr][nc]==0){
                        curremain++;
                    }

                }

                if(count>total){
                    flag=true;
                        r=i;
                        c=j;
                        total=count;
                        remain = curremain;
                    }
                else if(count==total){
                    if(remain<curremain){
                        flag=true;
                        r=i;
                        c=j;
                        remain = curremain;
                    }
                }
            }
        }
        if(!flag){
            outer : for (int i = 1; i <=N ; i++) {
                for (int j = 1; j <=N ; j++) {
                    if(map[i][j]==0){
                        map[i][j]=cur;
                        break outer;
                    }
                }
            }
        }else{
            map[r][c]=cur;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N * N + 1];
        map = new int[N + 1][N + 1];
        likeList = new ArrayList[N * N + 1];
        for (int i = 0; i < likeList.length; i++) {
            likeList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < N*N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            seq[i]=a;
            for (int j = 0; j < 4; j++) {
                likeList[a].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 1; i < seq.length ; i++) {
            memberset(seq[i]);
        }
     
        int total = 0;
        for (int i = 1; i <N+1 ; i++) {
            for (int j = 1; j <N+1 ; j++) {
                int count=0;
                for (int k = 0; k < 4 ; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(nr < 1||nr > N||nc < 1||nc > N){
                        continue;
                    }
                    if(likeList[map[i][j]].indexOf(map[nr][nc])!=-1){
                        count++;
                    }
                }
                switch (count){
                    case 0:
                        break;
                    case 1:
                        total+=1;
                        break;
                    case 2:
                        total+=10;
                        break;
                    case 3:
                        total+=100;
                        break;
                    case  4:
                        total+=1000;
                        break;
                }
            }
        }
        System.out.println(total);
    }
}
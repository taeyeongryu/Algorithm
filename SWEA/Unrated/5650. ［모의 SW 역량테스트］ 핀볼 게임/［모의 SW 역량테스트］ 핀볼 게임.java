import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
    static class Hole {
        int r;
        int c;
 
        public Hole(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
 
    // 하 좌 상 우
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static List<Hole>[] holelist;
    static int[][] map;
    static int N;
    static int max;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            max = 0;
            map = new int[N][N];
            holelist = new ArrayList[5];
            for (int i = 0; i < 5; i++) {
                holelist[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int tmp = sc.nextInt();
                    map[i][j] = tmp;
                    if (6 <= tmp && tmp <= 10) {
                        holelist[tmp - 6].add(new Hole(i, j));
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        findmax(i, j);
                    }
                }
            }
            System.out.printf("#%d %d%n", tc, max);
        }
    }
 
    private static void findmax(int row, int col) {
        int initRow = row;
        int initCol = col;
 
        for (int i = 0; i < 4; i++) {
            int idx = i;
            int cnt = 0;
              
            while (true) {
                row += dr[idx];
                col += dc[idx];
                  
                // 벽에 부딪히는 경우
                if (row == -1 || row == N || col == -1 || col == N) {
                    if (idx == 0) idx = 1;
                    else if (idx == 1) idx = 0;
                    else if (idx == 2) idx = 3;
                    else idx = 2;
                    cnt++;
                    continue;
                }
              
                // 종료 조건
                if ((row == initRow && col == initCol) || map[row][col] == -1) {
                    break;
                }
                  
                // 블록에 부딪히는 경우
                if(map[row][col] == 1) {
                    if (idx == 0) idx = 1;
                    else if (idx == 1) idx = 3;
                    else if (idx == 2) idx = 0;
                    else idx = 2;
                    cnt++;
                } else if(map[row][col] == 2) {
                    if (idx == 0) idx = 1;
                    else if (idx == 1) idx = 2;
                    else if (idx == 2) idx = 3;
                    else idx = 0;
                    cnt++;
                } else if(map[row][col] == 3) {
                    if (idx == 0) idx = 2;
                    else if (idx == 1) idx = 0;
                    else if (idx == 2) idx = 3;
                    else idx = 1;
                    cnt++;
                } else if(map[row][col] == 4) {
                    if (idx == 0) idx = 3;
                    else if (idx == 1) idx = 0;
                    else if (idx == 2) idx = 1;
                    else idx = 2;
                    cnt++;
                } else if(map[row][col] == 5) {
                    if (idx == 0) idx = 1;
                    else if (idx == 1) idx = 0;
                    else if (idx == 2) idx = 3;
                    else idx = 2;
                    cnt++;
                }
                  
                // 웜홀에 빠지는 경우
                else if(map[row][col] > 5) {
                    for(Hole w : holelist[map[row][col]-6]) {
                        if(w.r != row || w.c != col) {
                            row = w.r;
                            col = w.c;
                            break;
                        }
                    }
                }
            }
            max = Math.max(max, cnt);
        }
 
    }
}
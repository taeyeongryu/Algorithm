import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static class Matrix{
        int r;
        int c;
        int cnt;

        public Matrix(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Matrix{" +
                    "r=" + r +
                    ", c=" + c +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    static Matrix[][] calMatrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;


        calMatrix = new Matrix[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            calMatrix[i][i] = new Matrix(r, c, 0);
        }

        calculate( 1,  N);
        bw.append(calMatrix[1][N].cnt + "");
        bw.close();
        br.close();
    }

    private static void calculate(int a, int b) {
        for (int i = 0; i < b - a; i++) {
            if (calMatrix[a][a + i] == null) {
                calculate(a, a + i);
            }
            if (calMatrix[a + i + 1][b] == null) {
                calculate(a + i + 1, b);
            }
            Matrix matrixA = calMatrix[a][a + i];
            Matrix matrixB = calMatrix[a + i + 1][b];
            //이번에 새로 만든 cnt
            //왼쪽꺼, 오른쪽꺼, 새로만든 거
            int cnt = calMatrix[a][a + i].cnt + calMatrix[a + i + 1][b].cnt + matrixA.r * matrixA.c * matrixB.c;
            if (calMatrix[a][b] == null) {
                calMatrix[a][b] = new Matrix(matrixA.r, matrixB.c, cnt);
            } else {
                calMatrix[a][b].cnt = Math.min(cnt, calMatrix[a][b].cnt);
            }
        }
    }
}
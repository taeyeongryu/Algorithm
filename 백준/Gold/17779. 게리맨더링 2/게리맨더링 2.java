import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] board;
	static int N;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		//입력
		board=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++){
				board[i][j]=sc.nextInt();
				
			}
		} 
		// 개리멘더링
		for(int i=0; i<N-2; i++) {
			for(int j=1; j<N-1; j++) {
				select(i, j);
			}
		}
		System.out.println(ans);
	}
	private static void select(int x, int y) {
		for(int d1=1;d1<=Math.min((N-1)-y, (N-2)-x); d1++) {
			for(int d2=1; d2<=Math.min(((N-1)-(x+d1)), y); d2++) {
				garry(x, y, d1, d2);
			}
		}
		
	}
	private static void garry(int x, int y, int d1, int d2) {
		boolean check[][]=new boolean[N][N];
		int cnt1=-1;
		int cnt2=-1;
		int[] sum=new int[5];
		boolean f1=true;
		boolean f2=true;
		//5번 구역
		for(int i=x; i<=x+d1+d2; i++) {
			if(cnt1<d1 && f1) cnt1++;
			else {
				f1=false;
				cnt1--;
			}
			if(cnt2<d2 && f2)cnt2++;
			else {
				f2=false;
				cnt2--;
			}
			for(int j=y-cnt2; j<=y+cnt1; j++) {
				check[i][j]=true;
				sum[4]+=board[i][j];
			}
		}
		//1번구역
		for(int i=0; i<x+d2; i++) {
			for(int j=0; j<=y; j++) {
				if(check[i][j]) continue;
				sum[0]+=board[i][j];
			}
		}
		//2번구역
		for(int i=0; i<=x+d1; i++) {
			for(int j=y+1; j<N; j++) {
				if(check[i][j]) continue;
				sum[1]+=board[i][j];
			}
		}
		//3번구역
		for(int i=x+d2; i<N; i++) {
			for(int j=0; j<y-d2+d1; j++) {
				if(check[i][j]) continue;
				sum[2]+=board[i][j];
			}
		}
		//4번구역
		for(int i=x+d1+1; i<N; i++) {
			for(int j=y-d2+d1; j<N; j++) {
				if(check[i][j]) continue;
				sum[3]+=board[i][j];
			}
		}
		Arrays.sort(sum);
		ans=Math.min(sum[4]-sum[0], ans);
	}

}
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static LinkedList<Integer> board[];
	static int N,M;
	static final int[] dx= {0, -1, 0, 1};
	static final int[] dy= {-1, 0, 1, 0};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		board=new LinkedList[N];
		for(int i=0; i<N; i++) board[i]=new LinkedList<>();
		int T=sc.nextInt();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				board[i].add(sc.nextInt());
			}
		}
		//입력 완료
		for(int i=0; i<T; i++) {
			spin(sc.nextInt(), sc.nextInt(), sc.nextInt());
			searchNdestroy();
		}
		//계산
		int sum=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sum+=board[i].get(j);
			}
		}
		System.out.println(sum);
	}
	private static void searchNdestroy() {
		boolean[][] des=new boolean[N][M];
		boolean flag=true;
		boolean f2=true;
		for(int i=0; i<N; i++) {
		for(int j=0; j<M; j++) {
			if(board[i].get(j)==0) continue;
			f2=false;
			for(int k=0; k<4; k++) {
				int nx=i+dx[k];
				int ny=(j+dy[k])%M;
				if(nx<0 || nx>=N) continue;
				if(ny<0) ny+=M;
				if(board[i].get(j).equals(board[nx].get(ny))) {
					des[i][j]=true;
					des[nx][ny]=true;
					flag=false;
				}
			}
		}
		}
		//제거
		if(flag) {
			if(f2) return;
			double sum=0;
			int cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
				if(board[i].get(j)!=0) {
					sum+=board[i].get(j);
					cnt++;
				}
				}
			}
			sum/=cnt;
			for(int j=0; j<N; j++) {
			for(int i=0; i<M; i++) {
				if(board[j].get(i)==0) continue;
				if(board[j].get(i)>sum) {
					board[j].set(i, board[j].get(i)-1);
				}else if(board[j].get(i)<sum) board[j].set(i, board[j].get(i)+1);
			}
			}
		}else {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(des[i][j]) {
					board[i].set(j, 0);
				}
			}
		}
		}
	}
	private static void spin(int num, int direc, int count) {
		for(int i=num-1; i<N; i+=num) {
			if(direc==0) {
				for(int j=0; j<count; j++) {
					board[i].addFirst((board[i].pollLast()));
				}
			}else {
				for(int j=0; j<count; j++) {
					board[i].addLast((board[i].pollFirst()));
				}
			}
		}
		
	}	
}
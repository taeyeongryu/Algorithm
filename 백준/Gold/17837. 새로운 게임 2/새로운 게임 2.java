import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static final int[] dx= {0, 0, 0, -1, 1};
	static final int[] dy= {0, 1, -1, 0, 0};
	static int[][] board;
	static int N, K;
	static ArrayList<int[]> pawn=new ArrayList<>();
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		K=sc.nextInt();
		board=new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				board[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<K; i++) {
			//정보 행 열 방향 높이
			int[] temp= {sc.nextInt(), sc.nextInt(), sc.nextInt(), 1};
			pawn.add(temp);
		}
		for(int time=1; time<=1000; time++) {
			for(int i=0; i<K; i++) {
				move(i);
				//나가는 조건 : 높이가 4인가? 그러면 종료하라
				for(int j=0; j<K; j++) {
					if(pawn.get(j)[3]==4) {
						System.out.println(time);
						System.exit(0);
					}
				}
			}
		}
		//만약 루프를 빠져나왔다면?-1000이 지남
		System.out.println(-1);
	}
	private static void move(int a) {
		ArrayList<Integer> pointer=new ArrayList<>();
		int x=pawn.get(a)[0];
		int y=pawn.get(a)[1];
		int direc=pawn.get(a)[2];
		int height=pawn.get(a)[3];
		int heightmax=0;
		for(int i=0; i<K; i++) {
			if(pawn.get(i)[0]==x && pawn.get(i)[1]==y && pawn.get(i)[3]>=height) {
				pointer.add(i);
				pawn.get(i)[3]-=(height-1);
				heightmax++;
			}
		}
		heightmax++;
		x+=dx[direc];
		y+=dy[direc];
		//이제 이동
		//파란색이거나 맵 끝이라면?
		if(x<=0 || x>N || y<=0 || y>N || board[x][y]==2) {
			if(direc<=2) {
				direc=3-direc;
			}else {
				direc=7-direc;
			}
			pawn.get(a)[2]=direc;
			for(int i=0; i<pointer.size(); i++) {
				pawn.get(pointer.get(i))[0]=x;
				pawn.get(pointer.get(i))[1]=y;
			}
			x+=dx[direc];
			y+=dy[direc];
			int nx=x+dx[direc];
			int ny=y+dy[direc];
			if(nx<=0 || nx>N || ny<=0 || ny>N || board[nx][ny]==2) {
				//쌓는 메커니즘
				int heightstart=0;
				for(int i=0; i<K; i++) {
					if(pawn.get(i)[0]==x && pawn.get(i)[1]==y) {
						heightstart++;
					}
				}
				for(int i=0; i<pointer.size(); i++) {
					pawn.get(pointer.get(i))[0]=x;
					pawn.get(pointer.get(i))[1]=y;
					pawn.get(pointer.get(i))[3]+=heightstart;
				}
				return;
			}else if(board[nx][ny]==1) {
				x=nx;
				y=ny;
				//뒤집는 메커니즘
				for(int i=0; i<pointer.size(); i++) {
					pawn.get(pointer.get(i))[3]=heightmax-pawn.get(pointer.get(i))[3];
				}
				//쌓는 메커니즘
				int heightstart=0;
				for(int i=0; i<K; i++) {
					if(pawn.get(i)[0]==x && pawn.get(i)[1]==y) {
						heightstart++;
					}
				}
				for(int i=0; i<pointer.size(); i++) {
					pawn.get(pointer.get(i))[0]=x;
					pawn.get(pointer.get(i))[1]=y;
					pawn.get(pointer.get(i))[3]+=heightstart;
				}
				return;
			}else {
				x=nx;
				y=ny;
				//쌓는 메커니즘
				int heightstart=0;
				for(int i=0; i<K; i++) {
					if(pawn.get(i)[0]==x && pawn.get(i)[1]==y) {
						heightstart++;
					}
				}
				for(int i=0; i<pointer.size(); i++) {
					pawn.get(pointer.get(i))[0]=x;
					pawn.get(pointer.get(i))[1]=y;
					pawn.get(pointer.get(i))[3]+=heightstart;
				}
				return;
			}
		}
		//하얀색이라면?
		if(board[x][y]==0) {
			//쌓는 메커니즘
			int heightstart=0;
			for(int i=0; i<K; i++) {
				if(pawn.get(i)[0]==x && pawn.get(i)[1]==y) {
					heightstart++;
				}
			}
			for(int i=0; i<pointer.size(); i++) {
				pawn.get(pointer.get(i))[0]=x;
				pawn.get(pointer.get(i))[1]=y;
				pawn.get(pointer.get(i))[3]+=heightstart;
			}
			return;
		}
		//빨간색이라면?
		if(board[x][y]==1) {
			//뒤집는 메커니즘
			for(int i=0; i<pointer.size(); i++) {
				pawn.get(pointer.get(i))[3]=heightmax-pawn.get(pointer.get(i))[3];
			}
			//쌓는 메커니즘
			int heightstart=0;
			for(int i=0; i<K; i++) {
				if(pawn.get(i)[0]==x && pawn.get(i)[1]==y) {
					heightstart++;
				}
			}
			for(int i=0; i<pointer.size(); i++) {
				pawn.get(pointer.get(i))[0]=x;
				pawn.get(pointer.get(i))[1]=y;
				pawn.get(pointer.get(i))[3]+=heightstart;
			}
			return;
		}
	}
}
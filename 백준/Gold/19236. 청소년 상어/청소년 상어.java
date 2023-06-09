import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class Fish {
		int idx;
		int dir;
		int r;
		int c;
		public Fish(int idx, int dir, int r, int c) {
			super();
			this.idx = idx;
			this.dir = dir;
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Fish [idx=" + idx + ", dir=" + dir + ", r=" + r + ", c=" + c + "]";
		}
		
	}
	static Fish[] fisharr= new Fish[17];
	static int[][] map = new int[4][4];
	// 우상 상 좌상 좌 좌하 하 우하 우
	static int max= 0;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { 1, 0, -1, -1, -1, 0, 1, 1 };
	//이동 후 잡아먹는 메서드
	static void dfs(int[][] map1, Fish[] arr1, int count) {
		
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map1[i][j]+" ");
//			}
//			System.out.println();
//		}
		max = Math.max(max, count);
		//물고기 이동시킨다.
		move(map1,arr1);
//		System.out.println("이동후");
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map1[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println("count : "+ count);
//		System.out.println("max : "+ max);
//		System.out.println();
		//현재 상어 위치
		int r =arr1[0].r; 
		int c =arr1[0].c;
		//현재 상어 방향
		int dir = arr1[0].dir;
		
		for (int i = 1; i <= 3; i++) {
			
			int nr = r+dr[dir]*i;
			int nc = c+dc[dir]*i;
			if(nr<0||nr>=4||nc<0||nc>=4) {
				continue;
			}
			//새로 작업할 것들
			int [][] map2 = new int[4][4];
			Fish[] arr2 = new Fish[17];
			int count2 = count;
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					map2[j][k]=map1[j][k];
				}
			}
			for (int j = 0; j < 17; j++) {
				if(arr1[j]!=null) {
					arr2[j]=new Fish(arr1[j].idx, arr1[j].dir, arr1[j].r,arr1[j].c);					
				}
			}
			
			//물고기가 있으면 잡아먹는다.
			if(map2[nr][nc]>0) {
				//물고기 없애고 상어 위치 바꾼다.
				count2+=map2[nr][nc];
				arr2[0].r = nr;
				arr2[0].c = nc;
				arr2[0].dir = arr2[map2[nr][nc]].dir;
				arr2[map2[nr][nc]]=null;
				map2[nr][nc]=-1;
				map2[r][c]=0;
				dfs(map2, arr2, count2);
			}
		}
	}
	
	static void move(int[][] map1,Fish[] arr1) {
		for (int i = 1; i <= 16; i++) {
			if(arr1[i]!=null) {
				//현위치
				int r = arr1[i].r;
				int c = arr1[i].c;
				int dir = arr1[i].dir;
				for (int j = 0; j < 8; j++) {
					int ndir = (dir+j)%8;
					int nr = r+dr[ndir];
					int nc = c+dc[ndir];
					if(nr<0||nr>=4||nc<0||nc>=4) {
						continue;
					}
					//그냥 이동
					if(map1[nr][nc]==0) {
						map1[nr][nc]=i;
						map1[r][c]=0;
						arr1[i].dir = ndir;
						arr1[i].r = nr;
						arr1[i].c = nc;
						break;
					}
					//자리 바꾸기
					else if(map1[nr][nc]>0) {
						arr1[i].dir = ndir;
						arr1[i].r = nr;
						arr1[i].c = nc;
						arr1[map1[nr][nc]].r = r;
						arr1[map1[nr][nc]].c = c;
						map1[r][c]=map1[nr][nc];
						map1[nr][nc]=i;
						break;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int idxtmp = sc.nextInt();
				int dirtmp = sc.nextInt();
				fisharr[idxtmp]=new Fish(idxtmp, dirtmp, i, j);
				map[i][j]=idxtmp;
			}
		}
		//상어 생성 및 투입
		int count = map[0][0];
		fisharr[0]=new Fish(-1, fisharr[map[0][0]].dir, 0, 0);
		fisharr[map[0][0]]=null;
		map[0][0]=-1;
		dfs(map, fisharr, count);
		System.out.println(max);
	}

}
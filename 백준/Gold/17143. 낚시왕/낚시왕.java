import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static final int[] dx= {0, -1, 1, 0, 0};
	static final int[] dy= {0, 0, 0, 1, -1};
	static ArrayList<int[]> sharks=new ArrayList<>();
	static int R, C, M, sum;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		R=sc.nextInt();
		C=sc.nextInt();
		M=sc.nextInt();
		int[] fraud= {-1, -1, -1};
		sharks.add(fraud);
		sum=0;
		for(int i=0; i<M; i++) {
			int[] temp=new int[5];
			for(int j=0; j<5; j++) {
				temp[j]=sc.nextInt();
			}
			sharks.add(temp);
		}
		for(int fisher=1; fisher<=C; fisher++) {
			//낚시하자
			fishing:for(int line=1; line<=R; line++) {
				for(int shark=1; shark<sharks.size(); shark++) {
					if(sharks.get(shark)[1]==fisher && sharks.get(shark)[0]==line) {
						sum+=sharks.get(shark)[4];
						sharks.remove(shark);
						break fishing;
					}
				}
			}
			//상어 이동하자
			for(int i=1; i<sharks.size(); i++) {
				if(sharks.get(i)[2]==0) continue;
				switch(sharks.get(i)[3]) {
				case 1:
					int ways=(R-sharks.get(i)[0])+sharks.get(i)[2];
					if(((ways-1)/(R-1))%2==0) {
						if(ways%(R-1)==0) {
							sharks.get(i)[0]=1;
						}else {
							sharks.get(i)[0]=R-(ways%(R-1));
						}
					}else {
						if(ways%(R-1)==0) {
							sharks.get(i)[0]=R;
						}else {
							sharks.get(i)[0]=(ways%(R-1))+1;
						}
						sharks.get(i)[3]=2;
					}
					break;
				case 2:
					ways=(sharks.get(i)[0]-1)+sharks.get(i)[2];
					if(((ways-1)/(R-1))%2==0) {
						if(ways%(R-1)==0) {
							sharks.get(i)[0]=R;
						}else {
							sharks.get(i)[0]=1+(ways%(R-1));
						}
					}else {
						if(ways%(R-1)==0) {
							sharks.get(i)[0]=1;
						}else {
							sharks.get(i)[0]=R-(ways%(R-1));
						}
						sharks.get(i)[3]=1;
					}
					break;
				case 3:
					ways=(sharks.get(i)[1]-1)+sharks.get(i)[2];
					if(((ways-1)/(C-1))%2==0) {
						if(ways%(C-1)==0) {
							sharks.get(i)[1]=C;
						}else {
							sharks.get(i)[1]=1+(ways%(C-1));
						}
					}else {
						if(ways%(C-1)==0) {
							sharks.get(i)[1]=1;
						}else {
							sharks.get(i)[1]=C-(ways%(C-1));
						}
						sharks.get(i)[3]=4;
					}
					break;
				case 4:
					ways=(C-sharks.get(i)[1])+sharks.get(i)[2];
					if(((ways-1)/(C-1))%2==0) {
						if(ways%(C-1)==0) {
							sharks.get(i)[1]=1;
						}else {
							sharks.get(i)[1]=C-(ways%(C-1));
						}
					}else {
						if(ways%(C-1)==0) {
							sharks.get(i)[1]=C;
						}else {
							sharks.get(i)[1]=(ways%(C-1))+1;
						}
						sharks.get(i)[3]=3;
					}
					break;
				}
			}
			int[][] temp=new int[R+1][C+1];
			//상어 위치 같은가?
			for(int i=1; i<sharks.size(); i++) {
				int x=sharks.get(i)[0];
				int y=sharks.get(i)[1];
				if(temp[x][y]!=0) {
					if(sharks.get(temp[x][y])[4]<sharks.get(i)[4]) {
						sharks.get(temp[x][y])[4]=0;
						temp[x][y]=i;
					}else {
						sharks.get(i)[4]=0;
					}
				}else temp[x][y]=i;
			}
			//제거
			for(int i=1; i<sharks.size(); i++) {
				if(sharks.get(i)[4]==0) {
					sharks.remove(i);
					i--;
				}
			}
		}
		System.out.println(sum);
	}
	
}
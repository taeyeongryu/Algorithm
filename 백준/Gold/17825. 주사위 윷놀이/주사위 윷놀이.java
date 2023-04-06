import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int sum=0;
	static int max=0;
	static int[] status=new int[4];
	static int[] dices=new int[10];
	static int[] horse=new int[4];
	static int[] special1= {0, 13, 16, 19};
	static int[] special2= {0, 22, 24};
	static int[] special3= {0, 28, 27, 26};
	static int[] special4= {25,30,35,40};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		for(int i=0; i<10; i++) {
			dices[i]=sc.nextInt();
		}
		dup(0);
		System.out.println(max);
	}
	private static void dup(int a) {
		if(a==10) {
			max=Math.max(sum, max);
		}else {
			for(int i=0; i<4; i++) {
				int st=status[i];
				int hor=horse[i];
				int temp=move(i, dices[a]);
				if(temp==-1) continue;
				sum+=temp;
				dup(a+1);
				sum-=temp;
				status[i]=st;
				horse[i]=hor;
			}
		}
		
	}
	private static int move(int a, int dice) {
		if(horse[a]==-1) return -1;
		int temp=status[a];
		int hor=horse[a];
		horse[a]+=dice;
		int point=0;
		switch(status[a]) {
		case 0:
			if(horse[a]>20) {
				horse[a]=-1;
				return 0;
			}
			if(horse[a]==20) {
				status[a]=4;
				horse[a]=3;
				break;
			}
			point=horse[a]*2;
			if(horse[a]<20 && horse[a]%5==0) {
				status[a]=horse[a]/5;
				horse[a]=0;
			}
			break;
		case 1:
			if(horse[a]>3) {
				status[a]=4;
				horse[a]-=4;
				break;
			}
			point=special1[horse[a]];
			break;
		case 2:
			if(horse[a]>2) {
				status[a]=4;
				horse[a]-=3;
				break;
			}
			point=special2[horse[a]];
			break;
		case 3:
			if(horse[a]>3) {
				status[a]=4;
				horse[a]-=4;
				break;
			}
			point=special3[horse[a]];
			break;
		}
		if(status[a]==4) {
			if(horse[a]>3) {
				horse[a]=-1;
				return 0;
			}
			point=special4[horse[a]];
		}
		for(int i=0; i<4; i++) {
			if(a==i)continue;
			
			if(horse[i]==horse[a] && status[i]==status[a]) {
				horse[a]=hor;
				status[a]=temp;
				return -1;
			}
		}
		return point;
	}	
}
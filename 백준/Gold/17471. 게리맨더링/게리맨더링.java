import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] people;
	static ArrayList<Integer>[] nodes;
	static ArrayList<Integer> player1, player2;
	static int min=Integer.MAX_VALUE;
	static int N, sum;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		sum=0;
		N=sc.nextInt();
		nodes=new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			nodes[i]=new ArrayList<>();
		}
		people=new int[N+1];
		for(int i=1; i<=N; i++) {
			people[i]=sc.nextInt();
			sum+=people[i];
		}
		for(int i=1; i<=N; i++) {
			int te=sc.nextInt();
			for(int j=0; j<te; j++) {
				nodes[i].add(sc.nextInt());
			}
		}
		
		for(int i=0; i<(1<<N); i++) {
			player1=new ArrayList<>();
			player2=new ArrayList<>();
			for(int j=0; j<N; j++) {
				if((i&(1<<j))!=0) {
					player1.add(j+1);
				}else {
					player2.add(j+1);
				}
			}
			calc();
		}
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	private static void calc() {
		//1번 집단 무결성 검사
		if(!(player1.isEmpty())) {
		Queue<Integer> p1=new LinkedList<>();
		boolean check[]=new boolean[N+1];
		p1.add(player1.get(0));
		while(!(p1.isEmpty())) {
			int a=p1.poll();
			check[a]=true;
			for(int i=0; i<nodes[a].size(); i++) {
				int b=nodes[a].get(i);
				if(player2.contains(b) || check[b]) continue;
				p1.add(b);
			}
		}
		int cnt=0;
		for(int i=1; i<=N; i++) {
			if(check[i])cnt++;
		}
		if(cnt!=player1.size()) return;
		}else return;
		//2번 집단 무결성 검사
		if(!(player2.isEmpty())) {
		Queue<Integer> p2=new LinkedList<>();
		boolean check[]=new boolean[N+1];
		p2.add(player2.get(0));
		while(!(p2.isEmpty())) {
			int a=p2.poll();
			check[a]=true;
			for(int i=0; i<nodes[a].size(); i++) {
				int b=nodes[a].get(i);
				if(player1.contains(b) || check[b]) continue;
				check[b]=true;
				p2.add(b);
			}
		}
		int cnt=0;
		for(int i=1; i<=N; i++) {
			if(check[i])cnt++;
		}
		if(cnt!=player2.size()) return;
		}else return;
		//계산
		int partsum=0;
		for(int i=0; i<player1.size(); i++) {
			partsum+=people[player1.get(i)];
		}
		min=Math.min(min, Math.abs((sum-partsum)-partsum));
	}
	
}
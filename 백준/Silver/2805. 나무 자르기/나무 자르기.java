import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] tree;
	static int target,start,end;
	static int answer;
	static int now_h;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		tree = new int[n+1];
		st = new StringTokenizer(br.readLine());
		int max=0;
		for(int i=0;i<n;i++) {
			tree[i]= Integer.parseInt(st.nextToken());
			if (max<tree[i]) {
				max=tree[i];
			}
		}
		
		// 이분 탐색 - binary search
		start = 0;
		end = max;
		
		answer = binary_search(start, end);
		System.out.println(answer);
		
	}
	private static int binary_search(int start, int end){
		if (start>end) {
//			System.out.println("실패!!");
			return now_h;
//			return 0;
		}
		int mid = (start + end)/2;
		long total=0;
//		System.out.println("현재 절단기 높이는 "+mid);
		for(int i=0;i<n;i++) {
			
			int tmp = tree[i]-mid;
	
//			System.out.println("절단중... "+tmp+"획득 ");
			if(tmp>0) {
				total+=tmp;
			}
		}
		
//		System.out.println("필요 나무 : "+m);
//		System.out.println("절단한 나무 : "+total);
		//지금은 낭비  ! 
		if(total > m) {
//			System.out.println("낭비 입니다 절단기 높히세요 !!");
			now_h=mid;
//			System.out.println(now_h);
			binary_search(mid+1,end);
		}
		else if(total == m) {
			now_h=mid;
			return now_h;
		}
		
		//지금은 낭비 아님 - 더깎아!
		else if(total<m) {
//			System.out.println("아직 나무 부족! 절단기 낮추세요 !!");
			
			binary_search(start,mid-1);
		}
		
		return now_h;
	}
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i]=sc.nextInt();
		}
		Arrays.sort(arr);
		int idx=-1;
		int idx2=-1;
		outer : for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int total = 0;
				if (i!=j) {
					for (int j2 = 0; j2 < arr.length; j2++) {
						if (j2!=i&&j2!=j) {
							total+=arr[j2];
						}
					}
					if (total==100) {
						idx=i;
						idx2=j;
						break outer;
					}
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (i!=idx&&i!=idx2) {
				System.out.println(arr[i]);
			}
		}

	}

}
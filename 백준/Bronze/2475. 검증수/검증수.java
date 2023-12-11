import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] split = inputString.split(" ");
        int total = 0;
        for (int i = 0; i < split.length; i++) {
            int i1 = Integer.parseInt(split[i]);
            total+=Math.pow(i1, 2);
        }
        System.out.println(total % 10);
    }
}
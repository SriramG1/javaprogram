package weekone;

import java.util.Scanner;

public class Looping {
    private void printPattern(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                System.out.print(" ");
            }
            int count=1;
            for(int j=1;j<=i;j++){
                System.out.print(count+" ");
                count=count*(i-j)/j;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Looping looping = new Looping();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Rows : ");
        int row =in.nextInt();
        looping.printPattern(row);
    }
}

package weekone;

import java.util.Scanner;

public class ExceptionHandling {
    private void NotProper(){

        // That make compile time error

        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter your first number : ");
            int numberOne = in.nextInt();
            System.out.println("Enter your second number : ");
            int numberTwo = in.nextInt();
            System.out.println("Value of division : "+numberOne/numberTwo);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
       /* catch (RuntimeException exception){
            exception.printStackTrace();
        }
        catch (ArithmeticException arithmeticException){
            arithmeticException.printStackTrace();
        }*/
        System.out.println("Message after the Exception");
    }
    private void handling(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter your first number : ");
            int numberOne = in.nextInt();
            System.out.println("Enter your second number : ");
            int numberTwo = in.nextInt();
            System.out.println("Value of division : "+numberOne/numberTwo);
        }
        catch (NumberFormatException numberFormatException){
            numberFormatException.printStackTrace();
        }
        catch (ArithmeticException arithmeticException){
            arithmeticException.printStackTrace();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("Message after the Exception");
    }
    public static void main(String[] args) {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling.handling();
        exceptionHandling.NotProper();
    }
}

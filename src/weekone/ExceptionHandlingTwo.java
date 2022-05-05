package weekone;


import java.util.InputMismatchException;
import java.util.Scanner;

class Parent{
    void exception(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter your first number : ");
            int numberOne = in.nextInt();
            System.out.println("Enter your second number : ");
            int numberTwo = in.nextInt();
            System.out.println("Value of division : "+numberOne/numberTwo);
        }
        catch (InputMismatchException inputMismatchException){
            System.err.println("InputMismatch exception handled");
        }
    }
}
class Child extends Parent{
    void caller(){
        try {
            exception();
        }
        catch (Exception exception){
            System.err.println("Exception handled");
        }
    }
}
public class ExceptionHandlingTwo {
    public static void main(String[] args) {
        Child child = new Child();
        child.caller();
    }
}

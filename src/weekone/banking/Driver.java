package weekone.banking;

import java.util.Scanner;

public class Driver {
    Scanner in = new Scanner(System.in);
    boolean exit=true;
    public void register(){

    }
    public void deposit(){

    }
    public void withdraw(){

    }
    public void display(){

    }
    public void mainMenu(){
        System.out.println("""
                Enter your option :
                 (1) - For Register
                 (2) - For Deposit
                 (3) - For Withdraw
                 (4) - For Display User Details
                 (5) - For Exit""");
        char option =in.next().charAt(0);
        switch (option) {
            case '1' -> register();
            case '2' -> deposit();
            case '3' -> withdraw();
            case '4' -> display();
            case '5' -> exit=false;
            default -> {
                System.err.println("Your option is not valid enter valid option");
                mainMenu();
            }
        }
    }
    public static void main(String[] args) {
        Driver driver = new Driver();
        while (driver.exit){
            driver.mainMenu();
        }
    }
}

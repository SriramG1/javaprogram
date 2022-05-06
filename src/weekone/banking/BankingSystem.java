package weekone.banking;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class BankingSystem {

    private final Scanner in = new Scanner(System.in);
    private boolean exit=true;
    private static final String PATH="C:\\Users\\sys\\Desktop\\Banking\\bankingDetails.txt";

    private boolean validAccountNumber(String accountNumber) {
        if(accountNumber.length()!=6){
            return false;
        }
        for(int i=0;i<accountNumber.length();i++){
            if(!Character.isDigit(accountNumber.charAt(i))){
                return false;
            }
        }
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(PATH);
            reader = new BufferedReader(fileReader);
            String str = reader.readLine();
            while (str != null) {
                Details details = stringToJson(str);
                if(details.getAccountNumber() != null) {
                    if (details.getAccountNumber().equals(accountNumber)) {
                        return false;
                    }
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( fileReader != null) {
                    fileReader.close();
                }
                if( reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    private void readFile(String accountNumber){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(PATH);
            bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while (str != null) {
                Details details = stringToJson(str);
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Details stringToJson(String str){
        Gson gson = new Gson();
        return gson.fromJson(str,Details.class);
    }
    private Details getInput(){
        Details details = new Details();
        System.out.println("Enter Your name : ");
        details.setName(in.next());
        boolean isValid;
        do {
            System.out.println("Enter Your Account number (6 - digit number): ");
            String accountNumber=in.next();
            if(validAccountNumber(accountNumber)){
                details.setAccountNumber(accountNumber);
                isValid=false;
            }
            else {
                System.err.println("Enter Valid Number...");
                isValid=true;
            }
        }while (isValid);
        String accountType="";
        boolean isContinue;
        do {
            System.out.println("Enter which type of account of account : \n(1) -> Savings \n(2) -> Current");
            char option = in.next().charAt(0);
            if (option == '1') {
                accountType = "Savings";
                isContinue=false;
            } else if (option == '2') {
                accountType = "Current";
                isContinue=false;
            }
            else {
                isContinue=true;
            }
        }while (isContinue);
        details.setAccountType(accountType);
        System.out.println("Enter Your initial balance  : ");
        try {
            details.setBalance(in.nextInt());
        }
        catch (InputMismatchException inputMismatchException){
            inputMismatchException.printStackTrace();
            System.err.println("Enter valid amount . . .");
            getInput();
        }
        return details;
    }
    private void valueToFile(Details details){
        Gson gson = new Gson();
        String json=gson.toJson(details);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(PATH,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(json);
            printWriter.println();
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void register(){
        Details details = getInput();
        valueToFile(details);
    }
    private void transaction(int key){
        System.out.println("Enter Your account number : ");

    }
    private void displayDetails(String accountNumber){

    }
    private void mainMenu() {
        System.out.println("""
                Enter your option :
                 (1) - For Register
                 (2) - For Deposit
                 (3) - For Withdraw
                 (4) - For Display User Details
                 (5) - For Exit""");
        char option = in.next().charAt(0);
        switch (option) {
            case '1' -> register();
            case '2' -> transaction(1);
            case '3' -> transaction(2);
            case '4' -> {
                System.out.println("Enter Account Number : ");
                String accountNumber = in.next();
                displayDetails(accountNumber);
            }
            case '5' -> exit = false;
            default -> {
                System.err.println("Your option is not valid enter valid option");
                mainMenu();
            }
        }
    }
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        while (system.exit){
            system.mainMenu();
        }
    }
}

package weekone.bankingsystem;

import com.google.gson.Gson;
import weekone.banking.Details;

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

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
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
                if( reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    private Details readFile(String accountNumber){
        BufferedReader bufferedReader = null;
        Details details = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH));
            String str = bufferedReader.readLine();
            while (str != null) {
                details = stringToJson(str);
                if(accountNumber.equals(details.getAccountNumber())){
                    return details;
                }
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return details;
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
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(PATH, true)))) {
            printWriter.print(json);
            printWriter.println();
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void register(){
        Details details = getInput();
        valueToFile(details);
    }
    private String readFile(){
        BufferedReader reader = null;
        StringBuilder details= new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                details.append(currentLine).append("\n");
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return details.toString();
    }
    private String userString(String accountNumber){
        Details details = readFile(accountNumber);
        return new Gson().toJson(details);
    }
    private String replace(String user,int amount,int key){
        Details details = stringToJson(user);
        System.out.println(details.getBalance());
        if(key == 1) {
            details.setBalance(details.getBalance() + amount);
        }
        else {
            if(details.getBalance()>=amount){
                details.setBalance(details.getBalance() - amount);
            }
            else {
                System.err.println("Invalid balance . . .");
            }
        }
        return new Gson().toJson(details);
    }
    private String changeAmount(String details,String accountNumber,int amount,int key){
        BufferedReader reader ;
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String user = userString(accountNumber);
                if (currentLine.equals(user)) {
                    details = details.replace(user,replace(user,amount,key));
                }
                currentLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }
    private void writeFile(String details){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(PATH)))) {
            writer.write(details);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void transaction(int key){
        System.out.println("Enter Your account number : ");
        String accountNumber=  in.next();
        String details = readFile();
        displayDetails(accountNumber);
        if(key == 1) {
            System.out.println("Enter your deposit amount : ");
        }
        else {
            System.out.println("Enter your withdraw amount : ");
        }
        int amount;
        try {
            amount = in.nextInt();
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Your amount is invalid");
            transaction(key);
            inputMismatchException.printStackTrace();
            return;
        }
        details = changeAmount(details,accountNumber,amount,key);
        writeFile(details);
        displayDetails(accountNumber);
    }
    private void displayDetails(String accountNumber){
        Details details = readFile(accountNumber);
        if(details != null) {
            System.out.println("\n-----Your details showing below-----");
            System.out.println("Name            ->  "+details.getName());
            System.out.println("Account Number  ->  "+details.getAccountNumber());
            System.out.println("Type of account ->  "+details.getAccountType());
            System.out.println("Balance         ->  "+details.getBalance()+"\n");
        }
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
                System.out.println("Enter Your accountNumber : ");
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

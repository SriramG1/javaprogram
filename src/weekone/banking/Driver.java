package weekone.banking;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private final Scanner in = new Scanner(System.in);
    private boolean exit=true;
    private static final String PATH="C:\\Users\\sys\\Desktop\\Banking\\banking.txt";

    @SuppressWarnings("unchecked")
    private boolean validAccountNumber(String accountNumber) {
        if(accountNumber.length()!=6){
            return false;
        }
        for(int i=0;i<accountNumber.length();i++){
            if(!Character.isDigit(accountNumber.charAt(i))){
                return false;
            }
        }
        try {
            FileInputStream inputStream = new FileInputStream(PATH);
            ObjectInputStream stream = new ObjectInputStream(inputStream);
            ArrayList<Details> list = (ArrayList<Details>) stream.readObject();
            for(Details details : list){
                if(details.getAccountNumber()!=null) {
                    if (details.getAccountNumber().equals(accountNumber)) {
                        return false;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return true;
    }
    private Details getDetails(){
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
            getDetails();
        }
        return details;
    }

    @SuppressWarnings("unchecked")
    private void register(){
        FileInputStream fileInputStream = null;
        ObjectInputStream inputStream = null;
        FileOutputStream outputStream = null;
        ObjectOutputStream stream = null;
        try {
            fileInputStream = new FileInputStream(PATH);
            inputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Details> list = (ArrayList<Details>) inputStream.readObject();
            Details details = getDetails();
            outputStream = new FileOutputStream(PATH);
            stream = new ObjectOutputStream(outputStream);
            list.add(details);
            stream.writeObject(list);
            outputStream.flush();
            stream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
                if(stream != null){
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void transaction(int key){
        System.out.println("Enter Account Number : ");
        String accountNumber = in.next();
        displayDetails(accountNumber);
        System.out.println("Enter amount for transaction : ");
        int amount = in.nextInt();
        FileInputStream inputStream = null;
        ObjectInputStream stream = null;
        FileOutputStream outputStream = null;
        ObjectOutputStream stream1 = null;
        try {
            inputStream = new FileInputStream(PATH);
            stream = new ObjectInputStream(inputStream);
            ArrayList<Details> list = (ArrayList<Details>) stream.readObject();
            for(Details details : list){
                if(details.getAccountNumber()!=null) {
                    if (details.getAccountNumber().equals(accountNumber)) {
                        int balance = details.getBalance();
                        if(key==1) {
                            details.setBalance(balance + amount);
                        }
                        else {
                            if(balance >= amount){
                                details.setBalance(balance-amount);
                            }
                            else {
                                System.err.println("Account balance low than withdraw amount...");
                            }
                        }
                    }
                }
            }
            outputStream = new FileOutputStream(PATH);
            stream1 = new ObjectOutputStream(outputStream);
            stream1.writeObject(list);
            outputStream.flush();
            stream1.flush();
            displayDetails(accountNumber);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (outputStream != null ) {
                    outputStream.close();
                }
                if(inputStream != null ){
                    inputStream.close();
                }
                if(stream != null){
                    stream.close();
                }
                if(stream1 != null){
                    stream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void displayDetails(String accountNumber){
        FileInputStream inputStream = null;
        ObjectInputStream stream = null;
        try {
            System.err.println("----- Your details showing below -----");
            inputStream = new FileInputStream(PATH);
            stream = new ObjectInputStream(inputStream);
            ArrayList<Details> list = (ArrayList<Details>) stream.readObject();
            for(Details details : list){
                if(details.getAccountNumber()!=null) {
                    if (details.getAccountNumber().equals(accountNumber)) {
                        System.out.println("\nName of the user -> " + details.getName());
                        System.out.println("Account Number   -> " + details.getAccountNumber());
                        System.out.println("Account Type     -> " + details.getAccountType());
                        System.out.println("Balance          -> Rs." + details.getBalance()+"\n");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if(stream != null){
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void mainMenu(){
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
            case '2' -> transaction(1);
            case '3' -> transaction(2);
            case '4' -> {
                System.out.println("Enter Account Number : ");
                String accountNumber = in.next();
                displayDetails(accountNumber);
            }
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

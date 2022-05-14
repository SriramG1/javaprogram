package weekone.bankingsystem.usingjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


@SuppressWarnings("unchecked")
public class BankingSystemTwo {
    private static final Scanner in = new Scanner(System.in);
    public static final String PATH = "C:\\Users\\sys\\Desktop\\Banking\\JsonFormat.json";
    boolean exit = true;
    private boolean validAccountNumber(String accountNumber){
        if(accountNumber.length()!=6){
            return false;
        }
        for(int i=0;i<accountNumber.length();i++){
            if(!Character.isDigit(accountNumber.charAt(i))){
                return false;
            }
        }
        JSONArray jsonArray = readJson();
        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;
            if(jsonObject.get("accountNumber").equals(accountNumber)){
                return false;
            }
        }
        return true;
    }
    private UserDetails getUserInput(){
        UserDetails details = new UserDetails();
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
        do {
            System.out.println("Enter Your initial balance  : ");
            String balance = in.next();
            for(int i=0;i<balance.length();i++){
                isContinue = false;
                if(Character.isAlphabetic(balance.charAt(i))) {
                    isContinue = true;
                    System.out.println("Invalid balance...");
                }
            }
            details.setBalance(balance);
        }while(isContinue);
        return details;
    }
    private void register(UserDetails details){
        JSONObject object = new JSONObject();
        object.put("name",details.getName());
        object.put("accountNumber",details.getAccountNumber());
        object.put("accountType",details.getAccountType());
        object.put("balance",details.getBalance());
        JSONArray jsonArray = readJson();
        jsonArray.add(object);
        writeJson(jsonArray);
    }

    private void register(){
        UserDetails details = getUserInput();
        register(details);
        System.out.println("\nRegister successfully ... \n");
    }
    private void transaction(int key){
        JSONArray jsonArray = readJson();
        System.out.println("Enter your account number : ");
        String accountNumber = in.next();
        displayDetails(accountNumber);
        if(key == 1) {
            System.out.println("Enter amount for deposit : ");
        }else {
            System.out.println("Enter amount for withdraw : ");
        }
        int amount = 0;
        try {
            amount = in.nextInt();
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Enter valid amount...");
            transaction(key);
        }
        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject)object;
            if(accountNumber.equals(jsonObject.get("accountNumber"))) {
                int balance = Integer.parseInt(String.valueOf(jsonObject.get("balance")));
                if(key ==  1) {
                    jsonObject.put("balance", balance + amount);
                }
                else {
                    if(balance > amount){
                        jsonObject.put("balance", balance - amount);
                    }
                    else {
                        System.out.println("Invalid balance ... ");
                    }
                }
            }
        }
        writeJson(jsonArray);
        displayDetails(accountNumber);
    }
    private JSONArray readJson(){
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray =(JSONArray)jsonParser.parse(new FileReader(PATH));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
    private void writeJson(JSONArray jsonArray){
        FileWriter writer = null;
        try {
            writer = new FileWriter(PATH);
            writer.write(jsonArray.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayDetails(String accountNumber){
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(PATH));
            boolean isEmpty = true;
            for(Object object : jsonArray){
                JSONObject jsonObject = (JSONObject) object;
                if(accountNumber.equals(jsonObject.get("accountNumber"))) {
                    System.out.println("\nDetails showing below : ");
                    System.out.println("\nName of customer    -> " + jsonObject.get("name"));
                    System.out.println("Account Number      -> " + jsonObject.get("accountNumber"));
                    System.out.println("Account Type        -> " + jsonObject.get("accountType"));
                    System.out.println("Balance             -> " + jsonObject.get("balance") + "\n");
                    isEmpty = false;
                }
            }
            if(isEmpty){
                System.err.println("-----Invalid accountNumber-----");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
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
        BankingSystemTwo banking =  new BankingSystemTwo();
        while (banking.exit) {
            banking.mainMenu();
        }
    }
}

package weekone;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesFile {
    public static final String PATH="C:\\Users\\sys\\javaprogram\\src\\weekone\\student.properties";
    private void getDetails(Properties properties){
        Scanner in = new Scanner(System.in);
        boolean isContinued=true;
        do {
            System.out.println("Enter Student name : ");
            String name =in.next();
            System.out.println("Enter student ID : ");
            String id =in.next();
            properties.setProperty(id,name);
            System.out.println("Enter (1) for more register...\n press any key for skip...");
            char option = in.next().charAt(0);
            if(option!='1'){
                isContinued=false;
            }
        }while (isContinued);
    }
    private void displayDetails(){
        try {
            Properties properties = new Properties();
            FileInputStream stream = new FileInputStream(PATH);
            properties.load(stream);
            Enumeration<Object> enumeration = properties.keys();
            while (enumeration.hasMoreElements()){
                String id = (String) enumeration.nextElement();
                String name = properties.getProperty(id);
                System.out.println("ID of the student : "+id);
                System.out.println("Name of the student : "+name+"\n");
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        PropertiesFile propertiesFile = new PropertiesFile();
        try {
            Properties properties = new Properties();
            FileOutputStream stream = new FileOutputStream(PATH,true);
            propertiesFile.getDetails(properties);
            properties.store(stream,null);
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        propertiesFile.displayDetails();
    }
}

package weekone;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling {
    public static final String PATH="src\\weekone\\";
    private void write(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Adding some text into the file");
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void copyFile(File file) throws IOException {
        File newFile=fileCreation();
        try {
            FileWriter writer = new FileWriter(newFile);
            FileReader reader = new FileReader(file);
            int text;
            while ((text=reader.read())!=-1){
                writer.write(text);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void display(File file){
        if(file.canRead()){
            System.out.println("Your file is readable");
        }
        else {
            System.out.println("your file is not readable");
        }

        if(file.canWrite()){
            System.out.println("Your file is Writable");
        }
        else {
            System.out.println("your file is not writable");
        }
        String fileName = file.getName();
        int index=fileName.indexOf('.');
        if(index>0){
            System.out.println("File extension is : ("+fileName.substring(index)+")");
        }
        System.out.println("Length of the file is : "+file.length());
    }
    private File fileCreation() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name for file : ");
        String name=in.next();
        System.out.println("Enter Your file extension : ");
        String extension=in.next();
        String fileName=name+"."+extension;
        File file=new File(PATH+fileName);
        boolean status = file.exists();
        if(status){
            System.out.println("File is already exist...");
        }
        else {
            if(file.createNewFile()) {
                System.out.println("New File is created...");
            }
        }
        return file;
    }
    public static void main(String[] args) throws IOException {
        FileHandling fileHandling = new FileHandling();
        File file=fileHandling.fileCreation();
        fileHandling.write(file);
        fileHandling.display(file);
        fileHandling.copyFile(file);
    }
}

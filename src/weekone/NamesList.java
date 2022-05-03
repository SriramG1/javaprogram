package weekone;

import java.util.LinkedList;
import java.util.Scanner;

public class NamesList {
    LinkedList<String> list = new LinkedList<>();
    Scanner in = new Scanner(System.in);
    private boolean duplicateChecker(String name){
        for(String str:list){
            if(str.equals(name)){
                return false;
            }
        }
        return true;
    }
    private void storeInput(){
        System.out.println("Enter your limit : ");
        int limit = in.nextInt();
        for(int i=0;i<limit;i++) {
            String name=in.next();
            if (duplicateChecker(name)) {
                list.add(name);
            }
        }
    }
    private void nameSearch(){
        System.out.println("Enter name for search : ");
        String str=in.next();
        int i=0;
        boolean flag=true;
        for(String name : list){
            if(name.equals(str)){
                flag=false;
                System.out.println(name+" in "+i+" th position");
            }
            i++;
        }
        if(flag){
            System.err.println("Name not found ... !");
        }
    }
    private void printList(){
       for(String names : list){
           System.out.println(names);
       }
    }
    public static void main(String[] args) {
        NamesList namesList = new NamesList();
        namesList.storeInput();
        namesList.printList();
        namesList.nameSearch();
    }
}

package weekone;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class LinkedListProgram implements Comparator<Integer> {
    Scanner in = new Scanner(System.in);

    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }

    private void getInput(int length,LinkedList<Integer> list){
        for(int i=0;i<length;i++){
            list.add(in.nextInt());
        }
    }
    private void sort(LinkedList<Integer> list){
        LinkedListProgram listProgram = new LinkedListProgram();
        list.sort(listProgram);
    }
    private void maxAndMin(LinkedList<Integer> list){
        System.out.println("Minimum value of linked list is : \n"+list.get(0));
        System.out.println("Maximum value of linked list is : \n"+list.get(list.size()-1));
    }
    private void print(LinkedList<Integer> list){
        System.out.println(list);
    }
    public static void main(String[] args) {
        LinkedListProgram obj = new LinkedListProgram();
        LinkedList<Integer> list = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter length : ");
        int length=in.nextInt();
        obj.getInput(length,list);
        System.out.println("Before Sort");
        obj.print(list);
        obj.sort(list);
        System.out.println("After Sort");
        obj.print(list);
        obj.maxAndMin(list);
    }
}

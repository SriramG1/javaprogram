package weekone;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class StringTokenize {
    private void convertor(){
        Scanner in = new Scanner(System.in);
        TreeSet<String> treeSet = new TreeSet<>();
        System.out.println("Enter your String : ");
        String text=in.nextLine();
        StringTokenizer stringTokenize = new StringTokenizer(text," ");
        while (stringTokenize.hasMoreTokens()){
            treeSet.add(stringTokenize.nextToken());
        }
        System.out.println(treeSet);
    }
    public static void main(String[] args) {
        StringTokenize str=new StringTokenize();
        str.convertor();
    }
}

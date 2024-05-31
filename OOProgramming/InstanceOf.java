package OOProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Fan{ }
class Rockstar{ }
class Hacker{ }


public class InstanceOf{

    static String count(ArrayList myList){
        int a = 0,b = 0,c = 0;
        for(int i = 0; i < myList.size(); i++){
            Object element=myList.get(i);
            if(element instanceof Fan) //instanceof conditional
                a++;
            if(element instanceof Rockstar)//instanceof conditional
                b++;
            if(element instanceof Hacker)//instanceof conditional
                c++;
        }
        String ret = Integer.toString(a)+" "+ Integer.toString(b)+" "+ Integer.toString(c);
        return ret;
    }

    public static void main(String []args) throws FileNotFoundException {
        ArrayList myList = new ArrayList();
        String file = "src/main/resources/instanceOfTest";
        Scanner scan = new Scanner(new File(file));
        int t = scan.nextInt();
        for(int i=0; i<t; i++){
            String s = scan.next();
            if(s.equals("OOProgramming.Fan"))myList.add(new Fan());
            if(s.equals("OOProgramming.Rockstar"))myList.add(new Rockstar());
            if(s.equals("OOProgramming.Hacker"))myList.add(new Hacker());
        }
        System.out.println(count(myList));
        System.out.println("Hello World!");
    }
}

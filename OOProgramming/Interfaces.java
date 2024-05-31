package OOProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interfaces {

    public static void main(String[] args) throws FileNotFoundException {
        useInterfaces();
    }

    public static void useInterfaces() throws FileNotFoundException {
        String file = "src/main/resources/interfacesTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        MyCalculator2 my_calculator = new MyCalculator2();
        System.out.print("I implemented: ");
        ImplementedInterfaceNames(my_calculator);
        System.out.print(my_calculator.divisor_sum(n) + "\n");

    }

    // This method takes an Object and provides a print out of its implemented interfaces from a created list.
    static void ImplementedInterfaceNames(Object o) {
        Class[] theInterfaces = o.getClass().getInterfaces();
        for (int i = 0; i < theInterfaces.length; i++) {
            String interfaceName = theInterfaces[i].getName();
            System.out.println(interfaceName);
        }


    }
}

interface AdvancedArithmetic {
    int divisor_sum(int n); //Interface class provides methods to be implemented
}

class MyCalculator2 implements AdvancedArithmetic {

    public int divisor_sum(int n){ //must be implemented
        int total = 0;
        for (int i = 1; i <= n ; i++) {
            if(n % i == 0) {total += i;} //sums up all divisors of the num.
        }
        return total;
    }

}

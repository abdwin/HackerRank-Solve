package ExceptionHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

class MyCalculator {
    long power(int n, int p) throws Exception {
        if (n<0 || p<0){throw new Exception("n or p should not be negative.");}
        if (n==0 && p==0){throw new Exception("n and p should not be zero.");}
        return (long) Math.pow(n,p);
    }

}


public class ExceptionHandling {
    public static final MyCalculator my_calculator = new MyCalculator();

    public static void main(String[] args) throws FileNotFoundException {
        String file = "src/main/resources/exceptionTest";
        Scanner scan = new Scanner(new File(file));
            try {
                int x = scan.nextInt();
                int y = scan.nextInt();
//                System.out.println(my_calculator.power(x, y));
                System.out.println(x/y);
            } catch (InputMismatchException e) {
                System.out.println(e);
            } catch (ArithmeticException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                scan.close();
            }

        }
    }


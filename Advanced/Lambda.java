package Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

 public class Lambda {

     public static void main(String[] args) throws FileNotFoundException {
         MyMath lambda = new MyMath();
         PerformOperation op;
         boolean ret = false;
         String ans = null;
         String file = "src/main/resources/lambdaTest";
         Scanner scan = new Scanner(new File(file));
         int queries = scan.nextInt();
         for (int i = 1; i <= queries; i++) {
             int option = scan.nextInt();
             int num = scan.nextInt();
             if (option == 1) {
                 op = lambda.isOdd();
                 ret = lambda.checker(op, num);
                 ans = (ret) ? "ODD" : "EVEN";
             } else if (option == 2) {
                 op = lambda.isPrime();
                 ret = lambda.checker(op, num);
                 ans = (ret) ? "PRIME" : "COMPOSITE";
             } else if (option == 3) {
                 op = lambda.isPalindrome();
                 ret = lambda.checker(op, num);
                 ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
             }
             System.out.println(ans);
         }
     }

}

//Advanced.PerformOperation Interface provides method which takes in a num and returns a boolean.
interface PerformOperation {
    boolean check(int n);
}

class MyMath {

    // checker method takes a Advanced.PerformOperation and a Number as input to run and return check method
    // -> which in turn runs and returns isOdd/isPrime/isPalindrome against the num.
    public boolean checker(PerformOperation op, int num) {
        return op.check(num);
    }

    public PerformOperation isOdd() {
        return ((int num) -> num % 2 == 1);
    }

    public PerformOperation isPrime() {
        return ((int num) -> {
            if (num <= 2) return true;
            int divisor = 2;
            while (divisor < Math.sqrt(num)) {
                if (num % divisor == 0) {
                    return false;
                }
                divisor++;
            }
            return true;
        });
    }

    public PerformOperation isPalindrome() {
        return ((int num) -> {
            String forward = String.valueOf(num);
            String reverse = new StringBuilder(forward).reverse().toString();
            return (reverse.equals(forward));
        });
    }
}









package BigNumber;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class BigTypes {

    public static void main(String[] args) throws FileNotFoundException {
//        usePrimeCheck();
//        useBigAdd();
        useBigDecimal();
    }

    public static void usePrimeCheck() throws FileNotFoundException {
        String file = "src/main/resources/primeTest";
        Scanner scan = new Scanner(new File(file));
        while (scan.hasNext()) {
            String num = scan.nextLine(); //reads in as String
            BigInteger big = new BigInteger(num); //takes a String argument because number is BIG.
            String result = (big.isProbablePrime(1)) ? "Prime" : "Not prime";
            System.out.println(result);
        }
    }

    public static void useBigAdd() throws FileNotFoundException {
        String file = "src/main/resources/bigAddTest";
        Scanner scan = new Scanner(new File(file));
        String a = scan.nextLine();
        String b = scan.nextLine();
        scan.close();
        BigInteger bigA = new BigInteger(a);
        BigInteger bigB = new BigInteger(b);
        BigInteger result1 = bigA.add(bigB); // BigInteger provides math methods to deal with BIG numbers.
        BigInteger result2 = bigA.multiply(bigB);
        System.out.println(result1);
        System.out.println(result2);
    }

    // this was hard. In the end used an on-the-fly comparator to organise List<String>
    // if two BigDecimals are hte same - it would retain the order of the entry.
    // Note ArrayList does not guarantee entry order.
    public static void useBigDecimal() throws FileNotFoundException {
        String file = "src/main/resources/bigDecimalTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        scan.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String string = scan.nextLine();
            list.add(string);
        }
        // Build comparator comparing two objects. Returns 1 (original order) if the same.
        // couldn't use Method references within a static context.
        Comparator bigReverseCompare = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                BigDecimal A = new BigDecimal(o1);
                BigDecimal B = new BigDecimal(o2);
                return B.compareTo(A) == 0? 1: B.compareTo(A);
            }
        };
        list.sort(bigReverseCompare);
        list.forEach(System.out::println);

        }

    }





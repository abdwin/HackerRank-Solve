package Introduction;

import java.io.*;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


public class Introduction {

    public static void main(String[] args) throws FileNotFoundException {
//        stdInOut();
//        ifElse();
//        stdInOut2();
//        formatting();
//        loops1();
//        loops2();
//        dataTypes();
//        endOfFile();
//        convertIntToString();
//        dateTime();
        currencyFormat();
    }

    private static void stdInOut() {
        Scanner scan = new Scanner(System.in);
        for(int i=0; i<3; i++) {
            int num = scan.nextInt();
            System.out.println(num);
        }
        scan.close();
    }

    private static void ifElse() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        if(N % 2 != 0) {
            System.out.println("Weird");
        } else {
            if(N <= 5){
                System.out.println("Not Weird");
            } else if (N <= 20) {
                System.out.println("Weird");
            } else {
                System.out.println("Not Weird");
            }
        }
        bufferedReader.close();
    }

    private static void stdInOut2() throws FileNotFoundException {
        String file = "src/main/resources/stdInOutTest";
        Scanner scan = new Scanner(new File(file));
        int number = scan.nextInt();
        double doubleOut = scan.nextDouble();
//      nextDouble() does not scan to end of line so need to scan remaining line before nextLine for expected string.
//      if I had .next() here it would pick up the next String token but only one word with default delimiter.
        scan.nextLine();
        String stringOut = scan.nextLine();
        scan.close();
        System.out.println("String:" + " " + stringOut);
        System.out.println("Double:" + " " + doubleOut);
        System.out.println("Int:" + " " + number);
    }

    private static void formatting() throws FileNotFoundException {
        String file = "src/main/resources/formattingTest";
        Scanner scan = new Scanner(new File(file));
        String pretty = "================================";
        System.out.println(pretty);
        while(scan.hasNext()) {
            String rowString = scan.next();
            int rowNum = scan.nextInt();
            System.out.printf("%-15s%03d%n", rowString, rowNum);
        }
        System.out.println(pretty);
        scan.close();

    }

    private static void loops1() throws FileNotFoundException {
        String file = "src/main/resources/loopsTest";
        Scanner scan = new Scanner(new File(file));
        int num = scan.nextInt();
        for(int i=1; i<=10; i++){
            System.out.println(num + " x " + i + " = " + num*i);
        }
    }

    private static void loops2() throws FileNotFoundException {
        String file = "src/main/resources/loopsTest";
        Scanner scan = new Scanner(new File(file));
        int q = scan.nextInt();
        for(int i=1; i<=q; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int n = scan.nextInt();
            int result = a;
            for (int j = 0; j < n; j++) {
                result += Math.pow(2,j)*b;
                System.out.print(result + " ");
            }
            System.out.println();
        }
    }

    private static void dataTypes() throws FileNotFoundException {
        String file = "src/main/resources/dataTypeTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        for(int i=1; i<=n; i++) {
            try{
                long num = scan.nextLong();
                System.out.println(num + " " + "can be fitted in:");
                if(Byte.MIN_VALUE<=num && num<=Byte.MAX_VALUE){System.out.println("* byte");}
                if(Short.MIN_VALUE<=num && num<=Short.MAX_VALUE){System.out.println("* short");}
                if(Integer.MIN_VALUE<=num && num<=Integer.MAX_VALUE){System.out.println("* int");}
                System.out.println("* long");
            } catch(Exception e) {
                String num = scan.next();
                System.out.println(num + " " + "can't be fitted anywhere.");
            }
        }
        scan.close();
    }

    public static void endOfFile() throws FileNotFoundException {
        String file = "src/main/resources/endOfFileTest";
        Scanner scan = new Scanner(new File(file));
        int line = 1;
        while(scan.hasNext()){
            String text = scan.nextLine();
            System.out.println(line + " " + text);
            line++;
        }
        scan.close();
    }

    // Static Block Initialisation - when this Class is initialised it will run the static block once.
    static {
        String file = "src/main/resources/staticBlockTest";
        Scanner scan;
        try {
            scan = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int B = scan.nextInt(); //breadth
        int H = scan.nextInt(); //height
        if(B<=0 || H<=0){
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        } else {
            System.out.println(B * H);
        }
        scan.close();
    }

    public static void convertIntToString() throws FileNotFoundException {
        String file = "src/main/resources/endOfFileTest";
        Scanner scan = new Scanner(new File(file));
        int num = scan.nextInt();
        try{
            String text = String.valueOf(num);
            System.out.println("Good job");
        } catch(Exception e){
            System.out.println("Wrong answer");
        }
        scan.close();
    }

    public static String dateTime() throws FileNotFoundException {
        String file = "src/main/resources/dateTimeTest";
        Scanner scan = new Scanner(new File(file));
        int day = scan.nextInt();
        int month = scan.nextInt();
        int year = scan.nextInt();
        scan.close();
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().name();
    }

    public static void currencyFormat() throws FileNotFoundException {
        String file = "src/main/resources/currencyTest";
        Scanner scan = new Scanner(new File(file));
        double money = scan.nextDouble();
        scan.close();
        List<Locale> locales = new ArrayList<>();
        // needed to set Locale variant for "US" to ensure the country name string presented was "US".
        Collections.addAll(locales, new Locale("en", "US", "US"), new Locale("en", "IN"), Locale.CHINA, Locale.FRANCE);
        for(Locale country: locales){
            // use getVariant() if the country has one. Otherwise use getDisplayCountry().
            String variant = (country.getVariant()!="")? country.getVariant() : country.getDisplayCountry();
            System.out.println(variant + ": " + NumberFormat.getCurrencyInstance(country).format(money));
        }
    }



}

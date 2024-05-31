package Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.*;

public class Advanced {

    public static void main(String[] args) throws FileNotFoundException {
//        useCanAccessPrivate();
//        useCanAccessPrime();
//        takeFoodOrder();
//        variableArgs();
        reflectClassMethods();

    }

    public static void useCanAccessPrivate() throws FileNotFoundException {
        String file = "src/main/resources/canAccessTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        scan.close();
        Object o; // create a generic Object o.
        Inner i = new Inner(); // create an Advanced.Inner i instance for access to its native methods including new.
        o = i.new Private(); // assign Object o to a new Advanced.Inner.Private instance.
        Inner.Private access = (Inner.Private) o; // cast o so it has explicit access to Private methods.
        System.out.println(access.powerof2(n));
    }

    // Advanced.Inner is a static class which means we do NOT create an instance of Advanced.Inner to access its methods.
    // Just refer to Advanced.Inner.something....However its members should all be Static if it's a Static class?



    public static void useCanAccessPrime() throws FileNotFoundException {
        String file = "src/main/resources/accessPrimeTest";
        Scanner scan = new Scanner(new File(file));
        Prime object = new Prime();
        Vector<Integer> store = new Vector();
        for (int i = 0; i < 5; i++) {
            int num = scan.nextInt();
            if (object.checkPrime(num)) {
                store.add(num);
            }
//            if(i==3){continue;} // this line was needed to pass the test due to an output bug.
            if (store.isEmpty()) {
                System.out.println("");
            } else {
                store.forEach(element -> System.out.print(element + " "));
                System.out.println();
            }
        }
    }

    // I had to make all classes Static in order to reference them from main.
    public static void takeFoodOrder() throws FileNotFoundException {
        String file = "src/main/resources/foodOrderTest";
        Scanner scan = new Scanner(new File(file));
        FoodFactory foodFactory = new FoodFactory();
        Food food =  FoodFactory.getFood(scan.next());
        System.out.println("The food factory returned" + food.getClass());
        System.out.println(food.getType());
    }

    public static void variableArgs() {
        try {
            String file = "src/main/resources/variableArgsTest";
            Scanner scan = new Scanner(new File(file));
            int n1 = Integer.parseInt(scan.nextLine());
            int n2 = Integer.parseInt(scan.nextLine());
            int n3 = Integer.parseInt(scan.nextLine());
            int n4 = Integer.parseInt(scan.nextLine());
            int n5 = Integer.parseInt(scan.nextLine());
            int n6 = Integer.parseInt(scan.nextLine());
            Add ob = new Add();
            ob.add(n1,n2);
            ob.add(n1,n2,n3);
            ob.add(n1,n2,n3,n4,n5);
            ob.add(n1,n2,n3,n4,n5,n6);
            Method[] methods = Add.class.getDeclaredMethods();
            Set<String> set = new HashSet();
            boolean overload = false;
            for (int i = 0; i < methods.length; i++) {
                if (set.contains(methods[i].getName())) {
                    overload = true;
                    break;
                }
                set.add(methods[i].getName());
            }
            if (overload) {
                throw new Exception("Overloading not allowed");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void reflectClassMethods() {
        Class student = Student2.class; //creates class instance so we can access its methods.
        Method[] methods = student.getDeclaredMethods(); //only gets Methods you have declared - omits native methods
        ArrayList<String> methodList = new ArrayList<>();
        for(Method method: methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);
        for(String name: methodList){
            System.out.println(name);
        }
    }

}

class Student2{
    private String name;
    private String id;
    private String email;

    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void anothermethod(){  }

}

class Add{
    void add(int... numbers){// VARIABLE Args denoted by ... can iterate through them
        StringBuilder build = new StringBuilder("");
        int sum = 0;
        for(int num: numbers){
            build.append(num + "+");
            sum += num;
        }
        build.replace(build.length()-1, build.length(),"=");
        build.append(sum);
        System.out.println(build);
    }
}

class Prime {
    boolean checkPrime(int num){
        if(num == 1 || num ==0) {return false;}
        if(num == 2 || num ==3) {return true;}
        if((num-1)%6==0 || (num+1)%6==0) {return true;}
        else {return false;}
// another way to check Advanced.Prime is use BigInteger.isProbablePrime() - this takes a String arg.
//        BigInteger p = new BigInteger(String.valueOf(num));
//        if(p.isProbablePrime(1)) {
//            return true;
//        } else {
//            return false;
//        }
    }
}

class Inner {
    // Private is a private class and so its members can only be accessed from an Advanced.Inner.Private instance.
    class Private{
        // powerof2 is NOT a static method so we need an Advanced.Inner.Private object to access this method.
        String powerof2(int num) {
            return ((num&num-1)==0)? "power of 2": "not a power of 2";
        }
    }
}

interface Food {
    public String getType();
}

class Cake implements Food {
    public String getType() {
        return "Someone ordered Dessert!";
    }
}

class Pizza implements Food {
    public String getType() {
        return "Someone ordered a Fast Advanced.Food!";
    }
}

class FoodFactory {
    public static Food getFood(String order) {
        Food result = order.equalsIgnoreCase("pizza")? new Pizza() : new Cake();
        return result;
    }
}

class Singleton {
    // Initialised object is null. Used below to ensure only one is ever initialized.
    private static Singleton instance = null;
    private String str;

    //Private constructor!
    private Singleton() {
    }

    public static Singleton getSingleInstance() {
        if(instance == null) return new Singleton();
        else return instance;
    }

}

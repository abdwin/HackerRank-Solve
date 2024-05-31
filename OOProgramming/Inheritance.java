package OOProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inheritance {

    public static void main(String[] args) throws FileNotFoundException {
//        useInheritance2();
        useInheritance();
//        useAbstract();
    }


    public static void useInheritance() {
        Bird bird = new Bird();
        bird.walk(); // bird can use its superclass methods
        bird.fly(); // and its own methods
        bird.sing();
    }

    public static void useInheritance2() {
        Adder a = new Adder(); // OOProgramming.Adder has no methods but can use its superclass methods
        System.out.println("My superclass is: " + a.getClass().getSuperclass().getName()); //and can get info about its methods
        System.out.print(a.add(10,32) + " " + a.add(10,3) + " " + a.add(10,10) + "\n");
    }

    public static void useAbstract() throws FileNotFoundException {
        String file = "src/main/resources/abstractTest";
        Scanner scan=new Scanner(new File(file));
        String title=scan.nextLine();
        // OOProgramming.Book is abstract - an instance cannot be created. OOProgramming.MyBook extends it and so has to implement its abstract set method.
        MyBook new_novel = new MyBook();
        new_novel.setTitle(title);
        System.out.println("The title is: "+new_novel.getTitle());
        scan.close();
    }

}

class Animal{
    void walk(){
        System.out.println("I am walking");
    }
}

class Bird extends Animal {
    void fly() {
        System.out.println("I am flying");
    }
    void sing() {
        System.out.println("I am singing");
    }
}

class Arithmetic {
    public int add(int a, int b) {
        return a + b;
    }
}

class Adder extends Arithmetic {

}

abstract class Book {
    String title;

    abstract void setTitle(String s);

    String getTitle(){
        return title;
    }

}

class MyBook extends Book {

    void setTitle(String s) {
        this.title = s;
    }

}

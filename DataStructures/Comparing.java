package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Comparing {

    public static void main(String[] args) throws FileNotFoundException {

//        playerCompare();
        studentCompare();
    }

    //  Implementing the Comparator Interface on a class to customise order.
    //  Here again a class has been used simply to provide a Comparator.
    //  Normally this would be implemented within the DataStructures.Player class itself.
    //  Note Comparators can be created on fly.
    //  See DataStructures.Checker class used here.

    private static void playerCompare() throws FileNotFoundException {
        String file = "src/main/resources/compareThisTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        Player[] players = new Player[n];
        for(int i=0; i<n; i++){
            String name = scan.next();
            int score = scan.nextInt();
            players[i] = new Player(name, score);
        }
        Checker check = new Checker();
        Arrays.sort(players, check);
        for(Player player: players){
            System.out.println(player.name+" "+player.score);
        }
    }
//  Creating an on-fly Comparator with Advanced.Lambda and Method references. So not implemented with DataStructures.Student Class.
    public static void studentCompare() throws FileNotFoundException {
        String file = "src/main/resources/compareStudentTest";
        Scanner scan = new Scanner(new File(file));
        List<Student> students = new ArrayList<>();
        int n = scan.nextInt();
        while(scan.hasNext()){
            int id = scan.nextInt();
            String name = scan.next();
            double cgpa = scan.nextDouble();
            students.add(new Student(id, name, cgpa));
        }
        Comparator studentSort = Comparator.comparingDouble(Student::getCgpa).reversed()
                .thenComparing(Student::getName)
                .thenComparing(Student::getId);
        Collections.sort(students, studentSort);
        for(Student student: students){
            System.out.println(student.getName());
        }
    }

}

class Player {

    String name;
    int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

}

class Student{
    private int id;
    private String name;
    private double cgpa;
    public Student(int id, String name, double cgpa) {
        super();
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getCgpa() {
        return cgpa;
    }
}

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        int result = p2.score-p1.score;
        if(result==0){
            result  = p1.name.compareTo(p2.name);
        }
        return result;
    }

}


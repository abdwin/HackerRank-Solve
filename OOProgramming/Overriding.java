package OOProgramming;

// Test to demonstrate OOProgramming.Overriding Methods from a super class.
public class Overriding {

    public static void main(String []args){
        Sports c1 = new Sports();
        Soccer c2 = new Soccer();
        System.out.println(c1.getName());
        c1.getNumberOfTeamMembers();
        System.out.println(c2.getName());
        c2.getNumberOfTeamMembers();
        // use super keyword to use superclass method within subclass.
        MotorCycle M = new MotorCycle();//constructor prints out.

    }
}

class Sports{

    String getName(){
        return "Generic OOProgramming.Sports";
    }

    void getNumberOfTeamMembers(){
        System.out.println( "Each team has n players in " + getName() );
    }
}

class Soccer extends Sports {
    @Override // Override output for getNAme with "OOProgramming.Soccer Class"
    String getName() {
        return "OOProgramming.Soccer Class";
    }

    @Override // Override output for OOProgramming.Soccer subclass with 11 players.
    void getNumberOfTeamMembers(){
        System.out.println( "Each team has 11 players in " + getName() );
    }

}

class BiCycle{
    String define_me(){
        return "a vehicle with pedals.";
    }
}

class MotorCycle extends BiCycle {
    String define_me() {
        return "a cycle with an engine.";
    }

    MotorCycle() {
        System.out.println("Hello I am a motorcycle, I am " + define_me());

        String temp = super.define_me(); //Using super keyword to use the Overriden method from superclass.

        System.out.println("My ancestor is a cycle who is " + temp);
    }
}


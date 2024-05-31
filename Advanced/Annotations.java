package Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Scanner;


// Create custom Annotation with Values below which can be applied to Methods at Runtime.
@Target(ElementType.METHOD) //annotation assigns to methods only
@Retention(RetentionPolicy.RUNTIME) //annotation active at runtime
@interface FamilyBudget { //custom annotation creation
    String userRole() default "GUEST";//@Advanced.FamilyBudget annotation assigns userRole to annotated methods.
    // COMPLETE THIS INTERFACE
    int budgetLimit() default 200; //@Advanced.FamilyBudget annotation assigns budgetLimit to annotated methods.
}

class FamilyMember {
    @FamilyBudget(userRole = "SENIOR", budgetLimit = 100) //Annotation assigns methods/values
    public void seniorMember(int budget, int moneySpend) {
        System.out.println("Senior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }
    @FamilyBudget(userRole = "JUNIOR", budgetLimit = 50) //Annotation assigns methods/values
    public void juniorUser(int budget, int moneySpend) {
        System.out.println("Junior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }
}

// Advanced.Annotations class provides main runner.
public class Annotations {

    public static void main(String[] args) throws FileNotFoundException {
        String file = "src/main/resources/annotationsTest";
        Scanner in = new Scanner(new File(file));
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            String role = in.next();
            int spend = in.nextInt();
            try {
                //get all methods available to Advanced.FamilyMember - this will include our annotated methods
                Class annotatedClass = FamilyMember.class;
                Method[] methods = annotatedClass.getMethods();
                for (Method method : methods) { //for each method in class - if method is annotated
                    if (method.isAnnotationPresent(FamilyBudget.class)) {
                        FamilyBudget family = method.getAnnotation(FamilyBudget.class);
                        String userRole = family.userRole(); //apply user role from annotation
                        int budgetLimit = family.budgetLimit(); ////apply budget from annotation
                        if (userRole.equals(role)) {//if we have the correct annotated method (there are 2)
                            if(spend<=budgetLimit){ //and spend is within budget
                                //create Advanced.FamilyMember object and use to invoke this method
                                // with the arguments evaluated - the method itself provides the print out.
                                method.invoke(FamilyMember.class.newInstance(),
                                        budgetLimit, spend);
                            }else{
                                System.out.println("Budget Limit Over");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            testCases--;
        }
    }
}


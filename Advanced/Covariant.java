package Advanced;

public class Covariant {

//    Note all classes below do not have a constructor but can still have non-static methods.
//    The superclasses also are not abstract - they are concrete but have no properties.
//    The subclasses @Override its superclass implementation of the method.
//    This allows the subclass to have its own specific implementation whilst conforming to subtype and method name.

    public static void main(String[] args) {
        Region region = new WestBengal();
        Region region2 = new AndhraPradesh();
        Flower flower = region2.yourNationalFlower();
        System.out.println(flower.whatsYourName());
    }
}

    class Flower {
        public String whatsYourName(){
            return "I have many names and types";
        }
    }

    class Jasmine extends Flower {

        @Override
        public String whatsYourName() {
            return "Advanced.Jasmine";
        }
    }

    class Lily extends Flower {
        @Override
        public String whatsYourName() {
            return "Advanced.Lily";
        }
    }

    class Region {
        public Flower yourNationalFlower(){
            return new Flower();
        };
    }

    class WestBengal extends Region {
        @Override
        public Jasmine yourNationalFlower() {
            return new Jasmine();
        }
    }

    class AndhraPradesh extends Region {
        @Override
        public Lily yourNationalFlower() {
            return new Lily();
        }
    }





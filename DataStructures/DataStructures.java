package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataStructures {


    public static void main(String[] args) throws FileNotFoundException {
//        useMap();
//        useStack();
//        useHashSet();
//        useGenerics();
//        useDeque();
//        useBitSet();
//        usePrQueue();
//        useArrayList();
//        useArrayList2();
//        useArray1d();
//        useArray2d();
        useSubArray();
    }


    public static void useMap() throws FileNotFoundException {
        String file = "src/main/resources/mapTest";
        Scanner scan = new Scanner(new File(file));
        int n=scan.nextInt();
        scan.nextLine(); // scans remainder of line and returns "";
        Map<String, Integer> phoneBook = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            String name=scan.nextLine();
            int phone=scan.nextInt();
            scan.nextLine();
            phoneBook.put(name, phone);
        }
        while(scan.hasNext())
        {
            String s = scan.nextLine();
            try{
                int number = phoneBook.get(s);
                System.out.println(s +"="+ number );
            } catch(Exception e){
                System.out.println("Not found");
            }
        }
        scan.close();
    }

//    A Stack is essentially a Vector that provides useful queue methods to peek()/pop()/push()
//    A Character/char is also defined by '' and not "" which denotes a String
//    I've used a Map to make the lookups for a correct closing bracket easier
    public static void useStack() throws FileNotFoundException {
        String file = "src/main/resources/stackTest";
        Scanner scan = new Scanner(new File(file));
        Map<Character, Character> correctClose = new HashMap<>();
        correctClose.put('}','{');
        correctClose.put(')','(');
        correctClose.put(']','[');

        while (scan.hasNext()) {
            Stack checker = new Stack<>();
            boolean checked = true;
            String input=scan.next();
            if (input.length() % 2 == 1){
                checked = false;
            }
            else {
                for (int i = 0; i < input.length(); i++) {
                    char s = input.charAt(i);
                    if (s == '(' || s == '{' || s == '[') {
                        checker.push(s);
                    } else {
                        if (checker.empty()) {
                            checked = false;
                            break;
                        }
                        if (correctClose.get(s) == (checker.peek())) {
                            checker.pop();
                        } else {
                            checked = false;
                            break;
                        }
                    }
                }
            }
            System.out.println(checked);
        }

        scan.close();
    }

    public static void useHashSet() throws FileNotFoundException {
        String file = "src/main/resources/hashSetTest";
        Scanner scan = new Scanner(new File(file));
        int t = scan.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];
        for (int i = 0; i < t; i++) {
            pair_left[i] = scan.next();
            pair_right[i] = scan.next();
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i < t; i++) {
            String pair = pair_left[i] + " " + pair_right[i];
            set.add(pair);
            System.out.println(set.size());
        }
        scan.close();
    }

//    Using Generics we have created a printArray method that takes in
//    two different Types to deliver the same outcome.
//    We've also got overloaded methods which infer correctly which method to use
//    based on argument type.
    public static void useGenerics() throws FileNotFoundException {
        String[] strings = new String[]{"Hello", "World"};
        Integer[] numbers = new Integer[]{1, 2, 3};
        printArray(strings);
        printArray(numbers);
        List<String> strings2 = Arrays.asList(strings);
        List<Integer> numbers2 = Arrays.asList(1,2,3);
        printArray(strings2);
        printArray(numbers2);
        List<Object> stringsAndNums = new ArrayList<>();
        Collections.addAll(stringsAndNums, "Hello", "World", 1, 2, 3);
        printArray(stringsAndNums);
    }

    // Original method using <T> to provide generic type array
    private static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    // Overloaded method using List<?>
    private static void printArray(List<?> list) {
            for(int i = 0;i < list.size();i++) {
                System.out.println(list.get(i));
            }
    }

    // Here we use a Deque (double ended queue) to store our numbers whilst checking unique size
    // on a Set. Polling removes first entry of queue, and checks if it exists twice.
    // If not, it can be removed from the Set. Otherwise still exists in Set.
    public static void useDeque() throws FileNotFoundException {
        String file = "src/main/resources/dequeTest";
        Scanner scan = new Scanner(new File(file));
        int arraySize = scan.nextInt();
        int subSize = scan.nextInt();
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int uniqueCount = 0;
        for(int i=0; i<arraySize; i++){
            int num = scan.nextInt();
            deque.offer(num);
            set.add(num);
            if (deque.size()==subSize){
                uniqueCount = Math.max(set.size(), uniqueCount);
                int removeFirst = deque.poll();
                if (!deque.contains(removeFirst)){
                    set.remove(removeFirst);
                }
            }
        }
        System.out.println(uniqueCount);
        scan.close();
    }

//    BitSets are a Vector which holds 0s(false) and 1s(true). Specific methods available to perform
//    BitSet operations - and(), flip() etc. Never come across this before.
    public static void useBitSet() throws FileNotFoundException {
        String file = "src/main/resources/bitSetTest";
        Scanner scan = new Scanner(new File(file));
        int setSize = scan.nextInt();
        BitSet set1 = new BitSet(setSize);
        BitSet set2 = new BitSet(setSize);
        BitSet[] sets = new BitSet[3];
        sets[1] = set1;
        sets[2] = set2;
        int ops = scan.nextInt();
        for(int i=1; i<=ops;i++){
            String op = scan.next();
            int arg1 = scan.nextInt();
            BitSet onSet = sets[arg1];
            BitSet withSet = sets[(arg1 == 1)? 2 : 1];
            int index = scan.nextInt();
            switch(op){
                case "AND":
                    onSet.and(withSet);
                    break;
                case "OR":
                    onSet.or(withSet);
                    break;
                case "XOR":
                    onSet.xor(withSet);
                    break;
                case "FLIP":
                    onSet.flip(index);
                    break;
                case "SET":
                    onSet.set(index);
                    break;

            }
            // cardinality() counts the 1s in the BitSet.
            System.out.println(set1.cardinality() +" "+ set2.cardinality());
        }
        scan.close();
    }

    public static void usePrQueue() throws FileNotFoundException {
        String file = "src/main/resources/prQueueTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        Comparator<Student> prioritiseStudent = Comparator.comparingDouble(Student::getCgpa).reversed()
                .thenComparing(Student::getName).thenComparing(Student::getId);
        PriorityQueue<Student> queue = new PriorityQueue<>(n, prioritiseStudent);
        for (int i = 1; i <= n; i++) {
            String event = scan.next();
            if(event.equals("ENTER")) {
                String name = scan.next();
                double cgpa = scan.nextDouble();
                int id = scan.nextInt();
                Student student = new Student(id, name, cgpa);
                queue.offer(student);
            }
            else {
                queue.poll();
            }
        }
        scan.close();
        // Priority Queues DO NOT guarantee order internally!! So you have to Poll the head entry to
        // print correct order of students in queue.
        int size = queue.size();
        for (int i=0; i<size; i++) {
            System.out.println(queue.poll().getName());
        }
    }

    public static void useArrayList() throws FileNotFoundException {
        String file = "src/main/resources/arrayListTest";
        Scanner scan = new Scanner(new File(file));
        List<String[]> list = new ArrayList<>();
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <= n; i++) {
            String line = scan.nextLine();
            list.add(line.split(" "));
        }
        int q = scan.nextInt();
        for (int i = 1; i <= q; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            try {
                System.out.println((list.get(x-1)[y]));
            } catch(Exception e) {
                System.out.println("ERROR!");
            }
        }
        scan.close();
    }

    public static void useArrayList2() throws FileNotFoundException {
        String file = "src/main/resources/arrayList2Test";
        Scanner scan = new Scanner(new File(file));
        List<Integer> list = new ArrayList<>();
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(scan.nextInt());
        }
        int q = scan.nextInt();
        for (int i = 1; i <= q; i++) {
            String op = scan.next();
            if(op.equals("Insert")){
                list.add(scan.nextInt(), scan.nextInt());
            } else {
                list.remove(scan.nextInt());
            }
        }
        System.out.println(list);
        scan.close();
    }

    public static void useArray1d() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] list = new int[n];
        for(int i=0; i<n; i++){
            list[i] = scan.nextInt();
            System.out.println(list[i]);
        }
        scan.close();
    }

    public static void useArray2d() throws FileNotFoundException {
        String file = "src/main/resources/array2dTest";
        Scanner scan = new Scanner(new File(file));
        int[][] check = new int[6][6];
        for(int i=0; i<6; i++) {
            for (int j = 0; j < 6; j++) {
                check[i][j] = scan.nextInt();
            }
        }
        int count = Integer.MIN_VALUE;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                int temp = check[i][j] + check[i][j+1] + check[i][j+2]
                            + check[i+1][j+1]
                            + check[i+2][j] + check[i+2][j+1] + check[i+2][j+2];
                count = Math.max(count, temp);
            }
        }
        System.out.println(count);
        scan.close();
    }

    public static void useSubArray() throws FileNotFoundException {
        String file = "src/main/resources/subArrayTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        int[] check = new int[n];
        for (int i = 0; i < n; i++) {
            check[i] = scan.nextInt();
        }
        int count = 0;
        // set size of subArray
        for(int size = n; size>=0; size--) {
            // set start loop
            for (int start = 0; start < n - size; start++) {
                // set end position based on start
                int end = start+size;
                int subArrayTotal=0;
                for (int pos=start; pos<=end; pos++) {
//                    int[] subArray = Arrays.copyOfRange(check, i, i + size);
//                    int total = Arrays.stream(subArray).reduce(0, Integer::sum);
                    subArrayTotal += check[pos];
                }
                if (subArrayTotal < 0) count++;
            }
        }
        System.out.println(count);
        scan.close();
    }

}


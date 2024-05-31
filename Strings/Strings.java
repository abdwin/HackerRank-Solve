package Strings;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

    public static void main(String[] args) throws FileNotFoundException {
//        useSubString();
//        useSubStringCompare();
//        useUpperCase();
//        useReverse();
//        useReverse2();
//        useAnagram();
//        useAnagram2();
//        useStringToken();
//        useRegexPattern();
//        useRegexIP();
//        useRegexIP2();
//        useRegexDuplicate();
//        useRegexUsername();
        useRegexTagExtractor();
}
    // use String.substring(start-inclusive, end-exclusive) to get substring
    public static void useSubString() throws FileNotFoundException {
        String file = "src/main/resources/subStringTest";
        Scanner scan = new Scanner(new File(file));
        String phrase = scan.nextLine();
        String newPhrase = phrase.substring(scan.nextInt(), scan.nextInt());
        System.out.println(newPhrase);
    }

    //use String.compareTo(String) to get lexicographic comparison (ascending order)
    // returning -1(smaller), 0(equal), 1(bigger).
    public static void useSubStringCompare() throws FileNotFoundException {
        String file = "src/main/resources/subStringTest";
        Scanner scan = new Scanner(new File(file));
        String phrase = scan.nextLine();
        int size = phrase.length();
        int n = scan.nextInt(); //number of characters allowed in substring
        String smallest = phrase;
        String largest = "";
        for (int i = 0; i <= size-n ; i++) {
            String sub = phrase.substring(i, i+n);
            if(smallest.compareTo(sub)>0) {smallest=sub;} //smallest is bigger, so swap
            if(largest.compareTo(sub)<0) {largest=sub;} //largest is smaller, so swap
        }
        System.out.println(smallest);
        System.out.println(largest);
    }

    // use char Type and String/Character methods to print out name with capital first letter.
    public static void useUpperCase() throws FileNotFoundException {
        String file = "src/main/resources/upperTest";
        Scanner scan = new Scanner(new File(file));
        String A = scan.nextLine();
        String B = scan.nextLine();
        int result1 = A.length() + B.length(); //print out total length of Strings
        String result2 = (A.compareTo(B)>0)? "Yes":"No"; //print out "Yes" if A is larger than B.
        System.out.println(result1);
        System.out.println(result2);
        char upperA = Character.toUpperCase(A.charAt(0));
        char upperB = Character.toUpperCase(B.charAt(0));
        String A1 = upperA + A.substring(1);//char + substring concatenation
        String B1 = upperB + B.substring(1);
        System.out.println(A1 + " " + B1);
    }

    // Reverse the provided word using a Loop and String.equals(String) to check for Palindrome.
    public static void useReverse() throws FileNotFoundException {
        String file = "src/main/resources/reverseStringTest";
        Scanner scan = new Scanner(new File(file));
        String forwardWord = scan.next();
        scan.close();
        String reverseWord = "";
        int size = forwardWord.length();
        for(int i=0; i<size; i++) { //take each character and string them together in reverse order.
            reverseWord += forwardWord.charAt(size-1-i);
        }
        if(reverseWord.equals(forwardWord)) {System.out.println("Yes");}
        else {System.out.println("No");}
    }

    //v2 of above. String.split() into [] and simply check each character in loop is a mirror.
    //could also use charAt();
    public static void useReverse2() throws FileNotFoundException {
        String file = "src/main/resources/reverseStringTest";
        Scanner scan = new Scanner(new File(file));
        String[] forwardWord = scan.next().split("");
        int size = forwardWord.length;
        scan.close();
        String isPalindrome = "Yes";
        for(int i=0; i<size/2; i++) {
            if(!forwardWord[i].equals(forwardWord[size-1-i])){ //check string[i]==string[n-i]
                isPalindrome = "No"; //if not a match - break with false.
                break;
            };
        }
        System.out.println(isPalindrome);
    }

    // Compare two words to confirm if both have equal letters in anagram. CAT = ACT
    // use StringBuilder to easy delete characters found. If not found then "Not Anagram"
    public static void useAnagram() throws FileNotFoundException {
        String file = "src/main/resources/anagramsTest";
        Scanner scan = new Scanner(new File(file));
        String word1 = scan.next().toLowerCase(); //case-insensitive comparison only
        String word2 = scan.next().toLowerCase();
        scan.close();
        StringBuilder builder = new StringBuilder(word2); //builder starts with Word2
        if(word1.length() != word2.length()){System.out.println("Not Anagrams");}
        else {//if each word is at least the same length...
            String result = "Anagrams";
            for (int i = 0; i < word1.length(); i++) {
                //check if word2 contains the char at word1. If it does delete from word2 and continue
                int check = builder.indexOf(String.valueOf(word1.charAt(i)));//require String to use indexOf.
                if(!(check==-1)){//if character exists remove from word2.
                    builder.delete(check,check+1);//StringBuilder.delete(start,end-exclusive)
                } else {
                    result = "Not Anagrams";
                    break;
                }
            }
            System.out.println(result);
        }
    }

    // A char type will natively convert to an ASCII integer value (0-256) when inferred in that way.
    // This allows us to use an int[] Frequency array to keep track of used characters in each word.
    public static void useAnagram2() throws FileNotFoundException {
        String file = "src/main/resources/anagramsTest";
        Scanner scan = new Scanner(new File(file));
        String word1 = scan.next().toLowerCase();
        String word2 = scan.next().toLowerCase();
        scan.close();
//        256 characters ASCII.
        int[] checker = new int[256]; //array of 256 to capture presence of a character
        if(word1.length() != word2.length()){
            System.out.println("Not Anagrams");
        } else {
            for (int i = 0; i < word1.length(); i++) {
                checker[word1.charAt(i)]++;//add to frequency counter of character value. int ASCII value inferred.
                checker[word2.charAt(i)]--;//subtract from frequency counter if exists.
            }
            String result = "Anagrams";
            for (int character : checker) {
                if (character != 0) { //all int[] values should be 0 if an Anagram.
                    result = "Not Anagrams";
                    break;
                }
            }
            System.out.println(result);
        }
    }

    //String.trim(), String.split(with Regex Delimiter) to match/split on any of included characters.
    public static void useStringToken() throws FileNotFoundException {
        String file = "src/main/resources/stringTokenTest";
        Scanner scan = new Scanner(new File(file));
        if(scan.hasNext()) {
            // trim() removes leading/trailing space.
            String[] phrase = scan.nextLine().trim().split("[ !,?._'@]+"); //matches and splits on matched chars.
            System.out.println(phrase.length);
            for (String word : phrase) {
                System.out.println(word);
            }
        }
        else {
            System.out.println("0");// print 0 for length is no words provided.
        }
    }

    //Create a Regex Pattern from a provided regex string. Only valid if we can compile it.
    public static void useRegexPattern() throws FileNotFoundException {
        String file = "src/main/resources/regexPatternTest";
        Scanner scan = new Scanner(new File(file));
        int queries = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <=queries; i++) {
            String check = scan.nextLine().trim(); //read in a String Line
            try{//can we successfully Pattern.compile()?
                Pattern success = Pattern.compile(check);
                System.out.println("Valid");
            }
            catch(Exception e){
                System.out.println("Invalid");
            }
        }
        scan.close();
    }

    // is String provided a valid IP?
    public static void useRegexIP() throws FileNotFoundException {
        String file = "src/main/resources/regexIPTest";
        Scanner scan = new Scanner(new File(file));
        while(scan.hasNext()){
            String[] phrase = scan.next().trim().split("[.]"); //split on "."
            if (phrase.length == 4){ //should have 4 entries. xxx.xxx.xxx.xxx
                boolean result = true;
                for(String block: phrase){
                    if(block.length()>3){ //only 3 chars in length allowed.
                        result=false;
                        break;
                    }
                    int value;
                    try{
                        value = Integer.valueOf(block);// can we get int value from string?
                    } catch(Exception e){
                        result = false;
                        break;
                    }
                    if (value < 0 || value >255){ //is value between 0 and 255?
                        result = false;
                    }
                }
                System.out.println(result);
            } else {System.out.println(false);}
        }
        scan.close();
    }

    //It worked, but didn't strictly use a Regex approach above.
    // use String.match(regex String) to check IP format.
    public static void useRegexIP2() throws FileNotFoundException {
        String file = "src/main/resources/regexIPTest";
        Scanner scan = new Scanner(new File(file));
        String zeroTo255 = "((0|1)\\d{2}|2[0-4]\\d|25[0-5]|\\d{1,2})"; //Regex string explained.
        //Four OR match conditions are applied here against an expected 3 digit number
        // (0|1)\d{2} catches any three digit number starting with 0 or 1.
        // 2[0-4]\d catches any three digit number between 200 and 249.
        // 25[0-5] catches any three digit number between 250 and 255.
        // \d{1,2} catches any one or two digit number: 0-99.
        // Complete Regex String will then be...
        String validIpPattern = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
        while (scan.hasNext()) {
            String check = scan.next();
            System.out.println(check.matches(validIpPattern));
        }
        scan.close();

    }

    // From a compiled regex Pattern we can create a Matcher which has .find() methods.
    // String.replaceAll(regex, replacement), Pattern.matcher(pattern), Matcher.find()
    public static void useRegexDuplicate() throws FileNotFoundException {
        String file = "src/main/resources/regexDuplicateTest";
        Scanner scan = new Scanner(new File(file));
        // Regex to catch duplicate words
        // (?i) says the search pattern will be case insensitive
        // (xxx) a Group with a search term included.
        // \b___\b provides a word boundary - i.e. start and end of a word boundary
        // \w+ means match a word character. + means one or more characters.
        // \s+ means match whitespace. + means one or more whitespace.
        // \1 matches whatever pattern matched from Group 1...so this is all saying.....
        // Perform a Case Insensitive search for any (Group 1 Word) match
        // which is then followed by whitespace and then the SAME (Group 1 Word) match one or more times.
        String regex = "(?i)(\\b\\w+\\b)(\\b\\s+\\1\\b)+";
        Pattern duplicate = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); //case insensitive pattern compiled.
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <= n; i++) {
            String phrase = scan.nextLine().trim();
            Matcher matcher = duplicate.matcher(phrase); //create a matcher using the compiled pattern with phrase.
            while(matcher.find()){ //where a match in the phrase is found, replace with "" except first.
                phrase = phrase.replaceAll(regex, "$1"); // replace with "" except the first match.
            }
            System.out.println(phrase);
        }
        scan.close();
    }

    // String.matches(regex)
    public static void useRegexUsername() throws FileNotFoundException {
        String file = "src/main/resources/regexUsernameTest";
        Scanner scan = new Scanner(new File(file));
        int n = scan.nextInt();
        scan.nextLine();
        //[a-zA-Z] first character has to be a-zA-Z
        //followed by any character \w of between 7 to 29 characters. Min 8 - Max 30.
        String regex = "([a-zA-Z])\\w{7,29}";
        while (n-- > 0){
            String phrase = scan.nextLine().trim();
            String result = phrase.matches(regex)? "Valid" : "Invalid";
            System.out.println(result);
        }
        scan.close();
    }

    public static void useRegexTagExtractor() throws FileNotFoundException {
        String file = "src/main/resources/regexTagTest";
        Scanner scan = new Scanner(new File(file));
        // <(.+)> matches any sequence of characters except a return line inside <> () gives it Group 1
        // ([^<>]) matches any sequence of characters except < >. () this is Group 2 which we'll want to print
        // </\\1> matches found Group 1 characters inside </  >.
        String regex = "<(.+)>([^<>]+)</\\1>";
        Pattern tag = Pattern.compile(regex);//compile regex to a Pattern so we can create a Matcher.find()
        int n = scan.nextInt();
        scan.nextLine();
        while(n-- >0){
            String phrase = scan.nextLine();
            Matcher match = tag.matcher(phrase);
            if(!match.find()){System.out.println("None");}// if no match found - print None
            else{System.out.println(match.group(2));}//else print first find object (or we'll miss it)
            while(match.find()){ //print remaining find objects if there are any
                System.out.println(match.group(2));
            }
        }


    }

}

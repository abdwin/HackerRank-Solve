package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DynamicProgrammingExample {

    //Each attempt method below gets more optimised.
    //Idea is to read in a game array of 0s and 1s.
    //Player can step to next entry if a 0 or take a leap to an entry if a 0.
    //Can the player get to the end of the array?
    public static void main(String[] args) throws FileNotFoundException {
        String file = "src/main/resources/arrayGameTest";
        Scanner scan = new Scanner(new File(file));
        int games = scan.nextInt();
        for (int i = 1; i <= games; i++) {
            int n = scan.nextInt();
            int[] game = new int[n];
            int leap = scan.nextInt();
            for (int j = 0; j < n; j++) {
                game[j] = scan.nextInt(); //read in Game array of 0s and 1s.
            }
            // edge case if starting point = 1. You can't start.
            if (game[0] != 0) {
                System.out.println("NO");
                continue;
            }
            boolean win1 = attempt1(game, leap, 0);
            System.out.println(win1? "YES":"NO");
            boolean win2 = attempt2(game, leap, 0);
            System.out.println(win2? "YES":"NO");
            boolean win3 = attempt3(game, leap, 0);
            System.out.println(win3? "YES":"NO");
        }
    }

    // HashSet stores all visited positions so not re-evaluated.
    // Queue manages all evaluation attempts until True is found or Queue has exhausted options(empty)
    public static boolean attempt1(int[] game, int leap, int pos) {
        int n = game.length;
        if (leap >= n) {// Edge case: if leap jumps beyond end of array from starting point - PASS
            System.out.println("YES");
            return true;
        }
        Queue<Integer> check = new LinkedList();
        HashSet<Integer> visited = new HashSet<>();
        // Put in first two Queued entries. Can't evaluate pos-1 on first move.
        if (game[pos + 1] == 0) {//Offer next entry to Queue if 0
            check.offer(pos + 1);
        }
        if (game[pos + leap] == 0) {//Offer leap entry to Queue if 0
            check.offer(pos + leap);
        }
        visited.add(pos);
        boolean win = false;
        while (pos < n - 1 && !check.isEmpty()) {//go through queue until empty or past end.
            pos = check.poll();
            if (visited.contains(pos)) {// if already visited - ignore
                continue;
            } else {
                visited.add(pos); //add to visited if new
            }
            if (pos + leap >= n || pos + 1 >= n) {//break with True if can reach end
                win = true;
                break;
            }
            if (game[pos + leap] == 0) {//else offer leap entry if poss.
                check.offer(pos + leap);
            }
            if (game[pos + 1] == 0) {//else offer +1 entry if poss.
                check.offer(pos + 1);
            }
            if (game[pos - 1] == 0) {//else offer -1 entry if poss.
                check.offer(pos - 1);
            }
        }
        return win;
    }

    // Same as above but remove HashSet store. Just change the array value to 1 if already been visited.
    public static boolean attempt2(int[] game, int leap, int pos) {
        int n = game.length;
        // Edge case: if leap jumps beyond end of array from starting point - PASS
        if (leap >= n) {
            System.out.println("YES");
            return true;
        }
        Queue<Integer> check = new LinkedList();
        if (game[pos + 1] == 0) {
            check.offer(pos + 1);
        }
        if (game[pos + leap] == 0) {
            check.offer(pos + leap);
        }
        game[pos] = 1;
        boolean win = false;
        while (pos < n - 1 && !check.isEmpty()) {
            pos = check.poll();
            game[pos] = 1;
            if (pos + leap >= n || pos + 1 >= n) {
                win = true;
                break;
            }
            if (game[pos + leap] == 0) {
                check.offer(pos + leap);
            }
            if (game[pos + 1] == 0) {
                check.offer(pos + 1);
            }
            if (game[pos - 1] == 0) {
                check.offer(pos - 1);
            }
        }
        return win;
    }

    // Remove Queue and While loop. Recursive return call to attempt3 will drill down all positions until
    // a True is returned - if possible - or every pos has returned False.
    // This relies on evaluating False as well as True each time - i.e. game[pos] == 1 is simply FALSE
    // rather than not evaluated as above.
    public static boolean attempt3(int[] game, int leap, int pos) {//recursive call requires to know curr.pos
        int n = game.length;
        if (pos < 0 || game[pos] == 1) {//if passed move attempt = 1 return False for this attempt
            return false;
        }
        if (pos + leap >= n || pos + 1 >= n) {//if next move attempt is beyond end of array return True
            return true;
        }
        // ensure this value if revisited is returned as False by changing to 1.
        game[pos] = 1;
        // instead of while loop - use a recursive call. OR condition says only 1 True is needed. Clever.
        return attempt3(game, leap, pos+leap) ||
                attempt3(game, leap, pos+1) ||
                attempt3(game, leap, pos-1);
    }


}

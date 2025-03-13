import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// TC: O(2 ^ n) all the characters in the input string is visited
// SC: O(2 ^ n) as for every character exploration, its children also has n number of siblings

// Runs successfully on leetcode
// For BFS approach, parents is explored and checked for its validity 
// to be added in result. else checked against set to be added in queue to explore it children
public class RemoveInvalidParenthesis {

    public static void main(String[] args) {
        System.out.println(removeInvalidParenthesesBFS("()())()")); // [(())(), ()()()]
        System.out.println(removeInvalidParenthesesBFS("(a)())()")); // [(a())(),(a)()()]
        System.out.println(removeInvalidParenthesesBFS(")(")); // []

        // System.out.println(removeInvalidParenthesesDFS("()())()")); // [(())(),
        // ()()()]
        // System.out.println(removeInvalidParenthesesDFS("(a)())()")); //
        // [(a())(),(a)()()]
        // System.out.println(removeInvalidParenthesesDFS(")(")); // []
    }

    public static List<String> removeInvalidParenthesesBFS(String s) {
        if (s == null || s.length() == 0)
            return new ArrayList<>();
        List<String> result = new ArrayList<>();
        boolean flag = false;
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        set.add(s);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (isValid(current)) {
                    flag = true;
                    result.add(current);
                } else if (flag == false) {
                    for (int j = 0; j < current.length(); j++) {
                        char c = current.charAt(j);
                        if (c >= 'a' && c <= 'z')
                            continue;
                        String child = current.substring(0, j) + current.substring(j + 1);
                        if (!set.contains(child)) {
                            queue.offer(child);
                            set.add(child);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValid(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                count++;
            else if (input.charAt(i) == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }

}
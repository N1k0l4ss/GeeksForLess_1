package calculator;

import java.util.*;
import java.util.stream.Collectors;

public class CalcValidator {
    public Boolean staplesIsValid(String line) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');

        for (int i = 0; i < line.length(); i++) {
            if (!(line.charAt(i) == '(' || line.charAt(i) == ')')) // if it's not scope, continue
                continue;
            if (map.containsValue(line.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!map.get(stack.pop()).equals(line.charAt(i))) return false;
            } else stack.push(line.charAt(i));
        }
        return stack.isEmpty();
    }


    public String inputNormalizer(String line){ // "[^0-9 *()+-]"
        return line.trim()
                // Beginning of the string
                .replaceAll("^[\\+\\*\\/]", "") // Removes '+', '/', '*', '-' at the begin of string
                .replaceAll("^\\-{2}", "-") // Doesn't let to make two '-' at the beginning of string

                // Removing mistakes
                .replaceAll("[^0-9 *()+-.]| +", "") // Removes all non-valid character except numbers, point and spaces
                .replaceAll("\\-{3}", "-") // Doesn't let to make more than two minuses
                .replaceAll("[,.]+", ".") // Makes from ',' to '.' and doesn't let to make more than one point

                // This block doesn't let to enter more than one operator
                .replaceAll("\\*+[\\/\\+]?", "*")
                .replaceAll("\\++[\\/\\*]?", "+")
                .replaceAll("\\/+[\\*\\+]?", "/")

                // This block allows to not enter more than one minus after other operators
                .replaceAll("\\*+\\-{2}", "*")
                .replaceAll("\\++\\-{2}", "+")
                .replaceAll("\\/+\\-{2}", "/");
    }

    public Integer numberCounter(String input){
        return Arrays.stream(input.split("[^0-9.]"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList())
                .size();
    }
}

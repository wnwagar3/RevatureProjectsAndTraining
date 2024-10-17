import java.util.HashMap;
import java.util.Map;

public class MostCommonCharacter {
    /**
     * Find the most common character in str.
     * You could use a HashMap that maps a Character key to an Int value to represent how many times a Character has
     * been spotted.
     * @param str A String.
     * @return the most common character within str.
     */
    public char recurringChar(String str) {
        // Remove spaces if there are any
        str = str.replace(" ", "");

        // Create HashMap to store character frequencies
        Map<Character, Integer> charFrequency = new HashMap<>();

        // Loop through string to count character frequencies
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // If char not in HashMap already, put it in HashMap with count of 1
            if (!charFrequency.containsKey(c)) {
                charFrequency.put(c, 1);
            } else {
                // If char already in HashMap, increment value by 1
                charFrequency.put(c, charFrequency.get(c) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFrequency =0;
        
        //Loop through HashMap to find highest frenquency in order to return that char
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }
        return mostCommonChar;
    }
}

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Jour5Test {

    private static final String VOWELS_REGEX = ".*(.*[aeiou]){3}.*";
    private static final String SAME_LETTER_ROW_TWICE = "(.)\\1";
    private static final String BAD_STRING = "ab|cd|pq|xy";

    @Test
    public void regexTest(){

        Pattern badString = Pattern.compile(BAD_STRING);
        Pattern vowel = Pattern.compile(VOWELS_REGEX);
        Pattern doubleLetter = Pattern.compile(SAME_LETTER_ROW_TWICE);

        assertTrue(vowel.matcher("ugknbfddgicrmopn").find());
        assertFalse(vowel.matcher("hello").find());

        assertTrue(doubleLetter.matcher("abcdde").find());
        assertFalse(doubleLetter.matcher("abcde").find());

        assertTrue(badString.matcher("haegwjzuvuyypxyu").find());
    }
}

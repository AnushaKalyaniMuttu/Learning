import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestPalindrome {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("racecar", "level", "banana", "civic", "apple");

        // Find the longest palindrome substring across all strings
        String longestPalindrome = input.stream()
                .map(LongestPalindrome::findLongestPalindrome) // Find longest palindrome for each string
                .filter(Optional::isPresent) // Remove empty results
                .map(Optional::get) // Extract the value
                .max(Comparator.comparingInt(String::length)) // Find the longest palindrome
                .orElse("No palindrome found");

        System.out.println("Longest Palindrome: " + longestPalindrome);
    }

    // Method to find the longest palindrome in a string
    private static Optional<String> findLongestPalindrome(String str) {
        return IntStream.range(0, str.length())
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i, str.length())
                        .mapToObj(j -> str.substring(i, j)))
                .filter(LongestPalindrome::isPalindrome)
                .max(Comparator.comparingInt(String::length));
    }

    // Utility to check if a string is a palindrome
    private static boolean isPalindrome(String s) {
        return IntStream.range(0, s.length() / 2)
                .allMatch(i -> s.charAt(i) == s.charAt(s.length() - 1 - i));
    }
}

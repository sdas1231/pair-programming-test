package random.string;

public class PalindromeUsingStringBuilder {

    private static boolean isPalindrome(final String str) {
        return str.contentEquals(new StringBuilder(str).reverse());
    }

    public static void main(String[] args) {
        // Input string
        String str = "ANINA";
        System.out.println("Palindrome - " + isPalindrome(str.trim().toLowerCase().replace(" ", "")));
    }
}

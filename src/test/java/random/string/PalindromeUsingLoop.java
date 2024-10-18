package random.string;

public class PalindromeUsingLoop {
    private static boolean isPalindrome(final String str) {
        final int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("Palindrome - " + isPalindrome("ANINA".trim().toLowerCase().replace(" ", "")));
        System.out.println("Palindrome - " + isPalindrome("KSHMYA".trim().toLowerCase().replace(" ", "")));
    }
}

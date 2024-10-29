package random.string;

public class PalindromeUsingTwoPointer {
    private static boolean isPalindrome(final String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Palindrome - " + isPalindrome("ANINA".trim().toLowerCase().replace(" ", "")));
        System.out.println("Palindrome - " + isPalindrome("KSHMYA".trim().toLowerCase().replace(" ", "")));
    }
}

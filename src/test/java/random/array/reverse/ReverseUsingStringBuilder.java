package random.array.reverse;

import java.util.Arrays;

public class ReverseUsingStringBuilder {
    private static void reverse(final int[] arr, final int size) {
        StringBuilder reverse = new StringBuilder();
        for (int i = size; i > 0; i--) {
            reverse.append(arr[i - 1]).append(",");
        }

        System.out.println("Output Array - " + Arrays.toString(reverse.toString().split(",")));
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60};
        System.out.println("Input Array - " + Arrays.toString(arr));
        reverse(arr, arr.length);
    }
}

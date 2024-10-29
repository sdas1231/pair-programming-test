package random.array.reverse;

import java.util.Arrays;

public class ReverseUsingSwapping {

    private static void reverse(final int[] arr, final int size) {
        int t;
        for (int i = 0; i < size / 2; i++) {
            t = arr[i];
            arr[i] = arr[size - i - 1];
            arr[size - i - 1] = t;
        }

        System.out.println("Output Array - " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60};
        System.out.println("Input Array - " + Arrays.toString(arr));
        reverse(arr, arr.length);
    }
}

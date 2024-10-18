package random.array.reverse;

import java.util.Arrays;

public class ReverseUsingArray {
    private static void reverse(final int[] arr, final int size) {
        int[] temp = new int[size];
        for (int i = size - 1; i == 0; i--) {
            temp[size - i - 1] = arr[i];
        }

        System.out.println("Output Array - " + Arrays.toString(temp));
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60};
        System.out.println("Input Array - " + Arrays.toString(arr));
        reverse(arr, arr.length);
    }
}

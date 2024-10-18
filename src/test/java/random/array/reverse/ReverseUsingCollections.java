package random.array.reverse;

import java.util.Arrays;
import java.util.Collections;

public class ReverseUsingCollections {

    private static void reverse(final Integer[] arr) {
        Collections.reverse(Arrays.asList(arr));
        System.out.println("Output Array - " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 20, 30, 40, 50, 60};
        System.out.println("Input Array - " + Arrays.toString(arr));
        reverse(arr);
    }
}

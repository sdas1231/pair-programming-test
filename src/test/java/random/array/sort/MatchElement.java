package random.array.sort;

import java.lang.reflect.Array;
import java.util.Objects;

public class MatchElement {
    private static int match(final int[] arr, final int number) {
        if (Objects.isNull(arr) || arr.length == 0 || number < arr[0] || number > arr[arr.length - 1]) return -1;
        for (int i : arr) {
            if (arr[i] == number) return arr[i];
            if (arr[i] > number) return arr[i - 1];
        }

        return -1;
    }

    public static void main(String[] args) {
        final int[] sortedArr = {3, 4, 6, 9, 10, 12, 14, 15, 17, 19, 21};
        System.out.println(match(sortedArr, 22));
    }
}

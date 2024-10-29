package random.array.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FindPairs {
    private static void getPairsByMap(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int complement = sum - num;
            if (map.containsKey(complement)) {
                System.out.println("Pair: (" + num + ", " + complement + ")");
            }
            map.put(num, 1);
        }
    }

    private static void getPairsByStream(int[] arr, int sum) {
        IntStream.range(0, arr.length).forEach(i ->
                IntStream.range(i + 1, arr.length).filter(j -> arr[i] + arr[j] == sum)
                        .forEach(j -> System.out.println("Pair: (" + arr[i] + ", " + arr[j] + ")"))
        );
    }

    private static void getPairsByLoop(int[] arr, int sum) {
        int n = arr.length;
        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == sum) System.out.println("Pair: (" + arr[i] + ", " + arr[j] + ")");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // Input
        System.out.println("Input - " + Arrays.toString(arr));

        // Pairs
        getPairsByMap(Arrays.copyOf(arr, arr.length), 6);
        getPairsByStream(Arrays.copyOf(arr, arr.length), 6);
        getPairsByLoop(Arrays.copyOf(arr, arr.length), 6);
    }
}

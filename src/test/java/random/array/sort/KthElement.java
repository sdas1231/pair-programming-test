package random.array.sort;

import javax.xml.transform.dom.DOMLocator;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthElement {
    private static String kthLargestElementBySort(int[] arr, int k) {
        if (arr.length < k) return "Invalid Input";
        Arrays.sort(arr);
        return String.valueOf(arr[arr.length - k]);
    }

    private static String kthLargestElementByDescSort(Integer[] arr, int k) {
        if (arr.length < k) return "Invalid Input";
        Arrays.sort(arr, Collections.reverseOrder());
        return String.valueOf(arr[k - 1]);
    }

    //TO-DO - Update the method
    private static String kthLargestElementUsingQueue(Integer[] arr, int k) {
        if (arr.length < k) return "Invalid Input";

        // Max-Heap Priority Queue
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int next: arr) {
            queue.offer(next);
            if (queue.size() > k) queue.poll();
        }

        return String.valueOf(queue.peek());
    }

    private static String kthSmallestElementBySort(int[] arr, int k) {
        if (arr.length < k) return "Invalid Input";
        Arrays.sort(arr);
        return String.valueOf(arr[k - 1]);
    }

    private static String kthSmallestElementByDescSort(Integer[] arr, int k) {
        if (arr.length < k) return "Invalid Input";
        Arrays.sort(arr, Collections.reverseOrder());
        return String.valueOf(arr[arr.length - k]);
    }

    private static String kthSmallestElementUsingQueue(Integer[] arr, int k) {
        if (arr.length < k) return "Invalid Input";

        // Max-Heap Priority Queue
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for (int next: arr) {
            queue.offer(next);
            if (queue.size() > k) queue.poll();
        }

        return String.valueOf(queue.peek());
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 4, 5, 10};

        // Input
        System.out.println("Input - " + Arrays.toString(arr));

        // Kth Largest Element
        System.out.println("Kth Largest Element By Sort - " + kthLargestElementBySort(Arrays.copyOf(arr, arr.length), 2));
        System.out.println("Kth Largest Element By Desc Sort - " + kthLargestElementByDescSort(
                Arrays.stream(Arrays.copyOf(arr, arr.length)).boxed().toArray(Integer[]::new), 2));
        System.out.println("Kth Largest Element Using Queue - " + kthLargestElementUsingQueue(
                Arrays.stream(Arrays.copyOf(arr, arr.length)).boxed().toArray(Integer[]::new), 3));

        // Kth Smallest Element
        System.out.println("Kth Smallest Element By Sort - " + kthSmallestElementBySort(Arrays.copyOf(arr, arr.length), 2));
        System.out.println("Kth Smallest Element By Desc Sort - " + kthSmallestElementByDescSort(
                Arrays.stream(Arrays.copyOf(arr, arr.length)).boxed().toArray(Integer[]::new), 2));
        System.out.println("Kth Smallest Element Using Queue - " + kthSmallestElementUsingQueue(
                Arrays.stream(Arrays.copyOf(arr, arr.length)).boxed().toArray(Integer[]::new), 3));
    }
}

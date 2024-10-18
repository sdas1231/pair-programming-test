package array.rotation;

import java.util.Arrays;

public class ArrayRotate {
    private static int[] rightRotate(int[] arr, final int elements) {
        final int length = arr.length;
        final int rotations = elements % length;
        System.out.println("Number of Right Rotations - " + rotations);
        for (int i = 0; i < rotations; i++) {
            int lastElement = arr[length - 1];
            for(int k = length - 1; k > 0; k--) {
                arr[k] = arr[k - 1];
            }
            arr[0] = lastElement;
        }
        return arr;
    }

    private static int[] leftRotate(int[] arr, final int elements) {
        final int length = arr.length;
        final int rotations = elements % length;
        System.out.println("Number of Left Rotations - " + rotations);
        for (int i = 0; i < rotations; i++) {
            int firstElement = arr[0];
            for(int k = 0; k < length - 1; k++) {
                arr[k] = arr[k + 1];
            }
            arr[length - 1] = firstElement;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // Array Right Rotate
        System.out.println("Input - " + Arrays.toString(arr));
        System.out.println("Output - " + Arrays.toString(rightRotate(Arrays.copyOf(arr, arr.length), 2)));

        // Array Left Rotate
        System.out.println("Input - " + Arrays.toString(arr));
        System.out.println("Output - " + Arrays.toString(leftRotate(Arrays.copyOf(arr, arr.length), 2)));
    }
}

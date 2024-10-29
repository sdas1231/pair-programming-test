package array.rotation;

import java.util.Objects;

public class StringRotation {
    private static boolean isRotation(String str1, final String str2) {
        if (Objects.isNull(str1) || Objects.isNull(str2) || str1.isEmpty() || str2.isEmpty() ||
                str1.length() != str2.length()) return false;

        return str1.concat(str1).contains(str2);
    }

    public static void main(String[] args) {
        System.out.println(isRotation("ABCD", "CDAB"));
    }
}

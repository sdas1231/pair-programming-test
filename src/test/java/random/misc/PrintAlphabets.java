package random.misc;

public class PrintAlphabets {
    public static void main(String[] args) {
        for (int i = 1; i < 27; i++) {
            System.out.println(i % 3 == 0 && i % 5 == 0 ? "Python - Java" :
                    (i % 3 == 0 ? "Python" :
                            (i % 5 == 0 ? "Java" : Character.getName((64 + i)))));
        }
    }
}

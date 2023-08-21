package RecursionAndCombinatorialProblems;

import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] elements = scanner.nextLine().split("\\s+");
        printReverseElements(elements, elements.length - 1);
    }

    private static void printReverseElements(String[] elements, int index) {
        if (index < 0){
            return;
        }
        System.out.print(elements[index] + " ");
        printReverseElements(elements, index - 1);
    }
}

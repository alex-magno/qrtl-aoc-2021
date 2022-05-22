package VerticalHorizontalPosition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VerticalHorizontalPosition {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader("../challenges/day2/data/input.txt"));

        String[] navigationArray = new String[1000];

        int i = 0;
        int j = 0;

        int horizontalPosition = 0;
        int verticalPosition = 0;


        while (scanner.hasNext()) {

            navigationArray[i++] = scanner.nextLine();
        }

        for (int k = 0; k < navigationArray.length; k++) {
            if (navigationArray[j].startsWith("forward ") && navigationArray[j].matches(".*[0-9].*")) {
                horizontalPosition += Integer.parseInt(navigationArray[j].replaceAll("[forward ]", ""));
                j++;
            } else if (navigationArray[j].startsWith("down ") && navigationArray[j].matches(".*[0-9].*")) {
                verticalPosition += Integer.parseInt(navigationArray[j].replaceAll("[down ]", ""));
                j++;
            } else if (navigationArray[j].startsWith("up ") && navigationArray[j].matches(".*[0-9].*")) {
                verticalPosition -= Integer.parseInt(navigationArray[j].replaceAll("[up ]", ""));
                j++;
            }
        }


        System.out.println(horizontalPosition);
        System.out.println(verticalPosition);

        System.out.println(horizontalPosition*verticalPosition);
    }
}


package AdventDay1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SonarScanner_Day1 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new FileReader("../challenges/day1/data/input.txt"));
        int[] depthDataArray = new int[2000];
        int i = 0;
        int increasedMeasurements = 0;


        while (scanner.hasNextInt()) {

            depthDataArray[i++] = scanner.nextInt();
        }

        for (int counter = 0; counter < (depthDataArray.length - 1); counter++) {

            if (depthDataArray[counter] < depthDataArray[counter + 1]) {
                increasedMeasurements++;
            }

        }
        System.out.println(increasedMeasurements);
    }
}



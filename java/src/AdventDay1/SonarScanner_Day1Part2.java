package AdventDay1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SonarScanner_Day1Part2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader("../challenges/day1/data/input.txt"));
        int[] depthDataArray = new int[2000];
        int i = 0;
        int increasedMeasurements = 0;

        while (scanner.hasNextInt()) {

            depthDataArray[i++] = scanner.nextInt();
        }

        for (int counter = 0; counter < (depthDataArray.length - 3); counter++) {

            if (depthDataArray[counter] + depthDataArray[counter + 1] + depthDataArray[counter + 2] < depthDataArray[counter + 1] + depthDataArray[counter + 2] + depthDataArray[counter + 3]) {
                increasedMeasurements++;
            }

        }
        System.out.println(increasedMeasurements);
    }
}

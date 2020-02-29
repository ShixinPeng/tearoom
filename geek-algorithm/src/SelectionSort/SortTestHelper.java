package selectionSort;

import java.util.Random;

public class SortTestHelper {

    public static int[] generateRandomArray(int count, int rangeL, int rangeR) {

        assert rangeL<=rangeR:"rangeL > rangeR";
        int[] arr = new int[count];

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt((rangeR-rangeL) + 1)+rangeL;
        }

        return arr;
    }

}

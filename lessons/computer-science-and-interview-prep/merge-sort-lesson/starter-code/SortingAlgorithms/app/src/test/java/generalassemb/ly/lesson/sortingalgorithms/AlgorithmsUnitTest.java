package generalassemb.ly.lesson.sortingalgorithms;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/* Do not modify this class */

public class AlgorithmsUnitTest {

    Algorithms algorithms = new Algorithms();

    int[] array1 = new int[]{1,9,0,10,23,54,21,11,14};
    int[] array2 = new int[]{200,-1,-24, 100, 1, 15,-23,65, 29, 1000, 2, -78};
    int[] array3 = new int[]{-1,-2,-3,-4,1,2,3,7};
    int[] array4 = new int[]{200, 12,23,1,0,100,500,24,290,956};
    int[] array5 = new int[]{
            1,9,0,10,23,54,21,11,14,
            200,-1,-24, 100, 1, 15,-23,65, 29, 1000, 2, -78,
            -1,-2,-3,-4,1,2,3,7,
            200, 12,23,1,0,100,500,24,290,956};


    @Test
    public void mergeSort_Check() throws Exception {

        assertArrayEquals(getSortedArray(array1), mergeSortArray(algorithms, array1));
        assertArrayEquals(getSortedArray(array2), mergeSortArray(algorithms, array2));
        assertArrayEquals(getSortedArray(array3), mergeSortArray(algorithms, array3));
        assertArrayEquals(getSortedArray(array4), mergeSortArray(algorithms, array4));
        assertArrayEquals(getSortedArray(array5), mergeSortArray(algorithms, array5));
    }


    private static int[] mergeSortArray(Algorithms algorithms, int[] array) {
        return algorithms.mergeSort(array);
    }

    private static int[] getSortedArray(int[] array) {
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        return sorted;
    }

}
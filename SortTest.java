/*
File: sorting.java
Author: Ian Woodruff
*/
import java.util.Arrays;
import java.util.Random;

public class SortTest {
    
    //quicksort in place
    //assume array is populated with data prior to this call
    private static void quickSort(int[] array, int p, int r){
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }
    
    //partition method to be used in quicksort
    private static int partition(int[] Array, int p, int r){
        
        int x = Array[r];
        int i = p - 1;
        
        for (int j = p; j <= r - 1; j ++) {
            if (Array[j] <= x) {
                i++;
                int temp1 = Array[i];
                Array[i] = Array[j];
                Array[j] = temp1;
        }
        }
        
        int temp2 = Array[i+1];
        Array[i + 1] = Array[r];
        Array[r] = temp2;
 
        return i + 1;
    }
    
    //countingsort
    //sorted result is returned as a new array
    //assume array is populated with data prior to this call
    private static int[] countingSort(int[] input, int kVal) {
        int[] auxiliary = new int[kVal];
        for(int i = 0; i < kVal - 1 ; i++) {
            auxiliary[i] = 0;
        }
        
        for (int j = 0; j < input.length; j++) {
            auxiliary[input[j]]++;
        }
        
        for (int i = 1; i < kVal; i++) {
            auxiliary[i] += auxiliary[i - 1];
        }
        
        int b[] = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            b[auxiliary[input[i]]- 1] = input[i];
            auxiliary[input[i]]--;
        }
        
        return b;
        }
        
    //return 1 if a is sorted (in non-decreasing order);
    //return 0 otherwise
    //assume array is allocated and populated with data prior to this call
    private static int isSorted(int[] array) {
        int output = 1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                output = 0;
            }
        }
        
        return output;
        
    }
    
    public static void main(String[] args){
        int NVALUE = 100000000;
        int KVALUE = 1000;
        int[] array = new int[NVALUE];
        int[] copy = array;
        
        Random rand = new Random();
        for (int i = 0; i < NVALUE - 1; i++) {
            array[i] = rand.nextInt(KVALUE);
        }
        
        
        long startTime1 = System.nanoTime();
        int[] sorted = countingSort(array, KVALUE);
        long endTime1   = System.nanoTime();
        long totalTime1 = endTime1 - startTime1;
        System.out.println("total execution time is " + totalTime1 + " nanoseconds");
        
        if (isSorted(sorted) == 1) {
            System.out.println("testing countingSort: passed\n");
        }
        else {
            System.out.println("testing countingSort: failed\n");
        }
        
        long startTime2 = System.nanoTime();
        quickSort(copy, 0, array.length - 1);
        long endTime2   = System.nanoTime();
        long totalTime2 = endTime2 - startTime2;
        System.out.println("total execution time is " + totalTime2 + " nanoseconds");

        if (isSorted(copy) == 1) {
            System.out.println("testing quickSort: passed\n");
        }
        else {
            System.out.println("testing quickSort: failed\n");
        }
        }
       
    } 

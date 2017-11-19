package org.deeaae.learnings.ds.sorting.quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void execute(){
        int[] array = new int[] {1,12,13,4,5,14,10};
        quickSort(array,0,array.length-1);
        System.out.println("Answer");
        print(array);

    }
    static void print(int[] array){
        System.out.println("");
        Arrays.stream(array).forEach(i-> System.out.print(" "+i+" "));
        System.out.println("");
    }
    static void swap(int[] array,int a, int b){
        int temp = array[a];
        array[a]=array[b];
        array[b]=temp;
    }
    static int partition(int[] array,int start, int end){
        // Choose Pivot
        int pivot = array[end];
        int entriesLessThanPivot=start-1;
        for(int i=start;i<end;i++){
            if(array[i] <= pivot){
                // array[i] is smaller and is
                // on left of pivot
                entriesLessThanPivot++;
                swap(array,entriesLessThanPivot,i);
            }
        }
        print(array);
        swap(array,entriesLessThanPivot+1,end);
        return entriesLessThanPivot+1;

    }
    public static void quickSort(int[] array, int start,int end){
        if(start<end) {
            int pivotPosition = partition(array, start, end);
            quickSort(array, start, pivotPosition - 1);
            quickSort(array, pivotPosition + 1, end);
        }

    }
}

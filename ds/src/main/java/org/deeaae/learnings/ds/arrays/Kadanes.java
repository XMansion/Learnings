package org.deeaae.learnings.ds.arrays;


public class Kadanes {
    public static void testKKadanes(){
        int[] array1 = new int[] {-2,-3,4,-1,-2,1,5,-3};
        int[] array2= new int[] {-1 -2 -3 -4};
        kadanes(array1);
    }

    /*
Given an array containing both negative and positive integers. Find the contiguous sub-array with maximum sum.
 */
    public static void kadanes(int[] array) {
        int max=0;
        int i=0;
        int currentMax=0;
        for(;i<array.length;i++){
            currentMax=currentMax+array[i];
            if(currentMax>0 && currentMax>max){
                max=currentMax;
            }
            if(currentMax<=0){
                currentMax=0;
            }
        }
        System.out.println("Max subarray: "+max);

    }
}

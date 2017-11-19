package org.deeaae.learnings.ds.arrays;

import java.util.Arrays;

import static java.lang.System.out;

public class Frequencies {
    public static void execute(){
        int[] array = new int[]{2,3,3,2,5};
        Arrays.stream(frequncies(array)).forEach(i-> System.out.println(i));

    }
    public static int[] frequncies(int[] array){
        int length = array.length;
        int i=0;
        while (i<length){
            int n = array[i];
            if(n>0){
                int index = n-1;
                if(array[index]<=0){
                    array[index]--;
                    array[i]=0;
                } else {
                    array[i]=array[index];
                    array[index]=-1;
                }
            }
            if(array[i]<=0){
                i++;
            }

        }
        return array;
    }
}

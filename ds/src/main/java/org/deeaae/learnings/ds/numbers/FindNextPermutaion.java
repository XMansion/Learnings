package org.deeaae.learnings.ds.numbers;

import java.util.ArrayList;

public class    FindNextPermutaion {
    public static void execute(){
        System.out.println(nextPermutation(123));
        System.out.println(nextPermutation(132));
        System.out.println(nextPermutation(213));
        System.out.println(nextPermutation(231));
        System.out.println(nextPermutation(312));
        System.out.println(nextPermutation(321));
    }

    /*
    inp -> out
    123 -> 132
    132 -> 213
    213 -> 231
    231 -> 312
    312 -> 321
    321 -> 123
     */
    public static int nextPermutation(int n){
        String str = ""+n;
        int[] array = new int[str.length()];
        int i=str.length()-1;
        while (n!=0){
            array[i]=n%10;
            n=n/10;
            i--;
        }
        int[] result=nextPermutation(array);
        int sum=0;
        int pow=0;
        for (int num=str.length()-1;num>=0;num--){
            sum+=result[num]*Math.pow(10,pow++);
        }
        return sum;

    }

    static void print(int[] array){
        System.out.println("");
        for(int i=0;i<array.length;i++){
            System.out.print(" ["+array[i]+"] ");
        }
        System.out.println("");
    }

    static int[] sort(int[] array,int start,int end) {
        for(int i=start;i<end;i++){
            for(int j=start;j<end;j++){
                if(array[i]<array[j]){
                    int temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }

    static int[] nextPermutation(int[] array){
        int n=array.length;
        int i=n-2;
        // Find the number which is smaller than what we just passed.
        while(i>=0){
            if(array[i]<array[i+1]){
                break;
            }
            i--;
        }
        if(i==-1){
            //Next largest number not available
            return sort(array,0,array.length);
        }
        // Find the smallest after i

        int min=Integer.MAX_VALUE;
        int minIndex=-i;
        int cntr=i;
        while (cntr<n){
            if(array[cntr]>array[i]){
                if(array[cntr]<min){
                    min=array[cntr];
                    minIndex=cntr;
                }
            }
            cntr++;
        }

        int temp = array[minIndex];
        array[minIndex]=array[i];
        array[i]=temp;
        sort(array,i+1,n);
        return array;
    }
}

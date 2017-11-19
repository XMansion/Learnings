package org.deeaae.learnings.ds.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void execute(){
        int[] array = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
        int[] array2 = new int[]{23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        LIS(array2  );

    }
    public static int[] LIS(int[] array){
        int[] lis = new int[array.length];
        int[] soln = new int[array.length];

        // Initializing it to 1
        for(int i=0;i<lis.length;i++) {
            lis[i] = 1;
            soln[i]=i;
        }

        for(int i=1;i<array.length;i++){
            for(int j=0;j<i;j++){
                if(array[j]<array[i]){
                    if(lis[i]<(lis[j]+1)){
                        lis[i]=lis[j]+1;
                        soln[i]=j;
                    }
                }
            }
        }

        int max=Integer.MIN_VALUE;
        int maxPos=-1;
        for(int i=0;i<lis.length;i++){
            if(lis[i]>max){
                max=lis[i];
                maxPos=i;
            }
        }

        System.out.println("LIS Size "+max);
        System.out.println("LIS Sequence "+max);
        int current = maxPos;
        int prev=-1;
        while(current>=0){
            System.out.print(array[current] + "\t");
            prev=current;
            current=soln[current];
            if(prev==current){
                break;
            }
        }
        return null;


    }
}

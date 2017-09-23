package org.deeaae.learnings.ds.arrays;

import java.util.HashMap;

public class FindSubarrayWithSumN {

    public static void executeFindSubarrayWithSumN(){
        int[] array1 = new int[] {-2,-3,4,-1,2,1,5,-3};
        int[] array2= new int[] {-1 -2 -3 -4};
        int[] array3= new int[] {1,2,3,4,5,6,7,8,9,10};
        findSubarrayWithSumN(array3,15);
    }

    public static void findSubarrayWithSumN(int[] array, int sum) {
        HashMap<Integer,Integer> sumUptillNode = new HashMap();

        int totalSum =0;

        for(int i=0;i<array.length;i++) {
            totalSum=totalSum+array[i];
            sumUptillNode.put(totalSum,i);
            int key=totalSum-sum;
            if(sumUptillNode.containsKey(key)){
                int startIndex=sumUptillNode.get(key);
                int endIndex=i;
                System.out.println(":Substring with sum "+sum);
                for(int j=startIndex+1;j<=endIndex;j++){
                    System.out.print("["+array[j]+"]");
                }
                System.out.println("");
            }
        }

        if(sumUptillNode.containsKey(sum)){
            int startIndex=-1;
            int endIndex=sumUptillNode.get(sum);
            System.out.println("Substring with sum "+sum);
            for(int j=startIndex+1;j<=endIndex;j++){
                System.out.print("["+array[j]+"]");
            }
            System.out.println("");
        }




    }
}

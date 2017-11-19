package org.deeaae.learnings.ds.arrays;

public class RotateArray {
    public static void execute(){
        int[] array2= new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] array3= new int[] {1,2,3,4,5,6};
        int k=5;
        for(int i=0;i<array3.length;i++){
            System.out.print(array3[i]+ " ");
        }
        System.out.println("\nShift "+k);
        int[] result = rotateArray(array3,k);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+ " ");
        }

        System.out.println("Pivot: "+pivotPoint(result,0,result.length-1));
    }

    public static int[] rotateArray(int[] array,int n){
        int a[] = new int[array.length];
        for(int i=0;i<array.length;i++){
            //swap i with i-n
            //if it is negtaive length+(i-n)
            int swapWithIndex = i-(n);
            if(swapWithIndex <0){
                swapWithIndex=array.length+swapWithIndex;
            }
            ///System.out.println(i+"::"+swapWithIndex);
            a[swapWithIndex]=array[i];
        }
        return a;
    }

    public static int pivotPoint(int[] array, int low, int high){
        //System.out.println(low+":"+high);
        if(high<low){
            return -1;
        }
        if(array[low] < array[high]){
            return low;
        }

        int mid = (high+low)/2;
        int n = array.length;
        if(array[mid]<array[(mid+n-1)%n] && array[mid]<array[(mid+1)%n]){
            return mid;
        }

        if(array[low]<=array[mid]){
            return pivotPoint(array,mid+1,high);
        } else if(array[mid]<=array[high]){
            return pivotPoint(array,low,mid-1);
        }

        return -1;

    }
}

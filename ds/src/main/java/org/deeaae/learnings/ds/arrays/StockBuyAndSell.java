package org.deeaae.learnings.ds.arrays;

public class StockBuyAndSell {
    public static void execute() {
        int[] dataArray0 = new int[] {7, 6, 5, 4, 3, 2};
        int[] dataArray = new int[] {7, 1, 5, 3, 6, 4};
        int[] dataArray2 = new int[] {100, 180, 260, 310, 40, 535, 695};
        findSellAndBuys(dataArray0);
        System.out.println("\n\n------------------\n\n");
        findSellAndBuys(dataArray);
        System.out.println("\n\n------------------\n\n");
        findSellAndBuys(dataArray2);

    }

    public static void findSellAndBuys(int[] dataArray){
        int min=Integer.MAX_VALUE;
        int minIndex=-1;
        int max=Integer.MIN_VALUE;
        int maxIndex=-1;
        for(int i=0;i<dataArray.length;i++){
            if(dataArray[i]<min){
                //Also sell at the last peak.
                if(minIndex!=-1 && maxIndex!=-1 && !(minIndex==maxIndex)) {
                    System.out.println("Buy at["+minIndex+"]: " + dataArray[minIndex]);
                    System.out.println("Sell at["+maxIndex+"]: " + dataArray[maxIndex]);
                }
                min=dataArray[i];
                minIndex=i;
                max=Integer.MIN_VALUE;
            }
            if(dataArray[i]>max){
                max=dataArray[i];
                maxIndex=i;
            }
        }
        if(min<dataArray[dataArray.length-1]){
            if(minIndex!=-1 && maxIndex!=-1) {
                System.out.println("Buy at["+minIndex+"]: " + dataArray[minIndex]);
                System.out.println("Sell at["+maxIndex+"]: " + dataArray[maxIndex]);
            }

        }

    }
}

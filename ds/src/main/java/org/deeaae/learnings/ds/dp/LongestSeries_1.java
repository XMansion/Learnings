package org.deeaae.learnings.ds.dp;

import javax.naming.InitialContext;
import java.util.HashMap;
import java.util.Map;

public class LongestSeries_1 {
    public static void execute(){
        Map<Integer,Integer> lookupTable = new HashMap<>();
        lookupTable.put(1,1);
        findMaxLengthSeries(lookupTable);



    }
    public static int findMaxLengthSeries(Map<Integer,Integer> lookupTable){

        for(int i=1;i<=100;i++){
            findSeriesFor(i,lookupTable,false);
        }
        Map.Entry<Integer,Integer> entry = lookupTable.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        int maxFor = entry.getKey();
        int maxChain=entry.getValue();
        System.out.println("MaxFor: " + maxFor);
        System.out.println("MaxChainSize: " + maxChain);
        System.out.println("\n");
        printWholeSeries(maxFor);
        //printWholeSeries(3);
        return maxFor;

    }
    public static void printWholeSeries(int n){
        System.out.print(" " + n + "  ");
        if(n==1){
            return;
        }
        if(n%2==0) {
            printWholeSeries(n / 2);
        }else {
            printWholeSeries(3*n+1);
        }

    }
    public static int findSeriesFor(int n,Map<Integer,Integer> lookupTable,boolean print){


        if(lookupTable.containsKey(n)){
            return lookupTable.get(n);
        }
        int seriesLength;

        if(n%2==0){
            seriesLength = findSeriesFor(n/2,lookupTable,print)+1;
        } else {
            seriesLength = findSeriesFor(((3*n)+1),lookupTable,print)+1;
        }
        lookupTable.put(n,seriesLength);
        return seriesLength;
    }
}

package org.deeaae.learnings.ds.dp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibbonaciNumber {
    public static void execute() {
        setFiboMap();
        System.out.println("\n---Rec---\n");
        int i =1;
        boolean isRecFailed=false;
        boolean isDPFailed=false;
        while(i<100){
            //System.out.print("["+i+":"+fiboRecursion(i)+"]  ");
            System.out.print("["+i+":"+fiboDP( i)+"]  ");
            i++;
        }
        System.out.println("\n");
        fiboMap.forEach((k,v)-> System.out.print(" ["+k+":"+v+"] "));

    }

    public static long fiboRecursion(int n) {
        if(n==1) {
            return 0;
        }
        if(n==2){
            return 1;
        }

        return fiboRecursion(n-1)+ fiboRecursion(n-2);
    }
    public static Map<Integer,Long> fiboMap;

    static void setFiboMap(){
        fiboMap = new HashMap();
        fiboMap.put(Integer.valueOf(1),Long.valueOf(0));
        fiboMap.put(Integer.valueOf(2),Long.valueOf(1));
    }

    public static long fiboDP(int n){
        if(fiboMap.containsKey(Integer.valueOf(n))){
            return fiboMap.get(Integer.valueOf(n)).longValue();
        }
        long value = fiboDP(n-1)+fiboDP(n-2);
        fiboMap.put(Integer.valueOf(n),Long.valueOf(value));
        return value;

    }
}

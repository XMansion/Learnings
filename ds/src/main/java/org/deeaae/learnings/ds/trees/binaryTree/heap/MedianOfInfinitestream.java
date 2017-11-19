package org.deeaae.learnings.ds.trees.binaryTree.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfInfinitestream {
    public static void execute(){
        int[] array = new int[]{5,15,1,3};
        MedianOfInfinitestream medianOfInfinitestream = new MedianOfInfinitestream();
        Arrays.stream(array).forEach(i-> System.out.println(medianOfInfinitestream.getMedian(i)));
        System.out.println("\n\n\n");
        int[] array2 = new int[]{4,5,1,2,6,5};
        medianOfInfinitestream.reset();
        Arrays.stream(array2).forEach(i-> System.out.println(medianOfInfinitestream.getMedian(i)));


    }
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    double root;

    public MedianOfInfinitestream() {
        reset();
    }
    public void reset(){
        maxHeap=new PriorityQueue<>(Collections.reverseOrder());
        minHeap=new PriorityQueue<>();
        root = Integer.MIN_VALUE;

    }
    public double getMedian(int num){
        int maxHeapSize_L = maxHeap.size();
        int minHeapSize_R = minHeap.size();

        if(maxHeapSize_L == minHeapSize_R){
            // Total elements before num were even
            // num will make it odd. Median will be the top element from bigger heap
            if(num < root){
                maxHeap.add(num);
                root = maxHeap.peek();
            } else {
                minHeap.add(num);
                root = minHeap.peek();
            }
        } else if(maxHeapSize_L > minHeapSize_R) {
            if(num < root){
                maxHeap.add(num);
                int temp = maxHeap.poll();
                minHeap.add(temp);
            } else {
                minHeap.add(num);
            }
            root = (maxHeap.peek()+minHeap.peek())/2.0;
        } else if(maxHeapSize_L < minHeapSize_R){
            if(num < root){
                maxHeap.add(num);
            } else {
                minHeap.add(num);
                int temp = minHeap.poll();
                maxHeap.add(temp);
            }
            root = (maxHeap.peek()+minHeap.peek())/2.0 ;
        }
        return root;
    }
}

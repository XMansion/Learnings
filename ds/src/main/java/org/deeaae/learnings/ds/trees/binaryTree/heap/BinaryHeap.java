package org.deeaae.learnings.ds.trees.binaryTree.heap;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryHeap<T extends Comparable> {

    public static void execute(){


        

        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        binaryHeap.push(1);
        binaryHeap.push(3);
        binaryHeap.push(6);
        binaryHeap.push(5);
        binaryHeap.push(9);
        binaryHeap.push(8);
        System.out.println(binaryHeap.extractMin());
        System.out.println(binaryHeap.extractMin());
        System.out.println(binaryHeap.extractMin());
        System.out.println(binaryHeap.extractMin());
        binaryHeap.push(2);
        binaryHeap.print();
        binaryHeap.decrease(2,1);
        binaryHeap.print();
        System.out.println(binaryHeap.extractMin());
        System.out.println(binaryHeap.extractMin());
        System.out.println(binaryHeap.extractMin());


    }
    private List<T> heapData;

    public void print(){
        System.out.print("\nHeap >");
        heapData.stream().forEach(i-> System.out.print(i));
        System.out.println("");
    }

    public BinaryHeap(){
        heapData=new ArrayList<>();
    }

    private int left(int i){
        if((2*i+1)<heapData.size()) {
            return (2 * i + 1);
        }
        return -1;
    }

    private int right(int i){
        if((2*i+2)<heapData.size()) {
            return (2 * i + 2);
        }
        return -1;
    }

    private int parent(int i){
        if(((i-1)/2)<heapData.size()) {
            return ((i-1)/2);
        }
        return -1;
    }

    private void swap(int i,int j){
        T temp = heapData.get(i);
        heapData.set(i,heapData.get(j));
        heapData.set(j,temp);
    }

    private  void heapify(int i){
        //print();
        int left = left(i);
        int right = right(i);
        int smallest =i;
        if(left!=-1 && heapData.get(smallest).compareTo(heapData.get(left))>0){
            smallest = left;
        }
        if(right!=-1 && heapData.get(smallest).compareTo(heapData.get(right))>0){
            smallest = right;
        }
        if(smallest!=i){
            swap(i,smallest);
            heapify(smallest);
        }
    }

    public void push(T data){
        heapData.add(data);
        int i = heapData.size()-1;
        while (i>0 && heapData.get(i).compareTo(heapData.get(parent(i))) < 1){
            swap(i,parent(i));
            i=parent(i);
        }
    }

    public void decrease(int index, T data){

        if(heapData.get(index).compareTo(data)>0) {
            int i = index;
            heapData.set(index,data);
            while (i > 0 && heapData.get(i).compareTo(heapData.get(parent(i))) < 1) {
                swap(i, parent(i));
                i = parent(i);
            }
        }
    }

    public T getMin(){
        return heapData.get(0);
    }

    public T extractMin(){
        swap(0,heapData.size()-1);
        T min = heapData.remove(heapData.size()-1);
        heapify(0);
        return min;

    }
}

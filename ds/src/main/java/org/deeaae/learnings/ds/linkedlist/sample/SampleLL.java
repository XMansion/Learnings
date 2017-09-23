package org.deeaae.learnings.ds.linkedlist.sample;

import org.deeaae.learnings.ds.linkedlist.LinkListNode;

public class SampleLL {
    public static LinkListNode getListFromArray(int[] dataArray) {
        LinkListNode start =null;
        LinkListNode prev = null;
        for (int data:dataArray) {
            if(prev == null) {
                start=new LinkListNode(data);
                prev=start;
            }else {
                LinkListNode temp = new LinkListNode(data);
                prev.next=temp;
                prev=temp;
            }
        }
        return start;
        
    }
    public static LinkListNode sample1(){
        int[] dataArray = new int[] {1,2,3,4,5,6,7,8,9};
        return getListFromArray(dataArray);
    }

    public static LinkListNode sample2(){
        int[] dataArray = new int[] {1,2,3,4,3,2,1};
        return getListFromArray(dataArray);
    }

    public static LinkListNode sample3(){
        int[] dataArray = new int[] {1,2,3,4,4,3,2,1};
        return getListFromArray(dataArray);
    }
    
}

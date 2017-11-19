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

    public static LinkListNode sample4(){
        int[] dataArray = new int[] {1,2,3,4};
        return getListFromArray(dataArray);
    }

    public static LinkListNode sample5(){
        int[] dataArray = new int[] {9,8,7,6,5};
        return getListFromArray(dataArray);
    }

    public static LinkListNode sample6(){
        int[] dataArray = new int[] {2,2,2,3,4,5,6,6,6,7,7,8,8,8,9,10,11,12,13};
        return getListFromArray(dataArray);
    }

    
}

package org.deeaae.learnings.ds.linkedlist;

public class LinkListNode {
    public LinkListNode next;
    public LinkListNode previous;
    public int data;

    public LinkListNode() {
        previous=null;
        next=null;
    }

    public LinkListNode(int data) {
        super();
        this.data=data;
    }

    @Override
    public String toString() {
        return " ["+data+"] ";
    }
}

package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

public class SumOfLL {

    public static void execute() {
        LinkListNode one = SampleLL.getListFromArray( new int[] {5,2,1});
        LinkListNode two = SampleLL.getListFromArray( new int[] {1,2,6,7});
        LinkListNode sum = printSumOfTwoLists(one,two,0);
        LLUtils.printList(sum);

    }


    /*
    Compute sum of reverse list
     */
    public static LinkListNode printSumOfTwoLists(LinkListNode one, LinkListNode two,int carry) {
        LinkListNode sumNode = new LinkListNode();
        if(one == null && two ==null) {
            return null;
        }
        if(one==null){
            one=new LinkListNode(0);
        }
        if(two==null){
            two=new LinkListNode(0);
        }
        int sum=one.data+two.data+carry;
        sumNode.data=sum%10;
        int newCarry =sum/10;
        sumNode.next=printSumOfTwoLists(one.next,two.next,newCarry);
        return sumNode;
    }
}

package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

public class MergeList {

    public static void execute() {
        LinkListNode sample1 = SampleLL.sample1();
        LinkListNode sample2 = SampleLL.sample5();
        LLUtils.printList(sample1);
        LLUtils.printList(sample2);
        LinkListNode result = mergeList(sample1,sample2);
        LLUtils.printList(result);
    }
    public static LinkListNode mergeList(LinkListNode first,LinkListNode second) {
        if(first==null) {
            return second;
        }

        if(second == null) {
            return first;
        }
        LinkListNode node = mergeList(first.next,second.next);
        first.next=second;
        second.next=node;
        return first;
    }
}

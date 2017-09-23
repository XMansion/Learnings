package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

public class ReverseLinkList {
    public static void execute(){
        LinkListNode node = SampleLL.sample1();
        LLUtils.printList(node);
        node = LLUtils.reverseLinkList(node);
        LLUtils.printList(node);
    }


}

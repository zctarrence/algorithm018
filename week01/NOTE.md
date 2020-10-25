学习笔记
Week01
心得：
第一周的学习感觉还是非常充实的。在工具上，从eclipse改为了intellij，尝试了新的工具。在内容上，
对数组、链表、栈、队列的知识点有了更深入的认识和了解。另外对刷题方法也有了很好的实践，
使用五步刷题法。总的来说感觉收获满满。

另外，本周的时间安排不够好，没有把握好节奏，前半周放在学习上的时间不够多，导致本次作业中分析源码还没来得及做，
在后面要调整。github也是第一次接触，不熟练，还需要再看看实例和教程。

在本周学习中，感觉比较有成就感的是在做leetcode第24题两两交换链表中节点的时候。初次做的时候，
代码如下：
public ListNode swapPairs(ListNode head) {
        ListNode ret = new ListNode(-1);
        ret.next = head;
        ListNode prev = ret;
        while (head != null && head.next != null) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            prev.next = secondNode; 
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            prev = firstNode;  
            head = firstNode.next;
        }
        return ret.next;
    }
当时自己在想，可不可以偷个懒，把firstNode以及secondNode全部用head表示，经过尝试
这是不行的，因为这样会产生环（head.next.next = head)。刚开始自己没有注意到这点，想了很久，
找不出原因。因为当时自己觉得head.next就是表示secondNode，这样换没有问题，但是后面
自己画图后才发现：
prev.next = head.next;
head.next = head.next.next;
head.next.next = head; 
第三行head.next.next中的head.next已经不是secondNode了。
这个问题也让我意识到在做链表类问题时，要注意每个节点的指向，要熟练理解变化的模板，
有时候一个小疏忽，会有大错误。


作业补充：
①用 add first 或 add last 这套新的 API 改写 Deque 的代码：
Deque<String> deque = new LinkedList<>();

deque.addLast("a");
deque.addLast("b");
deque.addLast("c");
System.out.println(deque);

String str = deque.getFirst();
System.out.println(str);
System.out.println(deque);

while (deque.size() > 0) {
    System.out.println(deque.removeFirst());
}
System.out.println(deque);
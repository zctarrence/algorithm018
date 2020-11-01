学习笔记<br>
(感谢老师的点评，老师辛苦啦！本次笔记分为三部分：本周感想、上周回顾和HashMap小总结）<br>

### 本周感想：
本周学习的内容感觉难度适中，听课时可以接受，但是在做题时会有些卡顿。让人觉得比较郁闷的是，之前在上体验课的时候，接触过部分课后作业的题目，但是再次做的时候反应不够快。还是需要坚持践行五毒神掌！
<br>
### 上周回顾：
本周坚持了五毒神掌，把上周的题目均多次进行了回顾，对于84，42这两道用栈和队列解决的题目，在第二、三遍回顾时，还是感觉有些疙瘩，不是很熟练。深深的感受到，写这类代码，不仅要理解思路，同时需要实打实的进行操作，写下来，因为有很多的细节的问题需要处理，需要想通。比如84题，柱状图中的最大的矩形中，有一段代码如下：<br>
```
 maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1)); //Java
 ```
 此处先进行了pop,后进行了peek方法，但是自己再次写的时候把二者顺序写反了，写成了：

```
 maxArea = Math.max(maxArea, heights[stack.peek()] * (i - stack.pop())); //Java
 ```
结果出现了错误，因为对于(i - stack.pop())不能将所有情况的底的长度都表示出来，当左端是在开始的位置时，这样表示的底的长度就是错误的，因为差了偏移量1。而这又让我对刚开始要在stack中加入-1这个值有了更深的理解。在之前只是记住这是一个偏移量，但现在更加清晰了。
 <br>
 ### HashMap小总结
 HashMap是一个用于存储“键（key）—值(value)”对的集合，可以看成是上层数组加下层链表的结构。<br>
使用HashMap进行查找的操作就类似于在使用一本英汉字典：比如我们在查找“geek”单词的意思，假设这个字在第600页，那么我们会翻到第600页，在这一页上的众多单词中寻找“geek”单词。在上述过程中，字典的每一页相当于HashMap的上层的数组；第600的所有单词，这对应到HashMap中就是一个存有信息链表；页码“600”在HashMap中相当于HashCode，散列值，可以理解为数组的下标。我们通过key的HashCode(散列值)快速的定位到这个链表，再从链表中寻找到value的值。<br>
使用HashMap进行插入的话，过程与查找类似，我们通过散列值定位，然后寻找是否已经存在value值，如果没有则加入，若存在则覆盖。
以下为对于put和get函数的分析：<br>
<br>
**put函数：**
<br>
在put方法处会调用putVal()方法：<br>
```
 return putVal(hash(key), key, value, false, true);  //Java
 ```
这边hash指的是key的hash值，key就是key，value为key对应的要放入的值.
<br>
①若第一次put，table属性是空，此时需要revise，创建变量并且得到当前的数组的长度
```
if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
 ```
②如果在数组中，hash值的位置为空，那么直接创建存入，否则进入else部分
```
if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);

 ```
③else部分（hash值处不为空的情况）：
```
if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
```
④判断添加的对象是否是树，调用putTreeVal方法。
```
else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
```
⑤如果新添加的对象不与旧对象相同，也不是TreeNode，那么遍历node。<br>
如果遍历到了node的最后一个，便把新的对象添加进入。<br>
如果遍历到的node不是最后一个，且比较了key和hash值都相同，则跳出循环。
```
else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                 if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
```
⑥判断找到的node是否为空。若不为空，在设置旧值可以修改的情况下，改变旧值，否则返回原来的旧值。
```
if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
```
⑦判断是否需要扩大容量。
```
if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
```
<br>
**get函数：**
<br>
get调用了getNode方法，若结果为空，则返回空，否则返回e.value
```
return (e = getNode(hash(key), key)) == null ? null : e.value;
```
①首先判断HashMap中的数组是否为空以及长度是否大于0
```
if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null)
```
②获取key下标对应的链表对象，并比较第一个是否满足，若满足则返回第一个。
```
if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
```
③判断下一个对象是否为空，是的话则没有找到对应的值。若不是空，则判断是否是TreeNode，是的话用树的方法解决。
```
if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
```
④如果不是树，则比较key的值是否对应，如果对应则返回，每一次比较完后，都检查下当前是否是最后一个节点。
```
do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
```
⑤如果都不符合，则返回null。
`return null;`
<br>
由于内功不够深厚，在看代码的时候查阅了一些资料，链接如下：
https://blog.csdn.net/kai3123919064/article/details/90343177。


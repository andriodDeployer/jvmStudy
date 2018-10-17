/**
 * Created by DELL on 2018/10/11.
 *
 * 垃圾回收(gc)，主要包含两个层面，1.找垃圾，2.回收垃圾
 *  这是两个层面的东西，互不影响，正交的关系，可以分别来实现。
 *  找垃圾：常用的算法有引用计数(很少使用，存在循环引用的漏洞)，可达性分析(通过枚举GCRoot，集合safepoint来实现)
 *  回收垃圾：主要有三种算法：标记清除，标记压缩，复制算法。这三种算法各有优缺点，更具常见选择合适的算法实现。
 *  清除法-简单，但易产生碎片，可能总空间够但分配不了的问题
    压缩法-能解决清除法的问题，但是复杂且耗性能
    复制法-折衷一些，但是空间利用率低，总之，各有千秋


    就像Integer在应用程序中，大部分都是-128到127之间的数，所以jdk在后来的版本中，对改范围中Integer对象进行了缓存。
    通常经过统计分析对象的生命周期发现：大部分对象存活时间很短，少部分存活下来的对象，会存在很长时间，因为这个现象，它造就了
    垃圾收集的分代理论。然后在不同的代上采用不同的垃圾收集算法。


 *
 */
package gc;
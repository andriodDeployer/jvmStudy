package heapAreaAnay;

/**
 * Created by Administrator on 2018-7-15 0015.
 */

public class BigObjectEnterOldGen {

    private static final int _1MB = 1024 * 1024;

    /**
     *
     * -verbose:gc -Xms20M -Mmx20M -Xmn10M -XX:+printGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * 总共堆大小20M
     * 新生代10M，老年代10M
     * 在新生代的10M中有，edan:8M,两个suv各1M，也就是新生代可用空间可用的共有9M，
     * 因为新生代采用的复制算法，所以总会有一部分空间是不可用的，那一部分就是suv的空间1M
     *
     * 在分配大对象时，新生代中连续空间不足的可能性更大，更容易发生gc，同时发生gc后，对象在edan和suvisor中来回复制消耗性能。
     * 为了防止这些情况发生，通过设置参数PretenureSizeThreshold，可以将大小大于PretenureSizeThreshold的对象直接放到老年代中，(所谓直接的意思就是进入老年代的条件不同了，不用等到新生代装不下了才会放进老年代，只要对象大小的条件满足了，就放进去了)
     * 避免了新生代的上述问题，
     * 但是，如果这个大对象，是朝生夕灭的话，那么他在老年代真的很浪费空间，因为老年代中的对象不会轻易被gc掉，只有老年代不足的时候，才会发生老年代的gc。
     *
     * @param args
     */
    public static void main(String[] args){
        byte[] allocation1;
        allocation1 = new byte[4 * _1MB]; //因为这个对象大于指定的3M，所以这个对象会直接放到老年代中，不会发生gc。
    }
}

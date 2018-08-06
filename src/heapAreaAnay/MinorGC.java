package heapAreaAnay;/**
 * Created by Administrator on 2018-7-15 0015.
 */

/**
 * user is lwb
 **/


public class MinorGC {

    private static final int _1MB = 1024 * 1024;

    /**
     *
     * -verbose:gc -Xms20M -Mmx20M -Xmn10M -XX:+printGCDetails -XX:SurvivorRatio=8
     * 总共堆大小20M
     * 新生代10M，老年代10M
     * 在新生代的10M中有，edan:8M,两个suv各1M，也就是新生代可用空间可用的共有9M，
     * 因为新生代采用的复制算法，所以总会有一部分空间是不可用的，那一部分就是suv的空间1M
     *
     * @param args
     */

    public static void main(String[] args){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[2 * _1MB];//在分配这个空间时，新生代已经占用了6M(新生代可用的共9M)，allocation4无法存放进去新生代。此时会在新生代中发生一次gc，








    }
}

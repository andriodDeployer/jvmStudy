package heapAreaAnay;/**
 * Created by Administrator on 2018-7-15 0015.
 */

/**
 * user is
 **/


public class OldObjectEnterOldGen {

    /**
     * -Xms20M -Xmx20M -Xmn10M  -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1
     *
     * 总共堆大小20M
     * 新生代10M，老年代10M
     * 在新生代的10M中有，edan:8M,两个suv各1M，也就是新生代可用空间可用的共有9M，
     * 因为新生代采用的复制算法，所以总会有一部分空间是不可用的，那一部分就是suv的空间1M
     *
     * 通过设置参数MaxTenuringThreshold，可以将年龄大于MaxTenuringThreshold的对象直接放到老年代中(所谓直接的意思就是进入老年代的条件不同了，不用等到新生代装不下了才会放进老年代，只要年龄的条件满足了，就放进去了)
     * 避免了新生代的上述问题，
     * 同时也不会发生按照大小的条件直接将大对象发进去产生朝生夕灭的问题，通常年龄能达到一定的数量，就不会出现朝生夕灭的问题。
     * 但是会出现大对象的问题。
     *
     * 年龄的确定：当新生代发生一次gc后，这个对象没有被gc掉，同时由于大小合适，另一个suvisor可以存放的下，那么这个对象的年龄就会增大一岁。
     *
     *
     * @param args
     */
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[1/2 * _1MB];
        allocation2 = new byte[4 * _1MB];

        allocation3 = new byte[4 * _1MB];
        allocation3 = null;//那个allocation3当前应用的对象变成“垃圾”，此时新生代已经占用了8.25M

        allocation3 = new byte[4 * _1MB];//当给这个对象分配内存时，此时新生带空间不足，会在新生代发生minorgc，将allocaton3引用的对象4M空间释放，gc后，需要进一个4M和一个0.25M的对象放到1M的survior中，4M的对象放不下，直接进入老年代，另一个0.25M的对象，因为年龄为1了，所以也需要进入老年代中。

        System.out.println(allocation3.length);

    }

}

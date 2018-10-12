package gc;

// time java SafepointTestp
// 你还可以使用如下几个选项
// -XX:+PrintGCDetails
// -XX:+PrintGC
// -XX:+PrintGCApplicationStoppedTime //gc线程想要gc，但是applicationThread还没有到达安全点，那么gc线程就需要等待，等到applicationThread全部到达安全点后，gc线程开始执行gc，打印出gc线程等待的时间。这个时间的长短主要取决于safepoint设置的密度。密度越大等待的时间越短，密度越小等待的时间就越长。
// -XX:+PrintSafepointStatistics
// -XX:+UseCountedLoopSafepoints
public class SafepointTest {
  static double sum = 0;

  public static void foo() {
    //经过测试发现，将循环变量的类型设置为int和long，gc等待的时间是不同的，主要是因为在jit在进行优化时，对于计数循环和非计算循环的处理是不同的。
    //对于计数循环在每次循环结尾不加safepoint，而是在整个循环结束才加一次，safepoint密度低。
    //对于非计数循环，在每次循环结尾都会加上safepoint。
    //计数循环：对于int类型的循环变量i来说：1)基于该循环变量的循环出口只有一个，即i < limit，2) 循环变量随着迭代的增量为常数，例子中i++即增量为1，以及循环变量的上限(当增量为负数时则是下限)为循环无关的，即limit应是循环无关
    //如果循环变量的类型为long的话，直接作为非计算循环处理。safepoint密度高，基本不会出现很长的gc等待时间。

    //gc时间：gc等待的时间+寻找垃圾时间+垃圾回收时间。(寻找垃圾时间+垃圾回收时间=stw的时间)
    //程序中的计时:程序执行的时间和gc时间的和。他与gc等待时间没有直接的关系。ps使用long和int执行foo不仅gc等待时间变短了，但是程序中的计时时间却变长了，两者没有关系，
    long current = System.currentTimeMillis();
    for (int i = 0; i < 0x77777777; i++) {
      sum += Math.sqrt(i);
    }

    System.out.println(System.currentTimeMillis() - current);

  }

  public static void bar() {
    long current = System.currentTimeMillis();
    for (int i = 0; i < 50_000_000; i++) {
      new Object().hashCode();
    }
    System.out.println(System.currentTimeMillis() - current);
  }



  public static void main(String[] args) {
    new Thread(SafepointTest::foo).start();
    //new Thread(SafepointTest::bar).start();
  }
}

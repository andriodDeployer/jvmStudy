package jvmTool.jmh;/**
 * Created by DELL on 2018/10/17.
 */

/**
 * user is lwb
 **/


public class LoopPerformanceTest {

    /**如果i的类型为int的化，jit会启用优化，优化的结果时循环不会执行，而是直接返回10^9,
     * 如果是long的话，jit不会对其进行优化，虽然最终的i的值也是10^9，但是这个结果是循环计算出来的很耗时,
     *
     * @return
     */
    static long foo() {
        long i = 0;
        while (i < 1_000_000_000) {
            i++;
        }
        return i;
    }



    public static void main(String[] args) {
        long current = System.nanoTime();
        foo();
        long temp = System.nanoTime();
        System.out.println(temp - current);
        // warmup
//        for (int i = 0; i < 20_0; i++) {
//            foo();
//        }
        // measurement
      //  long current = System.nanoTime();
      /*  for (int i = 1; i <= 10_000; i++) {
            foo();
            if (i % 1000 == 0) {
                long temp = System.nanoTime();
                System.out.println(temp - current);
                current = System.nanoTime();
            }
        }*/
    }





}

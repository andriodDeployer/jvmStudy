package jmm;/**
 * Created by DELL on 2018/10/9.
 */

/**
 * user is lwb
 *
 * 双检锁(DCL:double check locking):是实现单利模式(懒加载)的一种高效方式。但是由于虚拟机没有严格实现jmm，导致在多线程环境下，会出现问题，
 * 具体出现问题的原因：对于代码：instance = new Instance();直觉来讲，构造函数的调用似乎应该在instance变量赋值前完成，但是由于指令重排序优化的的原因，
 * 完全有可能先new出来一个空的为调用过构造函数的instance对象(属性值全为空)，赋值给instance引用，然后在对instance对象进行初始化。
 * 这种重排序对与单线程是没有影响的，但是对于多线程会产生很大的影响，可能导致其他线程获取到一个属性值全为空的对象。
 *
 **/


public class DCLTest {

    class Test{
        private int a = 0;

        public int getA() {
            return a;
        }

        private Test(){
            System.out.println(instance);
            a=100;
            System.out.println(instance);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private  Test instance = null;
    public Test getInstance(){
        if(instance == null){
            synchronized (DCLTest.class){
                if(instance == null){
                    instance = new Test();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) throws InterruptedException {
            DCLTest dclTest = new DCLTest();
            new Thread(){
                @Override
                public void run() {
                    Test test = dclTest.getInstance();
                    System.out.println(test.getA());
                }
            }.start();
        Thread.sleep(1000);
        new Thread(){
            @Override
            public void run() {
                Test test = dclTest.getInstance();
                System.out.println(test.getA());
            }
        }.start();
    }
}

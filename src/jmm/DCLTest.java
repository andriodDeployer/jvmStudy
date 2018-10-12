package jmm;
/**
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
 * 关于new关键字创建一个对象的具体细节：根据编译器编译A a = new A(),这段代码的字节码可以看出：
 *
        0: new           #6                  // class jmm/A
        3: dup
        4: invokespecial #7                  // Method jmm/A."<init>":()V
        7: astore_1
        8: return

    1.首先执行jvm中的new指令，在堆中分配内存空间然后并将内存地址压入到操作数栈顶。new的作用，1.分配内存空间，2.将空间地址放到栈顶。
    2.然后执行dup指令，复制操作数栈顶值，然后在压入操作数栈顶中。此时操作数栈中，由连续两个相同的操作数在栈顶部位。
    3.执行invokespecial指令(执行实例方法),取出栈顶操作数，执行实例方法(实例初始化方法时实例方法)，也即是实例初始化方法，也即是构造器方法。
    4.执行astore_1命令(就是给a变量赋值),取出栈顶操作数，存放到局部变量表中的1号位置。至此栈顶上的连个相同的操作数据已经被全部取出来
    5.执行return命令，退出当前栈帧。

    可以看出：new指令和invokespecial指令时两个独立的执行，虽然两只总是成对出现，但是两者却构不成原子操作，也就是说两者之间可以插入其他指令，
    在cpu重排序的过程中，完全可以把astore_1命令放在new和invokespecial之间，这样的话，就会使a变量得到一个为没有初始化的A对象，也就是上面说的DCL的不安全问题。

    此外dup和new也是成对出现的，之所以这样，是因为调用构造器函数(实例方法)需要使用一个，第二个让程序员使用，如这里的astore_1.
    当然程序员可以不使用第二个，如new A(),那么此时操作数栈顶的操作数就没有被程序员使用，但是这种情况很少见，很少有创建一个对象，不使用它的情况。但是对于这种情况
    编译器会在invokespecial指令下，生成一个pop指令，将栈顶操作数出栈。这也就是为什么创建一个对象，总是要有一个dup指令。


 *
 *
 *
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
       /*     DCLTest dclTest = new DCLTest();
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
        */
        A a = new A();
    }

}

class A {}

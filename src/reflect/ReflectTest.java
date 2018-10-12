package reflect;/**
 * Created by DELL on 2018/8/9.
 */

import java.lang.reflect.Method;

/**
 * user is lwb
 *
 * 反射调用之所以效率低主要由一下原因，同时也是提升反射调用效率的着手点：
 *  1.变长参数，method.invoke的第二个参数的类型时变长的，编译器会生成已给数组作为入参。
 *  2.基本类型的拆装箱和缓存问题。
 *  3.方法内联的是否被使用。
 *
 **/


public class ReflectTest {

    public static void target(int i){
       // new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] agrs) throws Exception {
//        Class<?> kclass = Class.forName("reflect.ReflectTest");
//        Method method = kclass.getMethod("target",int.class);
//        method.invoke(null,0);
        test_v0();
        System.out.println("==========================");
        test_v1();
        test_v2();
        test_v3();
        test_v4();
    }

    public static void test_v0() {
        long current = System.currentTimeMillis();
        for(int i = 1; i<=2_000_000_000;i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            ReflectTest.target(0);
        }
    }


    public static void test_v1() throws Exception {
        Class<?> kclass = Class.forName("reflect.ReflectTest");
        Method method = kclass.getMethod("target",int.class);

        long current = System.currentTimeMillis();
        for(int i = 1; i<=2_000_000_000;i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            method.invoke(null,128);
        }
    }

    //减少值为128的integer对象的创建，因为integer默认情况下，会缓存-128-127之间的integer对象，可以通过-Djava.lang.IntegerCache.high=128
    public static void test_v2() throws Exception {
        Class<?> kclass = Class.forName("reflect.ReflectTest");
        Method method = kclass.getMethod("target",int.class);
        Integer integer = 128;
        long current = System.currentTimeMillis();
        System.out.println("使用integer的缓存");
        for(int i = 1; i<=2_000_000_000;i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            method.invoke(null,integer);
        }
    }

    //因为method.invoke的第二个参数是变长的，也就是在调用这个方法是，编译器会产生一个包含入参数的数组，作为方法的入参。在循环外部产生一个数组。
    //按道理来说，v3应该比v2耗时更少，但是结果却是更长，主要原因在于：v2中虽然每次调用都会产生一个数组，但是创建的数组，不会逃逸，也就是创建的数组不会在堆上分配空间
    //当在循环外创建了一个数组的话，那么这个数组相对于循环来说已经逃逸了，无法采用内联优化。耗费的时间反而更多了。
    public static void test_v3() throws Exception {
        Class<?> kclass = Class.forName("reflect.ReflectTest");
        Method method = kclass.getMethod("target",int.class);
        Integer[] agr = {128};
        long current = System.currentTimeMillis();
        System.out.println("消除变长参数语法糖");
        for(int i = 1; i<=2_000_000_000;i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            method.invoke(null,agr);
        }
    }

    //在反射的实现中，动态实现是基于字节码的，在第一次使用动态实现时由于需要生成字节码，相对本地实现很耗时，但是当字节码生成之后，动态实现比本地实现的效率要提升很多。
    //所以对一个method对象设置了一个阈值，当超过了这个阈值，就是用动态实现，当低于这个阈值就是用本地实现。这个阈值默认情况下为15.
    //通过设置-Dsun.reflect.noInflation=true,可以直接使用动态实现。不使用委派的本地实现。
    public static void test_v4() throws Exception {
        Class<?> kclass = Class.forName("reflect.ReflectTest");
        Method method = kclass.getMethod("target",int.class);
        method.setAccessible(true);
        long current = System.currentTimeMillis();
        System.out.println("去掉inflation的限制，直接使用动态实现");
        for(int i = 1; i<=2_000_000_000;i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            method.invoke(null,128);
        }
    }





}

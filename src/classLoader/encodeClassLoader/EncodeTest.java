package classLoader.encodeClassLoader;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.util.Date;

/**
 * user is lwb
 **/


public class EncodeTest {

    public static void main(String[] args) throws Exception{
        //有包名的类无法使用没有包名的类,没有包名的类可以使用有包名的类
//        ClassLoaderAttachment classLoaderAttachment = new ClassLoaderAttachment();
//        System.out.println(classLoaderAttachment.toString());


        //checkClassAttachment();
        //systemLoadClass();
        myLoadClass();




    }




    public static void checkClassAttachment(){
        //当启动虚拟机时，将EncodeTest这个类加载到方法区中的时候，会对这个类依赖的类进行加载，也就是会加载ClassLoaderAttachment(使用的类加载器时appclassloader,加载范围为classpath)
        //将类加载到方法区后，会进行一个验证的过程，如：魔数，版本等。如果此时ClassLoaderAttachment的class文件是乱码的话，那么验证就通不过，
        // 也就是在程序运行之前就会是虚拟机停下来了。如果把class文件按删除掉的话，就会爆出符号找不到的错误。
//        ClassLoaderAttachment classLoaderAttachment = new ClassLoaderAttachment();
//        System.out.println(classLoaderAttachment.toString());
    }


    private static void systemLoadClass() throws Exception {
        //下面这段代码，是在程序运行时进行类加载，属于动态加载类。如果加载的这个类的class文件时加密的，那么也会在类加载的验证阶段出现问题，但此时抛出的问题属于运行时错误了。
        Class<?> clazz1 = new EncodeTest().getClass().getClassLoader().loadClass("classLoader.encodeClassLoader.ClassLoaderAttachment");
        System.out.println(clazz1.newInstance().toString());
    }


    private static void myLoadClass() throws Exception{
        //在双亲委托模式下：要想让自定义的类起作用，那么就要保证，它的父类加载器找不到这个类(不是无法处理这个类，如加密的class，即使父类加载器无法处理，只要它找到了，那么自定义的类加载器就没有工作的机会)
        //也就是说，自定的类加载器的加载范围要在父类加载器作用这外。也就是父类加载器记载的话会爆出classNotFoundExcetption的异常的时候，让自定义的类加载器去加载。

        try {
            Class clazz = new DecodeClassLoader("encodedClass")
                    .loadClass("classLoader.encodeClassLoader.ClassLoaderAttachment");
            //这里要用Date(ClassAttachment的父类)，因为如果使用了ClassLoaderAttachment的话，
            // 在EncodeTest在加载的过程中，会对这个类依赖的类也进行加载，并验证，也就出现了验证不通过的错误。
            Date attachment1 =
                    (Date) clazz.newInstance();
            System.out.println(attachment1.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

package classLoader.encodeClassLoader;/**
 * Created by Administrator on 2018-8-5 0005.
 */

/**
 * user is lwb
 **/


public class EncodeTest {

    public static void main(String[] args) throws Exception{
        //有包名的类无法使用没有包名的类,没有包名的类可以使用有包名的类
//        ClassLoaderAttachment classLoaderAttachment = new ClassLoaderAttachment();
//        System.out.println(classLoaderAttachment.toString());


        //在使用这个类之前，需要将在这个类进行加载，不过不是让jvm来控制加载，而是采用我们自定义的类加载器进行加载。
        //但是我们自定义的类加载器也是遵循双亲委托
//

        Class<?> clazz1 = new EncodeTest().getClass().getClassLoader().loadClass("ClassLoaderAttachment");
        clazz1.newInstance();
        ClassLoaderAttachment attachment = new ClassLoaderAttachment();
        System.out.println(attachment.toString());

//        try {
//            Class clazz = new DecodeClassLoader("encodedClass")
//                    .loadClass("ClassLoaderAttachment");
//
//             Date attachment1 =
//                     (Date) clazz.newInstance();
//            System.out.println(attachment1.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

package classLoader;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.io.InputStream;

/**
 * user is lwb
 **/


public class MyClassLoader {

    public static void main(String[] args){

        /**
         * 先用自己的类加载器去加载一个class字节流，如果自己的加载器加载不到的话，再使用双钱委托的方式进行加载。
         */
        ClassLoader classLoader = new ClassLoader() {
            //一般情况下不要重写loadclass方法，因为这个方法在父类中实现了双亲委托的逻辑，
            //如果要要加载自己想要加载的类，重写自定义classloader的findclass方法就可以了，这样就可按照自定义的findclass方法中的逻辑去加载制定的class字节流了
            //如果要破环双亲委托方式(双亲委托的方式去加载类是一种java推荐的类加载方式，并不是强制要求的，我们也可以通过重写loadclass的方式使用我们自定义的类加载的顺序)
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    //如果在这个文件不在当前类的同意路径下的话，也就是这个类找不到的话，就是用双亲委托的方式去加载。
                    if(is == null){
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);

                    //defineClass方法是jvm根据class字节流生成一个class对象，放在内存中，在hotspot中放在方法去中。这个方式最好不要重写。
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
                return null;
            }
        };



        ClassLoader classLoader2 = new ClassLoader() {
            //findClass是自定义类加载器加载在双亲无法加载的时候，自定义的的家寨前按照findclass制定的逻辑去加载。
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
                return null;
            }
        };

        while (classLoader.getParent()!=null){
            classLoader = classLoader.getParent();
            System.out.println(classLoader.getClass().getName());
        }




    }

}

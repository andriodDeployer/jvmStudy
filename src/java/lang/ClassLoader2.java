package java.lang;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.io.InputStream;

/**
 * user is lwb
 **/


public class ClassLoader2 extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
            InputStream is = getClass().getResourceAsStream(fileName);
            //如果在这个文件不在当前类的同意路径下的话，也就是这个类找不到的话，就是用双亲委托的方式去加载。
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name,b,0,b.length);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return null;

    }
}

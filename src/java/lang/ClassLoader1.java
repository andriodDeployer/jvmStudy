package java.lang;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.io.InputStream;

/**
 * user is lwb
 **/


public class ClassLoader1 extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try{
            String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
            InputStream is = getClass().getResourceAsStream(fileName);
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
}

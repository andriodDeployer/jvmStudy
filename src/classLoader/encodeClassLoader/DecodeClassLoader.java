package classLoader.encodeClassLoader;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * user is lwb
 **/


public class DecodeClassLoader extends ClassLoader {

    private String classDir;

    public DecodeClassLoader( String classDir) {
        this.classDir = classDir;
    }

    //去一个指定的位置(不在父类加载器的范围内)加载class字节流
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFileName = classDir + "\\" + name + ".class";
        try{
            InputStream inputStream = new FileInputStream(classFileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MyClassLoaderEncoder.cypher(inputStream,bos);
            inputStream.close();
            byte[] bytes = bos.toByteArray();
            System.out.println("执行解密加载器");
            return defineClass(bytes,0,bytes.length);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;


    }
}

package classLoader.encodeClassLoader;/**
 * Created by Administrator on 2018-8-5 0005.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * user is lwb
 **/


public class MyClassLoaderEncoder {

    public static void main(String[] args) throws Exception{

        String srcPath = "D:\\workRequire\\workspace\\ideaWorkSpace\\jvmStudy\\encodedClass\\ClassLoaderAttachment.class";
        String descPath = "encodedClass\\ClassLoaderAttachment.class";
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(descPath);
        cypher(fis,fos);


    }

    public static void cypher(InputStream in, OutputStream os) throws Exception{
        int b = -1;
        b = in.read();
        while (b != -1){
            os.write(b ^ 0xff);
            b = in.read();
        }

    }

}

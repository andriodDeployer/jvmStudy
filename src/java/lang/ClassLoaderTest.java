package java.lang;
/**
 * Created by Administrator on 2018-8-5 0005.
 */

/**
 * user is
 **/

public class ClassLoaderTest {

    public static void main(String[] args){


        try{
            ClassLoader2 classLoader2 = new ClassLoader2();
            Class<?> student = classLoader2.loadClass("Student");
            System.out.println(student);


        }catch (Exception e){
            e.printStackTrace();
        }





    }

}

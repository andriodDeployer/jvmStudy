package classLoader;/**
 * Created by DELL on 2018/10/26.
 */


/**
 * user is
 **/


public class Main {


    public static void main(String[] agrs) throws ClassNotFoundException {

      //  Class.forName("classLoader.Person");
        Thread.currentThread().getContextClassLoader().loadClass("classLoader.Person");

    }
}

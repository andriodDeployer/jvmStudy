package exception.exceptioncache;/**
 * Created by DELL on 2018/8/22.
 */

import static exception.exceptioncache.ExceptionPool.getOrCreate;

/**
 * user is lwb
 **/


public class ExceptionTest {

    public static void main(String[] args){
        long start = System.nanoTime();
        throwException1();
        throwException2();
        long end = System.nanoTime();
        System.out.println("耗时 "+(end-start));
    }

    public static void throwException1(){
        System.out.println("throw-1");
        try {
            throw getOrCreate("exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void throwException2(){
        System.out.println("throw-2");
        try {
           SecondTest.test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

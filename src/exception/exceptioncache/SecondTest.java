package exception.exceptioncache;/**
 * Created by DELL on 2018/8/22.
 */

/**
 * user is lwb
 **/


public class SecondTest {


    public static void test1() throws Exception {
        try{
        test2();
        }catch (Exception e){
           // throw new Exception(e);
            try {
                throw e.fillInStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }


    public static void test2() throws Exception {
       test3();
    }


    public static void test3() throws Exception {
        System.out.println("secodeTest");
        throw ExceptionPool.getOrCreate("exception");
        //throw new Exception("exception");
    }




}

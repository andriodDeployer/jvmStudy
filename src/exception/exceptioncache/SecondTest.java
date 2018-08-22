package exception.exceptioncache;/**
 * Created by DELL on 2018/8/22.
 */

/**
 * user is lwb
 **/


public class SecondTest {


    public static void test1() throws Exception {

//        try{
//        test2();
//        }catch (Exception e){
//            try {
//                //throw e.fillInStackTrace();
//                throw e;
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        }

        test2();
    }


    public static void test2() throws Exception {
       test3();
    }


    public static void test3() throws Exception {
        System.out.println("secodeTest");
        Exception exception = ExceptionPool.getOrCreate("exception");

//        Exception exception = new Exception("exception"){
//            @Override
//            public synchronized Throwable fillInStackTrace() {
//                return this;//没有堆栈信息
//            }
//        };
       // exception.printStackTrace();

        throw exception;
    }
}

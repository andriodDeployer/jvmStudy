package exception.exceptioncache;/**
 * Created by DELL on 2018/8/22.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * user is lwb
 **/


public class ExceptionPool {

    private static Map<String,Exception> exceptionPool = new HashMap<>();

    public static Exception getOrCreate(String exceptionName){
        Exception oldException = exceptionPool.get(exceptionName);
        if(oldException != null){
            return oldException;
        }
        Exception exception = null;
        exception = new Exception(exceptionName);
//        exception = new Exception(exceptionName){
//            @Override
//            public synchronized Throwable fillInStackTrace() {
//                return this;
//            }
//        };
        exceptionPool.put(exceptionName,exception);
        return exception;
    }

}

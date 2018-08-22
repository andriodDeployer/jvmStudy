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
        Exception exception = new Exception(exceptionName);
        exceptionPool.put(exceptionName,exception);
        return exception;
    }

}

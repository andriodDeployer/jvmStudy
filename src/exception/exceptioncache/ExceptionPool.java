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
        exception = new Exception(exceptionName);//创建excepton对象时，会调用exception的fillstacktrack方法，会将逐个向上遍历栈帧填充信息。直到某个栈帧处理了逐个异常为止。这些操作都是在创建异常对象时完成的。
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

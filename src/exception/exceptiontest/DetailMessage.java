package exception.exceptiontest;/**
 * Created by DELL on 2018/11/15.
 */

/**
 * user is lwb
 **/


public class DetailMessage {


    public static void main(String[] args) {
        throwExceptionWithMessage();
    }

    private static void throwExceptionWithMessage(){
        throw new NullPointerException("hahaha");

    }


    private static void throwExceptionWithoutMessage(){
        throw new NullPointerException();

    }


}

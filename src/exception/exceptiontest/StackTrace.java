package exception.exceptiontest;/**
 * Created by DELL on 2018/11/15.
 */

/**
 * user is lwb
 **/


public class StackTrace {

    public static void main(String[] args) {
        g();
    }

    public static void f(){
        throw new NullPointerException("f");
    }

    public static void g(){
        try {
            f();
        }catch (NullPointerException e){
            e.printStackTrace();
            NullPointerException nullPointerException =  new NullPointerException("g");
            nullPointerException.printStackTrace();
            nullPointerException.setStackTrace(e.getStackTrace());
            System.out.println();
            nullPointerException.printStackTrace();
        }

    }



}

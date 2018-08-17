package trycatch.resources;/**
 * Created by DELL on 2018/8/14.
 */

import java.io.IOException;

/**
 * user is lwb
 **/


public class TryResouces {


    public static void main(String[] args){

        //BufferedReader br = new BufferedReader();
        TryResouces tryResouces = new TryResouces();
        //tryResouces.resourcesSuppressedException();
        //tryResouces.overrideException();
        tryResouces.test();
    }

    public void resourcesSuppressedException(){
        try(
                Connection1 connection1 = new Connection1();//try-catch-resource主要就是为了防止finally中抛出异常覆盖也无异常。
                Connection2 connection2 = new Connection2()){
            throw  new NullPointerException("logical ");//这个异常不会被覆盖

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void overrideException() {
        try{
            throw new IOException("try");
        }catch(Exception e){
            //执行到这里之后，说明上面的try的异常已经被处理过了，如果抛出的异常是catch。
            //throw new NullPointerException("catch");
        }finally {
            //System.out.println("finally");
            //如果finally抛出异常的话，那么只有finally的异常被抛出去，上面的try和catch的业务异常会被覆盖掉。
            // 通常finally中资源关闭的操作，抛出的异常都是关于资源关闭通用异常，对我们进行调试基本上没有什么启示。
            //throw new NullPointerException("finally");
        }
    }


    public void test(){

        try{
            throw new NullPointerException("dddd");
        }finally {
            System.out.println(" adsfasdfasd ");
        }
    }

}

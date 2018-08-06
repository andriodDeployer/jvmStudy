package oom.stack;/**
 * Created by Administrator on 2018-7-8 0008.
 */

/**
 * user is lwb
 **/

/**
 * 导致栈溢出的原因就是栈中栈帧过多，因为每个线程分配的栈大小是一定的，当这个线程调用的方法过多时，压入栈中的栈帧就很多
 * 每个栈帧的大小收到本地变量表的影响（方法中变量的数量），当线程中栈帧多导致该线程的栈空间不够用，出现栈溢出
 */

public class StackOverFlow {

    static class runA implements Runnable{
        int i =0;
        @Override
        public void run() {
            try {
                d();
            } catch (Throwable e) {
                System.out.println(i);
                //e.printStackTrace();
            }
        }

        void d() {
                int a=10;
                int b=20;
                StackOverFlow stackOverFlow = new StackOverFlow();
                int c = a+b;
            i++;
            d();
        }
    }


    public static void main(String[] args){
        new Thread(null, new runA(),"thread-1",1024*11).start();

    }

}

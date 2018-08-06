package oom.stack;/**
 * Created by Administrator on 2018-7-8 0008.
 */

/**
 * 导致虚拟机栈中内存溢出的一个原因就是，线程过多，OutOfMemory
 */

public class StackOutOfMemory {
    private void dontStop(){
        while (true){}
    }

    public void creatThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
        }
    }

    public static void main(String[] args){
        StackOutOfMemory outOfMemory = new StackOutOfMemory();
        outOfMemory.creatThread();

    }

}

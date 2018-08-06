package oom.heap;/**
 * Created by Administrator on 2018-7-8 0008.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class HeapOOM {

    /**
     * vmargs  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * 设置的内存大小不同，使用的gc算法不同会导致产生的错误日志是不同的。
     */
    static class OOMObject{
    }
    
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }
    
}

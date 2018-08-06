package oom.directect;
/**
 * Created by Administrator on 2018-7-15 0015.
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * user is lwb
 **/


public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true){
            unsafe.allocateMemory(_1MB);
        }

    }
}

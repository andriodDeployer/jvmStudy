package methodinvock;/**
 * Created by DELL on 2018/10/10.
 */

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;


/**
 * user is lwgb
 **/


public class DynamicInvokeTest {
    static class ClassA {
        public void println(String s){
            System.out.println(s);
        }
    }


    public static void main(String[] args) throws Throwable {
       // Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        //getMHByMT(obj).invokeExact("ddddd");
        new ClassA();
       // MethodHandle mhByReflect = getMHByReflect(object);
       // mhByReflect.invokeExact("ddd");


    }


    private static MethodHandles.Lookup lookup(){
        return MethodHandles.lookup();
    }

    private static MethodHandle getMHByMT(Object reveiver) throws Throwable{
        MethodType mt = MethodType.methodType(void.class,String.class);
        MethodHandles.Lookup lookup = lookup();
        //findVirtual，产生一个invokeVirtual指令。
        return lookup.findVirtual(reveiver.getClass(),"println",mt).bindTo(reveiver);
    }


    private static MethodHandle getMHByReflect(Object receiver) throws Throwable{
        Method m = ClassA.class.getDeclaredMethod("println",String.class);
        MethodHandles.Lookup lookup = lookup();
        return lookup.unreflect(m).bindTo(receiver);
    }

}

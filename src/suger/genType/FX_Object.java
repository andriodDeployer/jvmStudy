package suger.genType;/**
 * Created by DELL on 2018/10/13.
 */

/**
 * user is lwb
 **/


public class FX_Object<T> {
    //FX的字节码中：T被擦除，当作Object来对待。
    T t;
    public void add(T t){//descriptor: (Ljava/lang/Object;)V
        this.t = t;
    }

    public T get(){//descriptor:()Ljava/lang/Object;
        return t;
    }

    public void test(){
        FX_Object<String> fx = new FX_Object<String>();
        fx.add("1");//所以它的字节码为：Method add:(Ljava/lang/Object;)V，方法的描述符来自FX字节码的定义。
        fx.get();//所以它的字节码为： // Method get:()Ljava/lang/Object;方法的描述符来自FX字节码的定义。
    }

    public static void main(String[] args){

    }


}

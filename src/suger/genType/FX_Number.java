package suger.genType;/**
 * Created by DELL on 2018/10/13.
 */

/**
 * user is lwb
 **/


public class FX_Number<T extends Number> {
    //FX的字节码中：T被擦除，当作Number来对待。
    T t;
    public void add(T t){//descriptor: (Ljava/lang/Number;)V
        this.t = t;
    }

    public T get(){//descriptor:()Ljava/lang/Number;
        return t;
    }

    public void test(){
        FX_Number<Integer> fx = new FX_Number<Integer>();
        fx.add(1);//所以它的字节码为：Method add:(Ljava/lang/Number;)V，方法的描述符来自FX字节码的定义。
        fx.get();//所以它的字节码为： // Method get:()Ljava/lang/Number;方法的描述符来自FX字节码的定义。
    }

    public static void main(String[] args){

    }


}

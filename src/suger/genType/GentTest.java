package suger.genType;/**
 * Created by DELL on 2018/8/8.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/

//对于类泛型：在字节中，会生成一个类级别的Signature属性，属性值为<T:Ljava/lang/Object;>Ljava/lang/Object;
public class GentTest<T> {
    //对于泛型字段：会在字段上增加一个Signature属性，和字段的descriptor同一个级别，和descriptor的结构是相同的，都是显示字段的类型，如果在字段上使用了类定义的泛型的话，那么Signature的值为“TT”
    // 而descrptore为泛型的确定父类.
    private  T t;

    private Integer integer = 100;
    private String str = "SringIn";

    public T getObject(){

        List<T> list = new ArrayList<T>();
       // list.add(t);
        return list.get(0);
    }

    public void g(){
        List<T> list = new ArrayList<T>();
    }

    public static void main(String[] args){

    }

//局部变量的信息存放在
    public void testFinal(){
        final String a="aaa";
        String b = "";

    }
}

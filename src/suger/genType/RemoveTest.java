package suger.genType;/**
 * Created by DELL on 2018/8/8.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class RemoveTest {

    private Integer age;

    public static void main(String[] args){


        List<String> list = new ArrayList<String>();
        //形参是父类，实参可以是子类(多态)，形参是子类，实参是父类，过不了类型检查的。
        list.add(new String("1"));
        System.out.println(list.get(0));
        //list.get(0)//对应的字节码为：invokeInterface InterfaceMethod java/util/List.get:(I)Ljava/lang/Object; checkcast class java/lang/String 将类型转换成String，那么他是怎么知道是String的呢？
        //凡是方法体中带有泛型的变量，方法的code属性中都会由一个LocalVariableTypeTable的属性，
        // 这个LocalVariableTypeTable使用Signature属性记录了该变量的泛型类型。
    }

    public void test(){
        List<Integer> listtest = new ArrayList<Integer>();

        //listtest.add(new Integer("1"));
        //System.out.println(listtest.get(0));
    }

    public void test1(){
        List list = new ArrayList();
    }


    //关于泛型方法的的定义：在字节码中，
    // 为方法增加一个Signature属性(方法级别的与code属性同一级别)来记录方法的泛型。如果泛型方法中局部变量使用到这个泛型类型，那么会在方法的code属性的LocalVariableTypeTable中的Signature来记录泛型的类型，和方法级别属性Signature记录的一样
    // Signature属性和方法的描述符属性descriptor的结构是一样的，都是"(入参类型)返回值类型"，只是descriptor会将类型中的泛型擦出，类型为泛型的父类，默认情况下为Object。
    // 而Signature会将泛型的信息进行记录。
    //对于一个方法：<T> T getObject()。方法的descriptor为:"()Ljava/lang/Object"，而对于Signature为："<T:Ljava/lang/Object;>()TT"，由于是在方法上对泛型进行定义操作，所以会在原来结构上加上了泛型定义的内容。如果方法上定义的泛型，只是在类上进行使用的话，那么就是原来的结构
    //如果泛型的类型为<T extends Number>的话，Object怎变为Number了。
    public <T> T getObject(){
        List<T> a = new ArrayList<T>();
        return a.get(0);
    }


    public <T extends Number> T getnunber(){
        List<T> list = new ArrayList<T>();
        return list.get(0);
    }


}

package suger.enhanceFor;
/**
 * Created by DELL on 2018/8/9.
 */

import java.util.Arrays;
import java.util.List;

/**
 * user is lwb
 **/


public class EnhanceForTest {


    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4);

        //增强for循环是一个语法糖，在字节码中，实际上是转换成iterator进行实现的，这要求所有使用增强for循环的集合，必须实现iteratable接口，之所以要实习这个接口
        //是因为实现这个接口，就要实现iterator方法，因为编译时的解语法糖阶段，会将增强for循环，转换成普通的for循环，使用iterator的hasnext做为判定条件的。
        //是否可以不是实现iteratable接口，但是定义一个iterator方法呢？这是不行的，因为编译器判断一个类死否由iterator是根据是否实现了接口来判断的，不是去这个类中找这个方法，然后看有没有的。
        for(int str : list){
            System.out.println(str);
    }

        /**
         反编译后的结果为：
         for (Iterator localIterator = list.iterator(); localIterator.hasNext();)
         {
         int str = ((Integer)localIterator.next()).intValue();
         System.out.println(str);
         }





         */

    }

}

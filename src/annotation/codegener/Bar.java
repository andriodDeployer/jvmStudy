package annotation.codegener;/**
 * Created by DELL on 2018/10/18.
 */

import java.util.function.IntBinaryOperator;

/**
 * user is lwb
 **/


public class Bar {

    @Adapt(IntBinaryOperator.class)
    public static int add(int a,int b){
        return a + b;
    }
}

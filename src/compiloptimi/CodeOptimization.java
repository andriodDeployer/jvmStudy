package compiloptimi;/**
 * Created by DELL on 2018/8/9.
 */

/**
 * user is lwb
 **/


public class CodeOptimization {

    private Integer a = 10;

    public void getB(){
        Integer b = a;
        a=0;
        System.out.println(b);
    }


    public void getA(){
        System.out.println(a);
    }

    public static void main(String[] args){
        new CodeOptimization().getB();
    }




}

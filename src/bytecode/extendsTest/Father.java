package bytecode.extendsTest;/**
 * Created by DELL on 2018/10/19.
 */

/**
 * user is lwb
 **/


public class Father {

    private int age;
    public int money = 10;
    public int getAge(){
        System.out.println("father age");
        return age;
    }

    public Father(){
        System.out.println(this);
        this.age = 10;
    }





}

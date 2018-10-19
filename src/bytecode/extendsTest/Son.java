package bytecode.extendsTest;/**
 * Created by DELL on 2018/10/19.
 */

/**
 * user is lwb
 *
 *
 *
 *
 **/


public class Son extends Father {

    private int name;
    public int money = 100;


    public Son(int age){
       /* super();
        this();*/

    }

    public Son(){
        //Son son = new Son();


        System.out.println("==============");
        Father father = new Father();

    }

    public int getAge() {
        System.out.println("song getage");
        return 1;
    }

    public void test(){
        Father father = new Father();
        //System.out.println(super);
        super.getAge();
    }

    public static void main(String[] args){

        Son father = new Son();
        System.out.println("==============-----=------------------===========");
        father.getAge();


        father.testSuperThis();

    }


    public void testSuperThis(){
        System.out.println(super.getClass());
        System.out.println(this.getClass());
        Father fa = new Son();
        System.out.println(super.money);
        System.out.println(this.money);
        System.out.println(fa.money);
    }
}

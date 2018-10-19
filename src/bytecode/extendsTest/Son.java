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
        System.out.println("==============");
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
        Father father = new Son();
        Father father1 = new Son();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        father.getAge();
        father1.getAge();

        /*Son father = new Son();
        Son fat = new Son();
        System.out.println("==============-----=------------------===========");
        father.getAge();

        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        father.testSuperThis();*/

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

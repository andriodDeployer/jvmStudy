package classmeta;/**
 * Created by Administrator on 2018-7-22 0022.
 */

/**
 * user is lwb
 **/


public class TestClass {
    public int i = 9999;
    private static Person person1 = new Person();//静态变量初始化的过程和静态代码块是同一种方式。查看字节码可以知道
    private static Person person3;
    static {
        person3 = new Person();
    }

    public int test(){
        int statici = i;//getstatic
        Person person2 = person1;//getstatic
        int ab = 101;//bipush
        Person person = new Person();//new
        System.out.println(ab);
        return ab;
    }

    public static void statictest(){
        int statica = 10;
    }

    public void invokstatic() throws Exception{
        int invok = 100;
        statictest();//invokestatic
        privatetest();//invokespacial
    }

    public int trycatchtest(){
        int x;
        try{
            this.i=10;
            x = 10;
            return x;
        } catch (Exception e){
            x =2;
            return x;
        }finally {
            x=3;
        }
    }

    private void privatetest(){
        int iprivate = 10;
        System.out.println(iprivate);
    }
}

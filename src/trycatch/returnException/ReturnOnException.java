package trycatch.returnException;/**
 * Created by DELL on 2018/8/14.
 */

/**
 * user is lwb
 **/


public class ReturnOnException {


    public static void main(String[] args){
        ReturnOnException returnOnException = new ReturnOnException();
        Person person = returnOnException.testReturnRef();
        System.out.println(person.getName());

        System.out.println(returnOnException.testReturnBasic());




    }


//在catch中有return的时：如果在finally中有显示的return的话，则返回值时finally中return后的值。
//因为返回值时return后面变量/引用指向的值/对象，如果在finally中对引用重新赋值时不会影响真正返回值的，但是如果对引用执行对象中对象的
//的字段进行修改的话，会影响到返回值。
    public Person testReturnRef(){
        Person person = null;
        try{
            person = new Person();
            person.setName("try");
            return person;
        }catch(Exception e){

        }finally {
            person.setName("finally");
            person = new Person();
            person.setName("peron1_finally");
        }
        return person;
    }




    public int testReturnBasic(){
        int i = 0;
        try{
            i=10;
            return i;
        }catch(Exception e){

        }finally {
            i = 20;
            return i;
        }
    }

    public int testReturnBasicWithException(){

        //局部变量有4个：this，
        int i = 0;
        try{
            i=10;
            return i;
        }catch(Exception e){

        }finally {
            i = 20;
            return i;
        }
    }

    public int testReturnBoxingWithException(){

        //局部变量有4个：this，
        Integer i = 0;
        try{
            i=10;
            return i;
        }catch(Exception e){

        }finally {
            i = 20; //integer的值value是final不可变的，进行赋值，实质上进行了自动装箱操作也就是：i = Integer.valueOf(20),valueOf重新创建了一个Integer对象。
            return i;
        }
    }

}

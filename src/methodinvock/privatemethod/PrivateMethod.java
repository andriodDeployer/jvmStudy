package methodinvock.privatemethod;/**
 * Created by DELL on 2018/10/25.
 */

/**
 * user is lwb
 **/


public class PrivateMethod {

     void sup(){
        System.out.println("super");
    }


    public static void main(String[] args){
        PrivateMethod method = new Sub();
        method.sup();
    }

}

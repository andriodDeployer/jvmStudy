package bytecode;/**
 * Created by DELL on 2018/10/15.
 */

/**
 * user is lwb
 **/


public class IF_ELSE {
    public static void main(String[] args){
        int i=10;
        if(i == 10){
            System.out.println("i equal 10");
        }else{
            System.out.println("i not equal 10");
        }
    }

    /**
     * main方法生成字节码如下：
     *
     *
     *  0: bipush        10
     2: istore_1
     3: iload_1
     4: bipush        10
     6: if_icmpne     20 //取出栈顶的两个操作数，如果两个操作数相同的话，接着执行下面的字节码，如果不相同的话，跳转到20去执行。
     9: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     12: ldc           #3                  // String i equal 10
     14: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     17: goto          28
     20: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     23: ldc           #5                  // String i not equal 10
     25: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     28: return
     */

    public static int foo(boolean f, int in) {
        int v;
        if (f) {
            v = in;
        } else {
            v = (int) Math.sin(in);
        }
        if (v == in) {
            return 0;
        } else {
            return (int) Math.cos(v);
        }
    }



}

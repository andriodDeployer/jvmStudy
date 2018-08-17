package oom.memoryleak;
/**
 * Created by DELL on 2018/8/9.
 */

public class Hello {
    private String mName="37785612";
    private String fildName = "dddd";
    public String ffff = "dddd";

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    class Demo{
        public void show(){
        }
    }
    public void showDemo(final String s){
        String hh = "ddddd";

        new Demo(){
            public void show(){
                System.out.println("s="+s);
                System.out.println("name="+mName);
               // System.out.println(hh);
                System.out.println(ffff);
                System.out.println(fildName);
            }
        }
        .show();
    }


    public static void main(String[] args){
        System.out.println();
    }
}

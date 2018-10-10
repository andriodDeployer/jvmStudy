package methodinvock;/**
 * Created by DELL on 2018/10/10.
 */

/**
 * user is lwb
 **/


public class MethodInvoke {


    static class QQ{}

    static class TQQ extends QQ{}
    static class _360{}


    public static class Father {
        public void choice(QQ arg){
            System.out.println("father choice qq");
        }


        public void choice(_360 arg){
            System.out.println("father choice 360");
        }
    }


    public static class Son extends Father{
        public void choice(QQ arg){
            System.out.println("son choice qq");
        }


        public void choice(_360 arg){
            System.out.println("son choice 360");
        }
    }

    public static void main(String[] args){
        Father father = new Father();
      //  Father son = new Son();
        father.choice(new _360());
     //   TQQ tqq = new TQQ();
     //   son.choice(tqq);
    }

}

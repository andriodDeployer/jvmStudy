package methodinvock;/**
 * Created by DELL on 2018/10/10.
 */

/**
 * user is lwb
 * 使用方法内联：java InlineCacheTest
 * 不使用方法内联：java -XX:CompileCommand=dontinline,*.goOut InlineCacheTest
 *
 *
 *
 **/


public abstract class InlineCacheTest {
    abstract void goOut();

    static class Chinese extends InlineCacheTest{

        @Override
        void goOut() {

        }
    }

    static class Forigin extends InlineCacheTest{

        @Override
        void goOut() {

        }
    }


    public static void main(String[] arg){
        InlineCacheTest chinese = new Chinese();
        InlineCacheTest forigin = new Forigin();
        long current = System.currentTimeMillis();
        int count = 2_000_000_000;
        for(int i=1; i<=count; i++){
            if(i % 1_000_000_000 == 0){
                long temp = System.currentTimeMillis();
                System.out.println(temp-current);
                current = System.currentTimeMillis();
            }
            InlineCacheTest t = (i < 1_000_000_000 ) ? chinese : forigin;
            t.goOut();
        }
    }
}

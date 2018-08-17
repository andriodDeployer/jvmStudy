package suger.enhanceFor;/**
 * Created by DELL on 2018/8/9.
 */

import java.util.Iterator;

/**
 * user is lwb
 **/


public class MyCollection implements Iterable<Integer>{

    private  int[] a ;

    public MyCollection(){
        a = new int[]{1,2,3,4,5,6,7,8,9,10};
    }

    public Iterator<Integer> iterator(){
        return new MyIterator();
    }



    class MyIterator implements Iterator<Integer>{
        int index = 0;

        @Override
        public boolean hasNext() {
            return index<a.length;
        }

        @Override
        public Integer next() {
            return a[index++];
        }
    }


    public static void main(String[] args){
        MyCollection collection = new MyCollection();

        for(Integer integer : collection){
            System.out.println(integer);

        }
    }


}

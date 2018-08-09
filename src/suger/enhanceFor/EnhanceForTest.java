package suger.enhanceFor;
/**
 * Created by DELL on 2018/8/9.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class EnhanceForTest {


    public static void main(String[] args){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for(String str : list){
            System.out.println(str);
        }


    }

}

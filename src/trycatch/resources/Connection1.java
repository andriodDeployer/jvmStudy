package trycatch.resources;/**
 * Created by DELL on 2018/8/14.
 */

import java.io.IOException;

/**
 * user is
 **/


public class Connection1 implements AutoCloseable {

    //close会在finally中执行
    @Override
    public void close() throws IOException {
        throw new NullPointerException("Connection1 close exception");
    }
}

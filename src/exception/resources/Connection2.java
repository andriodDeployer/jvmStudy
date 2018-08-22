package exception.resources;/**
 * Created by DELL on 2018/8/14.
 */

import java.io.IOException;

/**
 * user is
 **/


public class Connection2 implements AutoCloseable{

    @Override
    public void close() throws IOException {
        throw new NullPointerException("Connection2 close exception");
    }
}

package onionsss.it.smartbeijing.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 作者：张琦 on 2016/5/31 12:37
 */
public class Closed {
    public static void close(Closeable close){
        if(close != null){
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

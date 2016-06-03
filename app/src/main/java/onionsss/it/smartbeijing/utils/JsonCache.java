package onionsss.it.smartbeijing.utils;

import android.app.Activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：张琦 on 2016/5/31 12:32
 */
public class JsonCache {
    public static void CacheJson(String urlFileName, String json, Activity activity){
        urlFileName = urlFileName.replace("/", "_");
        BufferedOutputStream bos = null;
        try {
            File file = new File(activity.getFilesDir(),urlFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(json.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            Closed.close(bos);
        }
    }

    public static String readCache(String fileName,Activity activity){
        String cache = null;
        fileName = fileName.replace("/", "_");
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(new File(activity.getFilesDir(),fileName));
            baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024*8];
            int len = 0;
            while((len = fis.read(b))!=-1){
                baos.write(b,0,len);
            }
            cache = baos.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Closed.close(fis);
        }
        return cache;
    }
}

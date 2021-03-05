package com.yidiantong.util;

import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyUtils {
    public static boolean fileCopy(String oldFilePath, String newFilePath) throws IOException {
        //如果原文件不存在
        if (fileExists(oldFilePath) == false) {
            Log.i("111111111","如果原文件不存在");
            return false;
        }
        //获得原文件流
        FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
        byte[] data = new byte[1024];
        File file1 = new File(newFilePath);
        //创建目录
        if(!file1.exists()){
            file1.mkdirs();
            Log.i("111111111111111","创建成功");
        }else {
            Log.i("11111111","开始写入");
            //输出流
            FileOutputStream outputStream = new FileOutputStream(file1);
            int a=0;
            //开始处理流
            while (inputStream.read(data) != -1) {
                outputStream.write(data);
                a++;
                Log.i("11111111",a+"");
            }
            inputStream.close();
            outputStream.close();

            Log.i("11111111111111111111",file1.getPath());
        }
       // Ed.readExcel(file1);
        return true;
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


}

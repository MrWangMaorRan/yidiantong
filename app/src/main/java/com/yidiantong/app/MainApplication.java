package com.yidiantong.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.yidiantong.api.ApiHttpsApiClient;
import com.yidiantong.util.log.CrashHandler;
import com.yidiantong.util.log.LogVariateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainApplication extends Application {
    public static Context con;

    @Override
    public void onCreate() {
        super.onCreate();
        con = this;
        MultiDex.install(this);

        // alibabahttp.sdk 初始化
        ApiHttpsApiClient.init();
//        ApiWebSocketApiClient.init();

        // 错误日志收集
        CrashHandler.getInstance().init(this);
        LogVariateUtils.getInstance().isShowLog(true);
        LogVariateUtils.getInstance().isWriteLog(true);

        // 拨号linPhone初始化
//        MyLinPhoneManager.getInstance().init(this);



        //CrashReport.initCrashReport(getApplicationContext(), "4a50d739b9", true);
      //  CrashReport.initCrashReport(getApplicationContext());
        Bugly.init(getApplicationContext(), "4a50d739b9", false);
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
       CrashReport.initCrashReport(context, "4a50d739b9", true, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
      //  CrashReport.initCrashReport(context, strategy);
        //CrashReport.testJavaCrash();
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}

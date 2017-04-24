package com.yzyfdf.ceshi;

import android.os.Process;

import com.yzyfdf.newsframework.util.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by SJJ .
 * 描述 ${TODO}
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        File dir = new File(Constant.mContext.getApplicationContext().getFilesDir().getAbsolutePath());
        File errorFile = new File(dir,"error.log");
        try {
            PrintStream printStream = new PrintStream(errorFile);
            e.printStackTrace(printStream);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Process.killProcess(Process.myPid());
    }
}

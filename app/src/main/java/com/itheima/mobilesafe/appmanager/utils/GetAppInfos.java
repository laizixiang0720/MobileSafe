package com.itheima.mobilesafe.appmanager.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.itheima.mobilesafe.appmanager.entity.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAppInfos {
    /**
     * 获取手机中所有应用程序
     */
    public static List<AppInfo> getAppInfos(Context context){
        PackageManager pm = context.getPackageManager();//获取应用程序的包管理器
        List<PackageInfo> packInfos = pm.getInstalledPackages(0);//获取所有应用
        List<AppInfo> appinfos = new ArrayList<AppInfo>();
        for(PackageInfo packInfo:packInfos){
            AppInfo appinfo = new AppInfo();
            appinfo.packageName = packInfo.packageName;
            appinfo.icon = packInfo.applicationInfo.loadIcon(pm);
            //获取应用名称
            appinfo.appName = packInfo.applicationInfo.loadLabel(pm).toString();
            //应用程序的apk文件路径
            String apkpath = packInfo.applicationInfo.sourceDir;
            appinfo.apkPath = apkpath;
            File file = new File(apkpath);
            appinfo.appSize = file.length();
            //应用程序安装的位置
            int flags = packInfo.applicationInfo.flags; //二进制映射
            //手机内存
            appinfo.isInRoom = (ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags) == 0;//外部存储
            //用户应用
            appinfo.isUserApp = (ApplicationInfo.FLAG_SYSTEM & flags) == 0;//系统应用
            appinfos.add(appinfo);
        }
        return appinfos;
    }
}

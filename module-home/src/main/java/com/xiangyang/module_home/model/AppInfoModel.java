package com.xiangyang.module_home.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xiangyang.library_base.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class AppInfoModel {
    private static final String TAG = "AppInfoModel";
    private Context context = null;
    private final LauncherApps launcherApps;
    private List<AppInfo> installedAppList = new ArrayList<>();

    public AppInfoModel(Context context) {
        this.context = context;
        launcherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
        Log.d(TAG, "AppInfoModel: ");
        installedAppList = queryInstallApp();

    }
    public List<AppInfo> getInstalledAppList() {
        return installedAppList;
    }

    private List<AppInfo> queryInstallApp() {
        Log.d(TAG, "queryInstallApp: ");
        List<AppInfo> infoList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            String activity = resolveInfo.activityInfo.name;
            Drawable icon = resolveInfo.loadIcon(packageManager);
            String title = resolveInfo.loadLabel(packageManager).toString();
            String packageName = resolveInfo.activityInfo.packageName;
//            Log.d(TAG, "title: " + title);
            Log.d(TAG, "packageName: " + packageName);
            Log.d(TAG, "icon: " + icon);

//            LogUtils.d("title = " + title + ",packageName = " + packageName);
            AppInfo appInfo = new AppInfo();
            appInfo.setActivity(activity);
            appInfo.setAppIcon(icon);
            appInfo.setAppTitle(title);
            appInfo.setPackageName(packageName);
            infoList.add(appInfo);
        }
        return infoList;
    }
}

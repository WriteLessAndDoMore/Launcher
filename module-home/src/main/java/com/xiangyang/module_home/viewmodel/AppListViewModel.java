package com.xiangyang.module_home.viewmodel;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.xiangyang.library_base.threadpoolexecutor.thread.EasyThread;
import com.xiangyang.library_base.utils.LogUtils;
import com.xiangyang.module_home.BR;
import com.xiangyang.module_home.R;
import com.xiangyang.module_home.model.AppInfo;
import com.xiangyang.module_home.model.AppInfoModel;

import java.lang.reflect.Method;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class AppListViewModel extends AndroidViewModel implements LifecycleObserver{
    private static final String TAG = "AppListViewModel";
    private AppInfoModel model;
    public ObservableList<AppItemViewModel> defaultAppList = new ObservableArrayList<>();
    public ItemBinding<AppItemViewModel> itemBinding = ItemBinding.of(BR.itemViewModel, R.layout.home_app_item);
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        queryInstallApp();
    }

    public AppListViewModel(@NonNull Application application, AppInfoModel model) {
        super(application);
        this.model = model;
        queryInstallApp();

    }
    public void queryInstallApp() {
        new EasyThread<AppInfo>() {

            @Override
            protected Object doBackground() {
                return model.getInstalledAppList();
            }

            @Override
            protected void onExecuteResult(Object o) {
                List<AppInfo> appInfoList = (List<AppInfo>) o;
                for (AppInfo appInfo : appInfoList) {
                    Log.d(TAG, "onExecuteResult: " + appInfo);
                    defaultAppList.add(new AppItemViewModel(AppListViewModel.this, appInfo));
                }
            }
        }.start();
    }

    public void startAppActivity(String packageName) {
        Context context = getApplication();
        LogUtils.d("packageName = " + packageName);
        PackageInfo packageInfo = getPackageInfo(packageName, context);
        if (packageInfo != null) {
            Log.d(TAG, "startAppActivity: " + packageName);
            Intent intent = getIntent(packageInfo, context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            setStartUpProperty(packageName);
        } else {
            Log.d(TAG, "startAppActivity: null");
        }

    }
    public static PackageInfo getPackageInfo(String packageName, Context context) {
        if (context == null) {
            return null;
        }
        PackageInfo info = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            info = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LogUtils.d(e.getMessage().toString());
        }
        return info;
    }
    public static Intent getIntent(PackageInfo packageInfo, Context context) {
        if (packageInfo == null || context == null) {
            return null;
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.setPackage(packageInfo.packageName);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveInfos.iterator().next();
        if (resolveinfo != null) {
            String packageName = resolveinfo.activityInfo.packageName;
            String className = resolveinfo.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            return intent;
        } else {
            return null;
        }
    }
    private static final String SYS_STARTUP = "sys.startup";
    public static void setStartUpProperty(String value) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class, String.class);
            set.invoke(c, SYS_STARTUP, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.xiangyang.module_home.viewmodel;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;

import com.xiangyang.module_home.model.AppInfo;

public class AppItemViewModel {
    public AppInfo appInfo = new AppInfo();
    public ObservableField<String> appTitle = new ObservableField<>();
    public ObservableField<Drawable> appIcon = new ObservableField<Drawable>();
    public ObservableField<String> packageName = new ObservableField<String>();
    public AppItemViewModel(AppListViewModel appListViewModel, AppInfo appInfo) {
        this.appInfo = appInfo;
        this.appTitle.set(appInfo.getAppTitle());
        this.appIcon.set(appInfo.getAppIcon());
        this.packageName.set(appInfo.getPackageName());
    }
}

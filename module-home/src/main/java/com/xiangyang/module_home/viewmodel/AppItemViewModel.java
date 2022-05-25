package com.xiangyang.module_home.viewmodel;

import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xiangyang.library_base.binding.command.BindingCommand;
import com.xiangyang.library_base.utils.LogUtils;
import com.xiangyang.module_home.databinding.HomeAppItemBinding;
import com.xiangyang.module_home.model.AppInfo;

public class AppItemViewModel {
    private static final String TAG = "AppItemViewModel";
    private AppListViewModel viewModel;
    public AppInfo appInfo = new AppInfo();
    public ObservableField<String> appTitle = new ObservableField<>();
    public ObservableField<Drawable> appIcon = new ObservableField<Drawable>();
    public ObservableField<String> packageName = new ObservableField<String>();
    public AppItemViewModel(AppListViewModel viewModel, AppInfo appInfo) {

        this.viewModel = viewModel;
        this.appInfo = appInfo;
        this.appTitle.set(appInfo.getAppTitle());
        this.appIcon.set(appInfo.getAppIcon());
        this.packageName.set(appInfo.getPackageName());
    }
    public void onClick(){
        Log.d(TAG, "start: ");
    }
    public BindingCommand itemClick = new BindingCommand(() -> {

        LogUtils.d();

        viewModel.startAppActivity(packageName.get());

    });
}

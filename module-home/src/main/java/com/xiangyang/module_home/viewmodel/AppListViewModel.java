package com.xiangyang.module_home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;

import com.xiangyang.library_base.threadpoolexecutor.thread.EasyThread;
import com.xiangyang.module_home.model.AppInfo;
import com.xiangyang.module_home.model.AppInfoModel;

import java.util.List;

public class AppListViewModel extends AndroidViewModel {
    private AppInfoModel model;
    public ObservableList<AppItemViewModel> defaultAppList = new ObservableArrayList<>();
//    public ItemBinding<AppItemViewModel> itemBinding = ItemBinding.of(BR.itemViewModel, R.layout.home_app_item);
    public AppListViewModel(@NonNull Application application, AppInfoModel model) {
        super(application);
        this.model = model;
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
                    defaultAppList.add(new AppItemViewModel(AppListViewModel.this, appInfo));
                }
            }
        }.start();
    }
}

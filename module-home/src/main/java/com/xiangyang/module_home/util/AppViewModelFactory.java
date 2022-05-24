package com.xiangyang.module_home.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.xiangyang.module_home.model.AppInfoModel;
import com.xiangyang.module_home.viewmodel.AppListViewModel;
//import com.autoai.project.home.viewmodel.AppListViewModel;
//import com.autoai.project.home.viewmodel.HomeViewModel;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    public static AppViewModelFactory instance;
    private final Application mApplication;
    private final AppInfoModel model;

    public static AppViewModelFactory getInstance(Application application) {
        if (instance == null) {
            synchronized (AppViewModelFactory.class) {
                if (instance == null) {
                    instance = new AppViewModelFactory(application, new AppInfoModel(application));
                }
            }
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    private AppViewModelFactory(Application application, AppInfoModel model) {
        this.mApplication = application;
        this.model = model;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AppListViewModel.class)) {
            return (T) new AppListViewModel(mApplication, model);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}

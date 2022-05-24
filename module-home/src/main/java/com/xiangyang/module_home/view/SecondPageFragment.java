package com.xiangyang.module_home.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangyang.module_home.R;
import com.xiangyang.module_home.databinding.FragmentSecondPageBinding;
import com.xiangyang.module_home.databinding.HomeSoundCardBinding;
import com.xiangyang.module_home.util.AppViewModelFactory;
import com.xiangyang.module_home.viewmodel.AppListViewModel;

public class SecondPageFragment extends Fragment {

    private FragmentSecondPageBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate
                (inflater, R.layout.fragment_second_page, container, false);
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        AppListViewModel appListViewModel = new ViewModelProvider
                (this, factory).get(AppListViewModel.class);



        return mBinding.getRoot();
    }

    public void themeUpNotice() {

    }
}
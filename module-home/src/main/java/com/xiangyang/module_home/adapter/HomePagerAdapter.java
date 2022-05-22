package com.xiangyang.module_home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xiangyang.module_home.view.FirstPageFragment;
import com.xiangyang.module_home.view.SecondPageFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {
   private List<Fragment> list = new ArrayList<>();
   public HomePagerAdapter(@NonNull FragmentManager fm) {
      super(fm);
      list.add(new FirstPageFragment());
      list.add(new SecondPageFragment());
   }

   @NonNull
   @Override
   public Fragment getItem(int position) {
      return list.get(position);
   }

   @Override
   public int getCount() {
      return list != null ? list.size() : 0;
   }

   public void onThemeNotice() {
      for (Fragment fragment : list) {
         if (fragment instanceof FirstPageFragment) {
            ((FirstPageFragment) fragment).themeUpNotice();
         } else if (fragment instanceof SecondPageFragment) {
            ((SecondPageFragment) fragment).themeUpNotice();
         }
      }
   }
}

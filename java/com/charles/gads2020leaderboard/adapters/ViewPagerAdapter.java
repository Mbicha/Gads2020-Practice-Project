package com.charles.gads2020leaderboard.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.charles.gads2020leaderboard.fragments.LearningLeadersFragment;
import com.charles.gads2020leaderboard.fragments.SkillIQLeadersFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private int tabIndex;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, int mTabs) {
        super(fm, behavior);
        this.tabIndex = mTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningLeadersFragment();
            case 1:
                return new SkillIQLeadersFragment();
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return tabIndex;
    }
}

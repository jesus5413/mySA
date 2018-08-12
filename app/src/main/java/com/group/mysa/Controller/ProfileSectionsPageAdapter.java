package com.group.mysa.Controller;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProfileSectionsPageAdapter extends FragmentPagerAdapter{

    public ProfileSectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                AttendingProfileFragment attendingProfileFragment = new AttendingProfileFragment();
                return attendingProfileFragment;


            case 1:
                AttendedProfileFragment attendedProfileFragment = new AttendedProfileFragment();
                return attendedProfileFragment;

            case 2:
                LikedProfileFragment likedProfileFragment = new LikedProfileFragment();
                return likedProfileFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Future Events";
            case 1:
                return "Past Events";
            case 2:
                return "Likes";
            default:
                return null;
        }
    }
}

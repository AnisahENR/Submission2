package com.example.submission2.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.submission2.Fragment.ListFollower;
import com.example.submission2.Fragment.ListFollowing;
import com.example.submission2.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public static final String USERNAME = "username";
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    String username;
    Bundle bundle;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String username) {
        super(fm);
        mContext = context;
        this.username = username;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
//        final FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                bundle = new Bundle();
                bundle.putString("username", username); // menginputkan parameter
                ListFollower listFollower = new ListFollower();
                listFollower.setArguments(bundle);// mengirim parameter
                return listFollower;

            case 1:
                bundle = new Bundle();
                bundle.putString("username", username); // menginputkan parameter
                ListFollowing listFollowing = new ListFollowing();
                listFollowing.setArguments(bundle);// mengirim parameter
                return listFollowing;
//                return new ListFollowing();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
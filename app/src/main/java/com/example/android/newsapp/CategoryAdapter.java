package com.example.android.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by trikh on 27-10-2016.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Books();
        } else if (position == 1) {
            return new Business();
        } else if (position == 2) {
            return new Music();
        } else if (position == 3) {
            return new Politics();
        } else if (position == 4) {
            return new Sports();
        } else {
            return new Technology();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}

package zac.vince.jl.patou.popitprof;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 3/16/18.
 */

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> surveys;

    public DemoCollectionPagerAdapter(FragmentManager fm, ArrayList<String> surveys) {
        super(fm);
        this.surveys = surveys;
    }

    @Override
    public Fragment getItem(int i) {
        ArrayList<String> fragmentSurveys = new ArrayList<>(this.surveys.subList(i*6, i*6+6));

        Fragment fragment = new SurveyListingFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(SurveyListingFragment.ARG_SURVEYS, fragmentSurveys);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        //TODO
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}

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

public class SurveysListPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> surveys;

    public SurveysListPagerAdapter(FragmentManager fm, ArrayList<String> surveys) {
        super(fm);
        this.surveys = surveys;
    }

    private ArrayList<String> getSixSurveys(int fragIndex) {
        ArrayList<String> result = new ArrayList<>();
        if (fragIndex*6 < this.surveys.size()) {
            for (int i = fragIndex * 6; i < this.surveys.size() && i < fragIndex * 6 + 6; i++) {
                result.add(this.surveys.get(i));
            }
        }
        return result;
    }

    @Override
    public Fragment getItem(int i) {

        ArrayList<String> fragmentSurveys = getSixSurveys(i);

        Fragment fragment = new SurveyListingFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(SurveyListingFragment.ARG_SURVEYS, fragmentSurveys);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        //TODO : global
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}

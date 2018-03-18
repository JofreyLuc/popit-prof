package zac.vince.jl.patou.popitprof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zac.vince.jl.patou.popitprof.CompatInterfaces.DashboardLauncher;

/**
 * Created by patrick on 3/16/18.
 */

public class SurveyListingFragment extends Fragment {

    public static final String ARG_SURVEYS = "surveys";
    private List<String> surveys;
    private DashboardLauncher dashboardLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            dashboardLauncher = (DashboardLauncher) getActivity();
        } catch(Exception e) {
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG);
            System.exit(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_surveylisting, container, false);
        Bundle args = getArguments();

        surveys = args.getStringArrayList(ARG_SURVEYS);

        GridLayout grid = (GridLayout)rootView.findViewById(R.id.grid);

        for (final String s : surveys) {
            if (s.equals("-1")) break;
            Button b = new Button(getContext());
            b.setText(s);
            b.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dashboardLauncher.launchDashboard(s);
                }
            });
            grid.addView(b);
        }

        return rootView;
    }

}

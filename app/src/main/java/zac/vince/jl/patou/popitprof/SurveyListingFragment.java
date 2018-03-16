package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by patrick on 3/16/18.
 */

public class SurveyListingFragment extends Fragment {

    public static final String ARG_SURVEYS = "surveyS";
    private List<String> surveys;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_surveylisting, container, false);
        Bundle args = getArguments();

        surveys = args.getStringArrayList(ARG_SURVEYS);

        GridLayout grid = (GridLayout)rootView.findViewById(R.id.grid);

        //TODO:propre
        for (final String s : surveys) {
            if (s.equals("-1")) break;
            Button b = new Button(getContext());
            b.setText(s);
            b.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), DashboardActivity.class);
                    i.putExtra(DashboardActivity.EXTRA_SURVEYNAME, s);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
                }
            });
            grid.addView(b);
        }

        return rootView;
    }

}

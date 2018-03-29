package zac.vince.jl.patou.popitprof;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zac.vince.jl.patou.popitprof.compatInterfaces.DashboardLauncher;

/**
 * Created by patrick on 3/16/18.
 */

public class SurveyListingFragment extends Fragment {

    public static final String ARG_SURVEYS = "surveys";
    private List<String> surveys;
    private DashboardLauncher dashboardLauncher;

    private PointF start = new PointF();
    private boolean circularMenu = false;

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

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN :
                        Log.i("AZER", "touch down fragment");
                        start.set(event.getX(), event.getY());
                        break;

                    case MotionEvent.ACTION_UP :
                        Log.i("AZER", "touch up fragment");
                        if (circularMenu) {
                            Log.i("AZER", "check circular menu");
                            if (start.y - event.getY() > 150) {
                                dashboardLauncher.launchSelectedCharts();
                            } else if (start.y - event.getY() < -150) {
                                dashboardLauncher.launchSelectedRemove();
                            }
                            dashboardLauncher.hideCircularMenu();
                        }
                        circularMenu = false;

                        break;


                }
                return true;
            }
        });


        Bundle args = getArguments();

        surveys = args.getStringArrayList(ARG_SURVEYS);

        GridLayout grid = (GridLayout)rootView.findViewById(R.id.grid);

        for (final String s : surveys) {
            if (s.equals("-1")) {
                TextView v = new TextView(getContext());
                v.setText("•");
                grid.addView(v);
            } else {
                Button b = new Button(getContext());
                b.setText(s);
                b.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dashboardLauncher.launchDashboard(s);
                    }
                });
                b.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (start != null) {
                            dashboardLauncher.popCircularMenu(s, start.x, start.y);
                        } else {
                            dashboardLauncher.popCircularMenu(s, 0, 0);
                        }
                        circularMenu = true;
                        return true;
                    }
                });
                grid.addView(b);
            }
        }

        return rootView;
    }

}

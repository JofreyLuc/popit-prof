package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import zac.vince.jl.patou.popitprof.compatInterfaces.DashboardLauncher;
import zac.vince.jl.patou.popitprof.persistence.DataStorage;

public class Accueil extends AppCompatActivity implements DashboardLauncher {

    private SurveysListPagerAdapter mSurveysListPagerAdapter;
    private ViewPager mViewPager;
    private boolean modeMenu = false;

    ImageView menu = null;

    private PointF start = new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        mSurveysListPagerAdapter = new SurveysListPagerAdapter(getSupportFragmentManager(), DataStorage.getInstance().getSurveys().getSurveysName());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSurveysListPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void launchDashboard(String surveyName) {
        Intent i = new Intent(this, DashboardActivity.class);
        i.putExtra(DashboardActivity.EXTRA_SURVEYNAME, surveyName);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN :
                start.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_POINTER_DOWN :
                break;

            case MotionEvent.ACTION_MOVE :
                break;

            case MotionEvent.ACTION_UP :
                if(modeMenu){

                }
                ((ViewManager)menu.getParent()).removeView(menu);
                this.modeMenu = false;
                break;

            case MotionEvent.ACTION_POINTER_UP :
                break;

        }
        return true;
    }

    @Override
    public void popCircularMenu(String surveyName, float x, float y) {

        RelativeLayout layout = findViewById(R.id.menuLayout);

        menu = new ImageView(getApplicationContext());
        menu.setBackgroundResource(R.drawable.menu_sujets_blanc);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,200);
        params.leftMargin = (int)x;
        params.topMargin = (int)y;
        layout.addView(menu, params);

        this.modeMenu = true;
    }
}

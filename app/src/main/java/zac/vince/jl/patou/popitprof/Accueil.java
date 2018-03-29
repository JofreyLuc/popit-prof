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
import android.widget.ImageView;

import android.widget.Toast;

import android.widget.RelativeLayout;

import zac.vince.jl.patou.popitprof.compatInterfaces.DashboardLauncher;
import zac.vince.jl.patou.popitprof.persistence.DataStorage;

public class Accueil extends AppCompatActivity implements DashboardLauncher {

    private SurveysListPagerAdapter mSurveysListPagerAdapter;
    private ViewPager mViewPager;
    private boolean modeMenu = false;
    private String surveyName;

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
    public void popCircularMenu(String surveyName, float x, float y) {

        RelativeLayout layout = findViewById(R.id.menuLayout);
        menu = new ImageView(getApplicationContext());
        menu.setBackgroundResource(R.drawable.menu_sujets_blanc);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(menu.getWidth(),menu.getHeight());
        params.leftMargin = 80;
        params.topMargin = 90;
        layout.addView(menu, params);

        this.modeMenu = true;
    }

    @Override
    public void hideCircularMenu() {
        ConstraintLayout layout = findViewById(R.id.accueilLayout);
        layout.removeView(menu);
    }

    @Override
    public void launchSelectedCharts() {
        Intent i = new Intent(this, ChartsActivity.class);
        i.putExtra(DashboardActivity.EXTRA_SURVEYNAME, surveyName);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
    }

    @Override
    public void launchSelectedRemove() {
        DataStorage.getInstance().removeSurvey(surveyName);
        Toast.makeText(this, "Questionnaire " + surveyName + " \"supprimé\"", Toast.LENGTH_LONG).show();
    }
}

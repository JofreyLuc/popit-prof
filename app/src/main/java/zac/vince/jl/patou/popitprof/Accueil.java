package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import zac.vince.jl.patou.popitprof.compatInterfaces.DashboardLauncher;
import zac.vince.jl.patou.popitprof.persistence.DataStorage;

public class Accueil extends AppCompatActivity implements DashboardLauncher {

    private SurveysListPagerAdapter mSurveysListPagerAdapter;
    private ViewPager mViewPager;

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
    public void popCircularMenu(String surveyName) {
        ImageView menu = new ImageView(getApplicationContext());
        Bitmap image = BitmapFactory.decodeFile();
    }
}

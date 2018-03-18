package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import zac.vince.jl.patou.popitprof.CompatInterfaces.DashboardLauncher;
import zac.vince.jl.patou.popitprof.model.Survey;
import zac.vince.jl.patou.popitprof.persistence.DataStorage;

public class Accueil extends AppCompatActivity implements DashboardLauncher {

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager(), DataStorage.getInstance().getSurveys().getSurveysName());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void launchDashboard(String surveyName) {
        Intent i = new Intent(this, DashboardActivity.class);
        i.putExtra(DashboardActivity.EXTRA_SURVEYNAME, surveyName);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
    }
}

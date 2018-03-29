package zac.vince.jl.patou.popitprof;

import android.content.res.Configuration;
import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;

public class ChartsActivity extends AppCompatActivity {

    private PointF startFinish = new PointF();
    private PointF startSort = new PointF();
    private PointF startSwitch = new PointF();

    private Boolean sortHasBeenTriggered = false;
    private Boolean swiping = false;

    private static final String TAG = "AZER";
    public static final int TOUCH_DISTANCE = 150;

    private Fragment barFragment = new BarFragment();
    private Fragment circularGaugeFragment = new CircularGaugeFragment();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int currentFragment; // 0: barchart | 1: circularGaugeFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        setCurrentFragment(barFragment);
        currentFragment = 0;
        fragments.add(barFragment);
        fragments.add(circularGaugeFragment);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount() > 1) { // multitouch event

            if (event.getPointerCount() >= 3) {
                if ((startFinish.y - event.getY()) < -TOUCH_DISTANCE)
                    finish();
            }

             else if (event.getPointerCount() == 2) {
                if (event.getAction() == MotionEvent.ACTION_POINTER_2_DOWN) {
                    startSort.set(event.getX(), event.getY());
                    sortHasBeenTriggered = false;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (!sortHasBeenTriggered && (startSort.y - event.getY()) > TOUCH_DISTANCE) { // vers le haut

                        if (event.getX() < (getWidthOfScreen() / 2)){
                            Toast.makeText(this, "Gauche Change sens tri vers le haut", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this, "Droite change sens tri vers le haut", Toast.LENGTH_LONG).show();
                        }


                        sortHasBeenTriggered = true;
                    } else if (!sortHasBeenTriggered && (startSort.y - event.getY()) < -TOUCH_DISTANCE) { // vers le bas

                        if (event.getX() < (getWidthOfScreen() / 2)){
                            Toast.makeText(this, "Gauche Change sens tri vers le bas", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this, "Droite change sens tri vers le bas", Toast.LENGTH_LONG).show();
                        }

                        sortHasBeenTriggered = true;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                    Log.i(TAG, "UP");
                }
            }

        } else { // single touch event
            startFinish.set(event.getX(), event.getY());

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startSwitch.set(event.getX(), event.getY());
                swiping = true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (swiping) {
                    if ((startSwitch.x - event.getX()) > TOUCH_DISTANCE) {
                        nextFragment();
                        swiping = false;
                    }
                    if ((startSwitch.x - event.getX()) < -TOUCH_DISTANCE) {
                        previousFragment();
                        swiping = false;
                    }
                }
            }
        }

        return true;
    }

    private void nextFragment() {
        currentFragment++;
        currentFragment %= fragments.size();
        setCurrentFragment(fragments.get(currentFragment));
    }

    private void previousFragment(){
        currentFragment--;
        currentFragment %= fragments.size();
        currentFragment = Math.abs(currentFragment);
        setCurrentFragment(fragments.get(currentFragment));
    }

    private void setCurrentFragment(Fragment f){
        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction ft = m.beginTransaction();

        ft.replace(R.id.contentFragmentView, f);
        ft.addToBackStack(null);

        ft.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
    }

    private int getWidthOfScreen(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        Log.i("AZER", "Height:" +height );
        Log.i("AZER", "Width:" +width);

        return width;

    }
}

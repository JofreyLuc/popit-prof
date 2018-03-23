package zac.vince.jl.patou.popitprof;

import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class ChartsActivity extends AppCompatActivity {

    private PointF start = new PointF();
    private Boolean sortHasBeenTriggered = false;

    private static final String TAG = "AZER";
    private static final int TOUCH_DISTANCE = 150;

    private Fragment barFragment = new BarFragment();
    private Fragment circularGaugeFragment = new CircularGaugeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        setCurrentFragment(barFragment);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount() > 1) { // multitouch event
            if (event.getPointerCount() >= 3) {
                if ((start.y - event.getY()) < -TOUCH_DISTANCE)
                    finish();
            }
        } else { // single touch event

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                start.set(event.getX(), event.getY());
                sortHasBeenTriggered = false;
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE){
                if (!sortHasBeenTriggered && (start.y - event.getY()) > TOUCH_DISTANCE) { // vers le haut
                    Toast.makeText(this, "Change sens tri vers le haut", Toast.LENGTH_LONG).show();
                    sortHasBeenTriggered = true;
                }
                else if (!sortHasBeenTriggered && (start.y - event.getY()) < -TOUCH_DISTANCE) { // vers le bas
                    Toast.makeText(this, "Change sens tri vers le bas", Toast.LENGTH_LONG).show();
                    sortHasBeenTriggered = true;
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP){
                Log.i(TAG, "UP");
            }
        }

        return true;
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
}

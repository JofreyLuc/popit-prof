package zac.vince.jl.patou.popitprof;

import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class ChartsActivity extends AppCompatActivity {

    private int nbTouches = 0;
    private PointF start = new PointF();

    private Fragment barFragment = new BarFragment();
    private Fragment circularGaugeFragment = new CircularGaugeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        setCurrentFragment(barFragment);

    }

    //TODO : duplication de code
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN :
                nbTouches = 1;
                start.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_POINTER_DOWN :
                nbTouches++;
                break;

            case MotionEvent.ACTION_MOVE :
                if (nbTouches >= 3 && (Math.abs(start.y - event.getY()) > 150)) {
                    finish();
                }
                break;

            case MotionEvent.ACTION_UP :
                nbTouches = 0;
                break;

            case MotionEvent.ACTION_POINTER_UP :
                nbTouches--;
                break;

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

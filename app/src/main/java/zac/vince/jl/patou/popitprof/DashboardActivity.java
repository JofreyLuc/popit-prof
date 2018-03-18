package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    public static String EXTRA_SURVEYNAME;

    private int nbTouches = 0;
    private PointF start = new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView t = (TextView) findViewById(R.id.text1);
        t.setText(getIntent().getStringExtra(EXTRA_SURVEYNAME));
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
                if (nbTouches == 3 && (Math.abs(start.y - event.getY()) > 150)) {
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


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_up);
    }
}

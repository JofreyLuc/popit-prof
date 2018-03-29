package zac.vince.jl.patou.popitprof;

import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import zac.vince.jl.patou.popitprof.persistence.DataStorage;

public class DashboardActivity extends AppCompatActivity {

    public static String EXTRA_SURVEYNAME;

    private String surveyName;

    private int nbTouches = 0;
    private PointF start = new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        surveyName = getIntent().getStringExtra(EXTRA_SURVEYNAME);

        TextView t = (TextView) findViewById(R.id.text_surveyName);
        t.setText("Questionnaire " + surveyName);

        Button delete = (Button) findViewById(R.id.button_delete);
        delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSurvey();
            }
        });

        Button charts = (Button) findViewById(R.id.button_graphs);
        charts.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCharts();
            }
        });
    }

    private void deleteSurvey(){
        DataStorage.getInstance().removeSurvey(surveyName);
        Toast.makeText(this, "Questionnaire " + surveyName + " \"supprimé\"", Toast.LENGTH_LONG).show();
        finish();
    }

    private void launchCharts(){
        Intent i = new Intent(this, ChartsActivity.class);
        i.putExtra(EXTRA_SURVEYNAME, surveyName);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
    }


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


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
    }
}

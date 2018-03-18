package zac.vince.jl.patou.popitprof.model;

import java.util.ArrayList;

/**
 * Created by patrick on 3/16/18.
 */

public class Surveys {

    ArrayList<Survey> surveys;

    public Surveys() {
        surveys = new ArrayList<>();
    }

    public void addSurvey(Survey s) {
        surveys.add(s);
    }

    public ArrayList<Survey> getSurveys() {
        return surveys;
    }

    public Survey getSurvey(String name) {
        for (Survey s : surveys){
            if (s.getName().equals(name)) return s;
        }
        return null;
    }

    public ArrayList<String> getSurveysName(){
        ArrayList<String> names = new ArrayList<>();

        for (Survey s : surveys) {
            names.add(s.getName());
        }

        //TODO : temp ?
        for (int i = names.size()-1; i < 12; i++) {
            names.add("-1");
        }

        return names;
    }
}

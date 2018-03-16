package zac.vince.jl.patou.popitprof.persistence;

import java.util.ArrayList;

import zac.vince.jl.patou.popitprof.model.Question;
import zac.vince.jl.patou.popitprof.model.Survey;
import zac.vince.jl.patou.popitprof.model.Surveys;

/**
 * Created by patrick on 3/16/18.
 */

public class DataStorage {

    private Surveys surveys;
    private static DataStorage instance;

    private DataStorage(){
        surveys = new Surveys();
        ArrayList<Question> questions1 = new ArrayList<>();
        questions1.add(new Question(1, "Bonjour ?"));
        questions1.add(new Question(2, "Pizza à l'ananas ?"));
        Survey survey1 = new Survey(questions1, "Survey 1");
        surveys.addSurvey(survey1);

        ArrayList<Question> questions2 = new ArrayList<>();
        questions1.add(new Question(1, "Bonjour ?2"));
        questions1.add(new Question(2, "Pizza à l'ananas ?2"));
        Survey survey2 = new Survey(questions2, "Survey 2");
        surveys.addSurvey(survey2);
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public Surveys getSurveys() {
        return surveys;
    }
}

package zac.vince.jl.patou.popitprof.model;

import java.util.ArrayList;

/**
 * Created by patrick on 3/16/18.
 */

public class Survey {

    ArrayList<Question> questions;
    String name;

    public Survey(ArrayList<Question> questions, String name) {
        this.questions = questions;
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

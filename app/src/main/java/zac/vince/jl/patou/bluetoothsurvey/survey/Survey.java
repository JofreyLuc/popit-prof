package zac.vince.jl.patou.bluetoothsurvey.survey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by patrick on 3/15/18.
 */

public class Survey implements Serializable {

    private List<String> questions;
    private List<Integer> answers;

    public Survey(List<String> questions, List<Integer> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "questions=" + questions +
                ", answers=" + answers +
                '}';
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
}

package zac.vince.jl.patou.popitprof.model;

/**
 * Created by patrick on 3/16/18.
 */

public class Question {

    private int number;
    private String question;
    private Integer answer;
    private Double answerTime;

    public Question(int number, String question) {
        this.number = number;
        this.question = question;
        this.answer = null;
        this.answerTime = null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Double getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Double answerTime) {
        this.answerTime = answerTime;
    }
}

package com.example.quizpythonofppt;

public class QuestionMode1 {
    private String question , option1 , option2 , option3 ;
    private int correctAnsNO;


    public QuestionMode1(String question, String option1, String option2, String option3, int correctAnsNO) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnsNO = correctAnsNO;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public int getAnswerNr() {
        return correctAnsNO;
    }
    public void setAnswerNr(int correctAnsNO) {
        this.correctAnsNO = correctAnsNO;
    }


    public int getCorrectAnsNo() {
        return correctAnsNO;
    }
}

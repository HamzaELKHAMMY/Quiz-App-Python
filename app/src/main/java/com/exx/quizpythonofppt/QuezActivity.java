package com.exx.quizpythonofppt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.exx.quizpythonofppt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuezActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;


    private TextView tvQuestion , tvScore , tvQuestionNo , tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1 , rb2 , rb3 ;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;
    private QuestionMode1 currentQuestion;
    private ColorStateList textColorDefaultCb;
    private CountDownTimer countDownTimer;
    private long timeleftInMillis;
    private List<QuestionMode1>questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quez);

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup= findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();
        textColorDefaultCb = tvTimer.getTextColors();


        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered ==false){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()) {
                        checkAnswer();
                    }else{
                        Toast.makeText(QuezActivity.this,"Please Select an option",Toast.LENGTH_SHORT).show();
                    }
                }else {
                        showNextQuestion();
                }
            }
        });

    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected)+ 1;
        if(answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            tvScore.setText("Score: "+score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if(qCounter <totalQuestions){
            btnNext.setText("Next");
        }else {
            btnNext.setText("Finish");
        }
    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);

        if(qCounter<totalQuestions){
            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: "+qCounter+"/"+totalQuestions);
            answered = false;
            timeleftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();


        }else {
            finish();
        }


    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeleftInMillis , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftInMillis = millisUntilFinished;
                updateCountDownText();


            }

            @Override
            public void onFinish() {
                timeleftInMillis = 0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeleftInMillis / 1000) /60;
        int seconds = (int) (timeleftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tvTimer.setText(timeFormatted);
        if(timeleftInMillis < 10000){
            tvTimer.setTextColor(Color.RED);
        }else {
            tvTimer.setTextColor(textColorDefaultCb);
        }
    }

    private void addQuestions() {
        questionsList.add(new QuestionMode1("Python est un langage ?","interprété","machine","compilé",1));
        questionsList.add(new QuestionMode1("En python 3,que fait l’opérateur // ?","Retourne le reste","Division entière","Division du float par zéro",2));
        questionsList.add(new QuestionMode1("Quelle fonction est utilisée pour ouvrir le fichier en lecture en Python?","open(file_name, mode)","fopen(file_name, mode)","openfile(file_name, mode)",1));
        questionsList.add(new QuestionMode1("En python, quel mot clé est utilisé pour commencer une fonction?","function","fun","def",3));
        questionsList.add(new QuestionMode1("En python, quelle est la bonne méthode pour charger un module?","include math","import math","using math",2));
        questionsList.add(new QuestionMode1("Quelle est la sortie pour 'python' [-3]?","‘h’","‘t’","Erreur : index négatif",1));
        questionsList.add(new QuestionMode1("print tupe(type(int))","Error","0","type ‘type’",1));
        questionsList.add(new QuestionMode1("[ (a,b) for a in range(3) for b in range(a) ] ?","[(1,0),(2,0),(2,1)]","[(1,0),(2,1),(2,1)]","[(0,0),(1,1),(2,2)]",1));
        questionsList.add(new QuestionMode1("myList = ['a','b','c','d']\nprint(\"\".join(myList))","dcba","ERROR","abcd",3));
        questionsList.add(new QuestionMode1("print type(type(int))","type ‘int’","ERROR","type ‘type’",3));


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }
}
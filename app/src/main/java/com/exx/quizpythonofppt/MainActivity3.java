package com.ex.quizpythonofppt;

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

import com.ex.quizpythonofppt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;


    private TextView tvQuestion , tvScore , tvQuestionNo , tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1 , rb2 , rb3 , rb4;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;
    private QuesMM currentQuestion;
    private ColorStateList textColorDefaultCb;
    private CountDownTimer countDownTimer;
    private long timeleftInMillis;
    private List<QuesMM> quesMMList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        quesMMList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup= findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();
        textColorDefaultCb = tvTimer.getTextColors();


        addQuestions();
        totalQuestions = quesMMList.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered ==false){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked() ||rb4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(MainActivity3.this,"Please Select an option",Toast.LENGTH_SHORT).show();
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
        rb4.setTextColor(Color.RED);
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
            case 4 :
                rb4.setTextColor(Color.GREEN);
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
        rb4.setTextColor(dfRbColor);

        if(qCounter<totalQuestions){
            currentQuestion = quesMMList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

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

        quesMMList.add(new QuesMM("print(max('welcom to waytolearnx.com'))","x","w","y","D",3));
        quesMMList.add(new QuesMM("chr(ord('A'))","65","A","a","ERROR",2));
        quesMMList.add(new QuesMM("x = lambda a : a + 10\nprint(x(5))","10","15","5","55",2));
        quesMMList.add(new QuesMM("Lorsqu’une fonction est définie dans une classe, on l’appelle ?","Module","Classe","Méthode","Une autre fonction",3));
        quesMMList.add(new QuesMM("Les objets String sont-ils mutables?","Oui","Non","je ne sais pas","Otre",1));
        quesMMList.add(new QuesMM("print('toto'.find('u'))","-1","ERROR","4","je ne sais pas",1));
        quesMMList.add(new QuesMM("print(2*2**3)","12","64","36","16",4));
        quesMMList.add(new QuesMM("def f (x= 100, y= 100 ):return (x+y,x-y)\n print(f(y=200 , x = 100))","ce code ne fonctionne pas","(200)","(300,-100)","(300,-100)",3));
        quesMMList.add(new QuesMM("Comment créer un set vide en Python?","set()","Array()","{}","[]",1));
        quesMMList.add(new QuesMM("Comment créer un boucle d'événements avec Python?","Window.eventloop()","Window.mainloop()","Window.lop()","Eventloop.window()",2));
        quesMMList.add(new QuesMM("print(tupe(1/2))","Error","<class 'float'>","<class 'int'>","0.5",1));
        quesMMList.add(new QuesMM("print(20//3)","6.66","6","Error","600",2));
        quesMMList.add(new QuesMM("print(type(1/2))","Error","<class 'float'>","<class 'int'>","0.5",2));
        quesMMList.add(new QuesMM("Un objet string en pyhton est-il mutable?","Non","Oui","string n'est pas unobjet","je ne sais pas",2));
        quesMMList.add(new QuesMM("Quelle fonction sert à ouvrir un fichier en Python?","requestopen(filename ,mode)","fopen (filename ,mode)","openfile(filename , mode)","open(filename , mode)",4));
        quesMMList.add(new QuesMM("Qui a créé python?","rasmus lerdorf","mark zuckerberg","hamza elkhammy","Guido van Rossum",4));
        quesMMList.add(new QuesMM("Quel mot-clé python utilisé pour démmarer une fonction ?","import","method","Def","Fonction",3));
        quesMMList.add(new QuesMM("a=1     \nb=a%1   \nc=b+2   \nprint(b)","2","1","0","Error",3));
        quesMMList.add(new QuesMM("L=[6,9,6]\nn=L[0]+L[1]\nprint(n)","L[0]+L[1]","n","15","21",3));
        quesMMList.add(new QuesMM("L=[1,2,2]\nM=[4,9,9]\nN=L+M\nprint(N)","N","[5,11,11]","{1, 2, 2, 4, 9, 9}","[1, 2, 2, 4, 9, 9]",4));





    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }
}
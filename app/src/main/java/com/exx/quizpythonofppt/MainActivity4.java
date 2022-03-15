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

public class MainActivity4 extends AppCompatActivity {
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
    private QuesM1 currentQuestion;
    private ColorStateList textColorDefaultCb;
    private CountDownTimer countDownTimer;
    private long timeleftInMillis;
    private List<QuesM1> quesM1ListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        quesM1ListList = new ArrayList<>();
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
        totalQuestions = quesM1ListList.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered ==false){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked() ||rb4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(MainActivity4.this,"Please Select an option",Toast.LENGTH_SHORT).show();
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
            currentQuestion = quesM1ListList.get(qCounter);
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

        quesM1ListList.add(new QuesM1("s=\"Quelle question est-ce ?\"\nprint(s[0:16]+s[-1])","Quelle question est-ce","Quelle question ?","C\\'est la question 3","3",2));
        quesM1ListList.add(new QuesM1("Quelle est la sortie pour 'python' [-3]?","‘h’","‘t’","Erreur : index négatif","P",1));
        quesM1ListList.add(new QuesM1("def f (x= 100, y= 100 ):return (x+y,x-y)\n print(f(y=200 , x = 100))","ce code ne fonctionne pas","(200)","(300,-100)","(300,-100)",3));
        quesM1ListList.add(new QuesM1("u=4\nL=[u]\nwhile (u<10)\tu=2*u-1\n\tL.append(u)\nprint(L)","[4, 7, 13]","[4, 7, 13]","-1","[4,7]",2));
        quesM1ListList.add(new QuesM1("L=[8,4,1]\nn=L[0]+L[1]\nprint(n)","L[0]+L[1]","n","[8,4,1]","12",4));
        quesM1ListList.add(new QuesM1("L=[6,2,3]\nM=[4,6,6]\nN=L+M\nprint(N)","[6, 2, 3, 4, 6, 6]","N","[4,6,6,6,2,3]","[10,8,9]",1));
        quesM1ListList.add(new QuesM1("Le langage Python a été créé en:","1981","1991","2001","2011",2));
        quesM1ListList.add(new QuesM1("Pour écrire puis exécuter un programme Python, on peut utiliser","un éditeur de texte","un logiciel de traitement de texte","visual studio code","je ne sais pas",1));
        quesM1ListList.add(new QuesM1("print(4^3)","64","12","43","7",4));
        quesM1ListList.add(new QuesM1("Quel module permet d'utiliser les expressions régulières ?","re","regx","regex","regexp",1));
        quesM1ListList.add(new QuesM1("a=1     \nb=a%1   \nc=b+2   \nprint(b)","2","1","0","Error",3));
        quesM1ListList.add(new QuesM1("Un objet string en pyhton est-il mutable?","Non","Oui","string n'est pas unobjet","je ne sais pas",2));
        quesM1ListList.add(new QuesM1("Que retourne fraction(6,8) ?","68/10","0.75","Fraction(2,4)","fraction (3,4)",4));
        quesM1ListList.add(new QuesM1("Après un \"if\", \"while\" ou \"for\", qu'est-ce qui définit un bloc d'instructions ?","l'indentaion","{}","[]","<>",1));
        quesM1ListList.add(new QuesM1("Quelle instruction permet de saisir une valeur au clavier ?","get()","input","strlin()","getk()",2));
        quesM1ListList.add(new QuesM1("Si Maliste=[68,333,111], comment supprimer le 2éme élément (333) ?","del Maliste[2]","Maliste.remove(1)","del Maliste[1]","Maliste.remove(2)",3));
        quesM1ListList.add(new QuesM1("On place un \\ en fin de ligne pour ","commenter","prolonger la ligne","invalider la ligne suivante","marquer l'échappement sur toute la ligne",2));
        quesM1ListList.add(new QuesM1("Comment s'appelle l'éditeur et environnement de développement habituel de Python ?","IDLE","Python Studio","eclipse","LabVIEW",1));
        quesM1ListList.add(new QuesM1("\"%d\" % 0b11 affiche","3","11","17","2833",1));
        quesM1ListList.add(new QuesM1("Comment faire une boucle de 1 à 10 ?","for i=1 to 10 do","for i in (1..10):","for i in range(1,10):","for(i=1;i<=10;i++)",3));
        quesM1ListList.add(new QuesM1("print([1,2]+[3,4])","[1, 2, 3, 4]","[1, 2, 3, 4]","[4,6]","invalid syntax",1));
        quesM1ListList.add(new QuesM1("Comment tirer un nombre au sort entre 1 et 10 ?","randint(1,10)","random(1,10)","int(random(1,10))","rnd(1,10)",1));
        quesM1ListList.add(new QuesM1("A quoi correspond ** ?","décalage binaire","puissance","pointeur","modulo",2));
        quesM1ListList.add(new QuesM1("Que donne 7%2 ?","invalid syntax","3","1","2",3));
        quesM1ListList.add(new QuesM1("for i in range(1,8,3): print(i) affichera","1 2 3 4 5 6 7 8 7 6 5 4 3","8 16 24","1 4 7","1 3 6",3));
        quesM1ListList.add(new QuesM1("Quelle instruction permet de gérer les exceptions (erreurs) ?","on error / resume","on error / throw","try / except","try / catch",3));
        quesM1ListList.add(new QuesM1("Que renvoie type(print) ?","<class 'str' '>","<class 'builtin_function_or_method'>","<class 'print'>","unexpected argument",2));
        quesM1ListList.add(new QuesM1("Quelle est la méthode inverse de \"split\" ?","merge","pack","join","hash",3));
        quesM1ListList.add(new QuesM1("Comment indiquer un commentaire en Python ?","/*","#","//","||",2));
        quesM1ListList.add(new QuesM1("Comment concaténer Ch1 et Ch2 ?","Ch1+Ch2","Ch1.CH2","Ch1&Ch2","concat(Ch1,Ch2)",1));

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }
}
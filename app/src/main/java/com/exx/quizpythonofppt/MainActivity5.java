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

public class MainActivity5 extends AppCompatActivity {
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
    private QuesM2 currentQuestion;
    private ColorStateList textColorDefaultCb;
    private CountDownTimer countDownTimer;
    private long timeleftInMillis;
    private List<QuesM2> quesM2List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        quesM2List = new ArrayList<>();
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
        totalQuestions = quesM2List.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered ==false){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked() ||rb4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(MainActivity5.this,"Please Select an option",Toast.LENGTH_SHORT).show();
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

        if (qCounter < totalQuestions) {
            currentQuestion = quesM2List.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: " + qCounter + "/" + totalQuestions);
            answered = false;
            timeleftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();


        } else {
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

        quesM2List.add(new QuesM2("Si a=0 + 1 j , que vaut a*a ?","(-1 + 0j)","j*2","N/A","0",1));
        quesM2List.add(new QuesM2("divmod(7,2) retourne","( 3 , 5 )","( 3 , 1 )","3","1",2));
        quesM2List.add(new QuesM2("Quel symbole est utilisé pour ajouter un \"décorateur\" à une fonction #","#","&","$","@",4));
        quesM2List.add(new QuesM2("print(chr(66))","unexpected argument","66","b","B",4));
        quesM2List.add(new QuesM2("Que retourne not(True and False) ?","N/A","False","True","0",3));
        quesM2List.add(new QuesM2("Pour écrire puis exécuter un programme Python, on peut utiliser","un éditeur de texte","un logiciel de traitement de texte","visual studio code","je ne sais pas",1));
        quesM2List.add(new QuesM2("Le type \"int\" entre Python 2.x et 3.x","reste inchangé","passe de 32 à 64 bits","passe de 8 à 32 bits","passe de 32 bits à illimité",4));
        quesM2List.add(new QuesM2("Que retourne 3.68E4 ","368.0","3680000.0","36800.0","3680.0",3));
        quesM2List.add(new QuesM2("Si ch=\"abcdefgh\" , que retourne ch[-3:] ?","e'","'fgh'","'abc'","'f'",2));
        quesM2List.add(new QuesM2("D = {1: 3, 2: 4, 3: 5, 4: 2, 5: 1}\nprint(D[D[D[1]]])","1","2","3","4",1));
        quesM2List.add(new QuesM2("D = {1: 3, 2: 4, 3: 5, 4: 2, 5: 1}\nprint(D[D[D[2]]])","2","4","6","8",2));
        quesM2List.add(new QuesM2("d = {i**2: i for i in range(-3,3)}\nprint(d)","{9: -3, 4: 2, 1: 1, 0: 0}","{4: 2, 1: 1, 0: 0, 9: 3}","{9: -3, 4: -2, 1: -1, 0: 0}","{0: 0, 1: 1, 2: 4, 3: 9}",1));
        quesM2List.add(new QuesM2("x = lambda a : a + 1.25\nprint(x(5))","6.25","15","1.25","Error",1));
        quesM2List.add(new QuesM2("Comment s'appelle l'éditeur et environnement de développement habituel de Python ?","IDLE","Python Studio","eclipse","LabVIEW",1));
        quesM2List.add(new QuesM2("a=9\na += 2\nprint(a)","11","9","9.0","2",1));
        quesM2List.add(new QuesM2("def diff(val1,val2):\n           return val2 - val1\n a = diff(3.0,-2.0)  \n print(a)         ","5.0","-1.0","1.0","-5.0",4));
        quesM2List.add(new QuesM2("Quelle est la sortie du code suivant\n[ (a,b) for a in range(3) for b in range(a) ]","[ (1,0),(2,1),(3,2)]","[ (0,0),(1,1),(2,2)]","[(1, 0), (2, 0), (2, 1)]","(1,0),(2,1),(2,1)]",3));
        quesM2List.add(new QuesM2("x = 2 ; y = 10\nx  = y * x + 1\nprint(x)","21","Error","20+1","x",1));
        quesM2List.add(new QuesM2("print(max(\"please help\" ))","s","e","a blank space character","p",1));
        quesM2List.add(new QuesM2("y = [4, 5,1j]\ny.sort()","[1j,4,5]","[5,4,1j]","[4,5,1j]","Type Error",4));
        quesM2List.add(new QuesM2("Un objet string en pyhton est-il mutable?","Non","Oui","string n'est pas unobjet","je ne sais pas",2));
        quesM2List.add(new QuesM2("a = 0\nwhile a<10:\nprint(\"you are learning python\")","9","10","11","Infinite number of times.",4));
        quesM2List.add(new QuesM2("print(2 ** 2 **3)","256","12","64","38",1));
        quesM2List.add(new QuesM2("print(\"abbzxyzxzxabb\".count(‘abb’,-10,-1))","Error","2","0","1",3));
        quesM2List.add(new QuesM2("Quel module est utilisé en python pour créer des graphique ?","Canvas","Turtle","Tkinter","Graphics",2));
        quesM2List.add(new QuesM2("Laquelle des déclarations suivantes attribue la valeur 100 à la variable x en Python :","x ← 100","let x = 100","x = 100","x := 100",3));
        quesM2List.add(new QuesM2("Quelle fonction Python intégrée renvoie le numéro unique attribué à un objet?","identity()","id()","ref()","refnum()",2));
        quesM2List.add(new QuesM2("Lesquels des éléments suivants sont des mots réservés Python (mots clés)?","goto","default","Sting","None",4));
        quesM2List.add(new QuesM2("str = \"pynative\"\nprint (str[1:3])","yn","pyn","pn","ya",1));
        quesM2List.add(new QuesM2("x = lambda a : a + 10\nprint(x(5))","10","15","5","55",2));
        quesM2List.add(new QuesM2("print(20//3)","6.66","6","Error","600",2));
        quesM2List.add(new QuesM2("valueOne = 5 ** 2\nvalueTwo = 5 ** 3\nprint(valueOne)\nprint(valueTwo)","10\n25","Error: invalid syntax","Error","25\n125",4));
        quesM2List.add(new QuesM2("listOne =[20, 40, 60, 80]\nlistTwo =[20, 40, 60, 80]\nprint(listOne==listTwo)\nprint(listOne is listTwo)","True\nFalse","True\nTrue","False\nTrue","False\nFalse",1));
        quesM2List.add(new QuesM2("var = \"James\" * 2  * 3\nprint(var)","JamesJamesJamesJamesJamesJames","JamesJamesJamesJamesJames","Error: invalid syntax","JamesJames",1));
        quesM2List.add(new QuesM2("var1 = 1 ;var2 = 2 ;var3 = \"3\"\nprint(var + var2 + var3)","Error","16","23","123",1));
        quesM2List.add(new QuesM2("p, q, r = 10, 20 ,30\nprint(p, q, r)","10 20 30","10 20 ","1020","Error: invalid syntax",1));
        quesM2List.add(new QuesM2("var= \"James Bond\"\nprint(var[2::-1])","maj","dnoB semaJ","dno","Jam",1));
        quesM2List.add(new QuesM2("x = 36 / 4 * (3 +  2) * 4 + 2\nprint(x)","182.0","37","The Program executed with errors","117",1));
        quesM2List.add(new QuesM2("for x in range(0.5, 5.5, 0.5):\nprint(x)","The Program executed with errors","[0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5]","[0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]","[0.5,2.5, 3, 3.5, 4, 4.5, 5]",1));
        quesM2List.add(new QuesM2("print('%x, %X' % (15, 15))","15 15","F F","f, F","f, F",3));


        







    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }


}
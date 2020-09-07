package com.SundarImages.BhartiImageQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private ImageButton rb1;
    private ImageButton rb2;
    private ImageButton rb3;
    private ImageButton cb1;
    private ImageButton cb2;
    private ImageButton cb3;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    int answerNr;
    Boolean flag1,shown,correct;
    ImageView img;
    private boolean answered;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        rb1 = findViewById(R.id.image_button1);
        rb2 = findViewById(R.id.image_button2);
        rb3 = findViewById(R.id.image_button3);
        cb1 = findViewById(R.id.image_check_button1);
        cb2 = findViewById(R.id.image_check_button2);
        cb3 = findViewById(R.id.image_check_button3);
        img = findViewById(R.id.imageQuestion);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultCd = textViewCountDown.getTextColors();
        if (savedInstanceState == null) {
            Log.d("in ","savedInstanceNull");
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answered) {
                    if(!flag1)
                    {
                        Toast.makeText(Quiz.this, "Please provide an answer.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Log.d("in ", "not answered so check answer");
                        checkAnswer();

                    }
                } else {
                    if(!shown)
                    {  Log.d("in","showing");
                        if(!flag1) {
                            Log.d("flag",flag1.toString());
                            if (answerNr == currentQuestion.getAnswerNr()) {
                                score++;
                                flag1 = true;
                                correct = true;
                                Log.d("score",""+score);
                                textViewScore.setText("स्कोर: " + score);
                                countDownTimer.cancel();
                            }
                        }

                        showSolution();
                        shown=true;
                    }
                    else{
                    Log.d("in ","answer showNextQuestion");
                    showSolution();
                    showNextQuestion();}
                }
            }
        });
    }
    private void showNextQuestion() {
        Log.d("in ","showNextQuestion");
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        rb1.setBackgroundColor(Color.WHITE);
        rb2.setBackgroundColor(Color.WHITE);
        rb3.setBackgroundColor(Color.WHITE);
        cb1.setBackgroundColor(Color.WHITE);
        cb2.setBackgroundColor(Color.WHITE);
        cb3.setBackgroundColor(Color.WHITE);
        rb1.setClickable(true);
        rb2.setClickable(true);
        rb3.setClickable(true);
        if (questionCounter < 10) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            String PACKAGE_NAME = getApplicationContext().getPackageName();

            String opt1 = currentQuestion.getOption1();
            int o1 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt1 , null, null);
            rb1.setBackgroundResource(o1);

            String opt2 = currentQuestion.getOption2();
            int o2 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt2 , null, null);
            rb2.setBackgroundResource(o2);

            String opt3 = currentQuestion.getOption3();
            int o3 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt3 , null, null);
            rb3.setBackgroundResource(o3);


            String test = currentQuestion.getQuestion(); // store only image name in database(means not   store "R.drawable.image") like "image".\
             //  this is image file name

            int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+test , null, null);
            img.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));

            questionCounter++;
            textViewQuestionCount.setText("प्रश्न: " + questionCounter + "/" + 10);
            answered = false;
            flag1 = false;
            shown = false;
            correct = false;
            answerNr = 0;
            buttonConfirmNext.setText("पुष्टि करें");

            startCountDown();
        } else {
            finishQuiz();
        }
    }
    private void startCountDown() {
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                correct = true;
                updateCountDownText();
                rb1.setClickable(false);
                rb2.setClickable(false);
                rb3.setClickable(false);
                shown = true;
                answered = true;
                showSolution();
            }
        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if (timeLeftInMillis < 5000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    private void checkAnswer() {
        Log.d("in ","check answer");
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);

    }
    private void showSolution() {
        Log.d("in ","show solution");
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        String opt1 = "green_tick";
        int o1 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt1 , null, null);
        String opt2 = "wrong";
        int o2 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt2 , null, null);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                countDownTimer.cancel();
                cb1.setBackgroundResource(o1);
                break;
            case 2:
                countDownTimer.cancel();
                cb2.setBackgroundResource(o1);
                break;
            case 3:
                countDownTimer.cancel();
                cb3.setBackgroundResource(o1);
                break;
        }
        if(!correct){
        switch (answerNr) {
            case 1:
                countDownTimer.cancel();
                cb1.setBackgroundResource(o2);
                break;
            case 2:
                countDownTimer.cancel();
                cb2.setBackgroundResource(o2);
                break;
            case 3:
                countDownTimer.cancel();
                cb3.setBackgroundResource(o2);
                break;
        }}
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("आगे");
        } else {
            buttonConfirmNext.setText("समाप्त");
        }
    }
    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }


    @Override
    public void onClick(View view) {
        answered = true;
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        String opt3 = "point";
        int o3 = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+opt3 , null, null);
            switch (view.getId()) {
                case R.id.image_button1:
                    //countDownTimer.cancel();
                    cb1.setBackgroundResource(o3);
                    cb2.setBackgroundColor(Color.WHITE);
                    cb3.setBackgroundColor(Color.WHITE);
                    answerNr = 1;
                    break;
                case R.id.image_button2:
                   // countDownTimer.cancel();
                    cb2.setBackgroundResource(o3);
                    cb1.setBackgroundColor(Color.WHITE);
                    cb3.setBackgroundColor(Color.WHITE);
                    answerNr = 2;
                    break;
                case R.id.image_button3:
                    //countDownTimer.cancel();
                    cb1.setBackgroundColor(Color.WHITE);
                    cb2.setBackgroundColor(Color.WHITE);
                    cb3.setBackgroundResource(o3);
                    answerNr = 3;
                    break;
            }
    }
}